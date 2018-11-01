package i5b5.wajaty.hd.projekt.loading.tasks;

import i5b5.wajaty.hd.projekt.loading.SourceSystem;
import i5b5.wajaty.hd.projekt.loading.commons.CommonLoader;
import i5b5.wajaty.hd.projekt.model.dwh.NetworkSession;
import i5b5.wajaty.hd.projekt.model.dwh.NetworkSessionPrice;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import i5b5.wajaty.hd.projekt.mybatis.mappers.DwhMapper;
import i5b5.wajaty.hd.projekt.mybatis.mappers.NetworkMapper;
import i5b5.wajaty.hd.projekt.utils.MappingUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NetworkTask {

    private final DwhMapper dwhMapper;
    private final NetworkMapper networkMapper;
    private final PlatformTransactionManager txManager;

    @Autowired
    public NetworkTask(DwhMapper dwhMapper, NetworkMapper networkMapper, @Qualifier("dwhTxMan") PlatformTransactionManager txManager) {
        this.dwhMapper = dwhMapper;
        this.networkMapper = networkMapper;
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
            processSessionPrices();
            processNetworkSessions();
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            LogManager.getLogger(NetworkTask.class).error("An error occured while network sessions source load, loading is rollbacked.",e);

        }

    }

    private void processClients() {
        CommonLoader.loadAllClients(networkMapper, dwhMapper, SourceSystem.NETWORK.value());
    }


    private void processSubscriptionTypes() {
        CommonLoader.loadAllSubscriptionTypes(networkMapper, dwhMapper, SourceSystem.NETWORK.value());
    }

    private void processProvinces() {
        CommonLoader.loadAllProvinces(networkMapper, dwhMapper, SourceSystem.NETWORK.value());
    }

    private void processDistricts() {
        CommonLoader.loadAllDistricts(networkMapper, dwhMapper, SourceSystem.NETWORK.value());
    }

    private void processLocalities() {
        CommonLoader.loadAllLocalities(networkMapper, dwhMapper, SourceSystem.NETWORK.value());
    }

    private void processSessionPrices() {
        System.out.println("Starting loading network session prices from system " + SourceSystem.NETWORK.value());
        networkMapper.getAllNetworkSessionPrices().forEach(price -> {
            System.out.println("Loading network session price with id " + price.getNetworkSessionPriceId());
            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_network_session_price_id",
                    "network_session_price_id",
                    SourceSystem.NETWORK.value(),
                    String.valueOf(price.getNetworkSessionPriceId()));
            if (dwhKey == null) {
                final long key = dwhMapper.getNextKeyForTable("network_session_price_seq");

                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_network_session_price_id",
                        "network_session_price_id",
                        SourceSystem.NETWORK.value(),
                        String.valueOf(price.getNetworkSessionPriceId()),
                        key);

                final NetworkSessionPrice translateNetworkSessionPrice = MappingUtils.translateNetworkSessionPrice(price);
                translateNetworkSessionPrice.setNetworkSessionPriceId((int) key);

                dwhMapper.insertNewNetworkSessionPriceRow(translateNetworkSessionPrice);
            } else {
                final NetworkSessionPrice priceRow = dwhMapper.findLastActiveNetworkSessionPriceRow(dwhKey.getDwhId());
                if (price.isDeleted() && priceRow.getEndDate() == null) {
                    dwhMapper.closeNetworkSessionPriceRow(priceRow.getNetworkSessionPriceId());
                } else if (price.getLastActionTime().after(priceRow.getStartDate())) {
                    dwhMapper.closeNetworkSessionPriceRow(priceRow.getNetworkSessionPriceId());

                    final NetworkSessionPrice translateNetworkSessionPrice = MappingUtils.translateNetworkSessionPrice(price);
                    translateNetworkSessionPrice.setNetworkSessionPriceId((int) dwhKey.getDwhId());

                    dwhMapper.insertNewNetworkSessionPriceRow(translateNetworkSessionPrice);
                }
            }
        });
    }

    private void processNetworkSessions() {
        System.out.println("Starting loading network sessions from system " + SourceSystem.NETWORK.value());
        networkMapper.getAllNetworkSessions().forEach(session -> {
            System.out.println("Loading network session with id " + session.getNetworkSessionId());
            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_network_session_id",
                    "network_session_id",
                    SourceSystem.NETWORK.value(),
                    String.valueOf(session.getNetworkSessionId()));

            if (dwhKey == null) {

                final NetworkSession translateNetworkSession = MappingUtils.translateNetworkSession(session);

                decideIfRejectNetworkSession(dwhMapper.findDwhKeyForSourceSystemAndKey("map_client_id",
                        "client_id",
                        SourceSystem.NETWORK.value(),
                        String.valueOf(session.getClientId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_locality_id",
                                "locality_id",
                                SourceSystem.NETWORK.value(),
                                String.valueOf(session.getLocalityId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_network_session_price_id",
                                "network_session_price_id",
                                SourceSystem.NETWORK.value(),
                                String.valueOf(session.getNetworkSessionPriceId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                                "subscription_type_id",
                                SourceSystem.NETWORK.value(),
                                String.valueOf(session.getSubscriptionTypeId())),
                        session,
                        translateNetworkSession
                        );
            }
        });
    }

    private void decideIfRejectNetworkSession(
            MappingClass clientId,
            MappingClass localityId,
            MappingClass networkSessionPriceId,
            MappingClass subscriptionTypeId,
            i5b5.wajaty.hd.projekt.model.sources.network.NetworkSession networkSession,
            NetworkSession dwhSession) {
        boolean shouldReject = CommonLoader.checkRejected(clientId,localityId,subscriptionTypeId, dwhSession);
        if (networkSessionPriceId == null) {
            dwhSession.setNetworkSessionPriceId(null);
            shouldReject = true;
        } else {
            dwhSession.setNetworkSessionPriceId((int) networkSessionPriceId.getDwhId());
        }

        if (shouldReject) {
            dwhSession.setNetworkSessionId(null);
            dwhMapper.insertRejectedNetworkSession(dwhSession,
                    SourceSystem.NETWORK.value(),
                    String.valueOf(networkSession.getNetworkSessionId()));
        } else {
            final long key = dwhMapper.getNextKeyForTable("network_session_seq");
            dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_network_session_id",
                    "network_session_id",
                    SourceSystem.NETWORK.value(),
                    String.valueOf(networkSession.getNetworkSessionId()),
                    key);

            dwhSession.setNetworkSessionId(key);


            dwhMapper.insertNewNetworkSessionRow(dwhSession);
        }

    }
}
