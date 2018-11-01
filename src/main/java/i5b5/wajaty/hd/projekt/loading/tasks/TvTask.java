package i5b5.wajaty.hd.projekt.loading.tasks;

import i5b5.wajaty.hd.projekt.loading.SourceSystem;
import i5b5.wajaty.hd.projekt.loading.commons.CommonLoader;
import i5b5.wajaty.hd.projekt.model.dwh.Call;
import i5b5.wajaty.hd.projekt.model.dwh.TvChannel;
import i5b5.wajaty.hd.projekt.model.dwh.TvTransmission;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import i5b5.wajaty.hd.projekt.mybatis.mappers.DwhMapper;
import i5b5.wajaty.hd.projekt.mybatis.mappers.TvMapper;
import i5b5.wajaty.hd.projekt.utils.MappingUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TvTask   {

    private final DwhMapper dwhMapper;
    private final TvMapper tvMapper;
    private final PlatformTransactionManager txManager;

    @Autowired
    public TvTask(DwhMapper dwhMapper, TvMapper tvMapper, PlatformTransactionManager txManager) {
        this.dwhMapper = dwhMapper;
        this.tvMapper = tvMapper;
        this.txManager = txManager;
    }
    public void run() {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            processClients();
            processProvinces();
            processDistricts();
            processLocalities();
            processSubscriptionTypes();
            processTvChannels();
            processTvTransmission();
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            LogManager.getLogger(TvTask.class).error("An error occured while tv transmissions source load, loading is rollbacked.",e);
        }
    }

    private void processTvTransmission() {
        tvMapper.getAllTvTransmissions().forEach(x -> {

            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_tv_transmission_id",
                    "tv_transmission_id",
                    SourceSystem.TV.value(),
                    String.valueOf(x.getTvTrasnsmissionId()));

            if (dwhKey == null){
                TvTransmission transmission = MappingUtils.translateTvTransmission(x);

                decideIfRejectTvTransmission(dwhMapper.findDwhKeyForSourceSystemAndKey("map_client_id",
                        "client_id",
                        SourceSystem.TV.value(),
                        String.valueOf(x.getClientId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_locality_id",
                                "locality_id",
                                SourceSystem.TV.value(),
                                String.valueOf(x.getLocalityId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_tv_channel_id",
                                "tv_channel_id",
                                SourceSystem.TV.value(),
                                String.valueOf(x.getTvChannelId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                                "subscription_type_id",
                                SourceSystem.TV.value(),
                                String.valueOf(x.getSubscriptionTypeId())),
                        x,
                        transmission);
            }

        });
    }

    private void processTvChannels() {
        tvMapper.getAllTvChannels().forEach(x -> {
            MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_tv_channel_id",
                    "tv_channel_id",
                    SourceSystem.TV.value(),
                    String.valueOf(x.getTvChannelId()));
            if (dwhKey == null) {
                long tvChannelSeq = dwhMapper.getNextKeyForTable("tv_channel_seq");
                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_tv_channel_id",
                        "tv_channel_id",
                        SourceSystem.TV.value(),
                        String.valueOf(x.getTvChannelId()),
                        tvChannelSeq);
                TvChannel tvChannel = MappingUtils.translateTvChannel(x);
                tvChannel.setTvChannelId((int) tvChannelSeq);
                dwhMapper.insertNewTvChannelRow(tvChannel);
            } else {
                TvChannel tvChannelRow = dwhMapper.findLastActiveTvChannelRow(dwhKey.getDwhId());
                if (x.isDeleted() && tvChannelRow.getEndDate() == null) {
                    dwhMapper.closeTvChannelRow(dwhKey.getDwhId());
                } else if (x.getLastActionTime().after(tvChannelRow.getStartDate())) {
                    dwhMapper.closeTvChannelRow(dwhKey.getDwhId());
                    TvChannel tvChannel = MappingUtils.translateTvChannel(x);
                    tvChannel.setTvChannelId((int) dwhKey.getDwhId());
                    dwhMapper.insertNewTvChannelRow(tvChannel);
                }
            }
        });
    }

    private void processSubscriptionTypes() {
        CommonLoader.loadAllSubscriptionTypes(tvMapper, dwhMapper, SourceSystem.TV.value());
    }

    private void processLocalities() {
        CommonLoader.loadAllLocalities(tvMapper, dwhMapper, SourceSystem.TV.value());
    }

    private void processDistricts() {
        CommonLoader.loadAllDistricts(tvMapper, dwhMapper, SourceSystem.TV.value());
    }

    private void processProvinces() {
        CommonLoader.loadAllProvinces(tvMapper, dwhMapper, SourceSystem.TV.value());
    }

    private void processClients() {
        CommonLoader.loadAllClients(tvMapper, dwhMapper, SourceSystem.TV.value());
    }

    private void decideIfRejectTvTransmission(
            MappingClass clientId,
            MappingClass localityId,
            MappingClass tvChannelId,
            MappingClass subscriptionTypeId,
            i5b5.wajaty.hd.projekt.model.sources.tv.TvTransmission tvTransmission,
            TvTransmission dwhTvTransmission
    ) {
        boolean shouldReject = CommonLoader.checkRejected(clientId, localityId, subscriptionTypeId, dwhTvTransmission);
        if (tvChannelId == null) {
            dwhTvTransmission.setTvChannelId(null);
            shouldReject = true;
        } else {
            dwhTvTransmission.setTvChannelId((int)tvChannelId.getDwhId());
        }

        if (shouldReject) {
            dwhTvTransmission.setTvTransmissionId(null);
            dwhMapper.insertRejectedTvTransmission(dwhTvTransmission,
                    SourceSystem.TV.value(),
                    String.valueOf(tvTransmission.getTvTrasnsmissionId()));
        } else {
            final long key = dwhMapper.getNextKeyForTable("tv_transmission_seq");
            dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_tv_transmission_id",
                    "tv_transmission_id",
                    SourceSystem.TV.value(),
                    String.valueOf(tvTransmission.getTvTrasnsmissionId()),
                    key);
            dwhTvTransmission.setTvTransmissionId(key);
            dwhMapper.insertNewTvTransmissionRow(dwhTvTransmission);
        }
    }
}
