package i5b5.wajaty.hd.projekt.loading.tasks;

import i5b5.wajaty.hd.projekt.loading.SourceSystem;
import i5b5.wajaty.hd.projekt.loading.commons.CommonLoader;
import i5b5.wajaty.hd.projekt.model.dwh.Call;
import i5b5.wajaty.hd.projekt.model.dwh.CallPrice;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import i5b5.wajaty.hd.projekt.mybatis.mappers.CallMapper;
import i5b5.wajaty.hd.projekt.mybatis.mappers.DwhMapper;
import i5b5.wajaty.hd.projekt.utils.MappingUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
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
public class CallTask {
    private final DwhMapper dwhMapper;
    private final CallMapper callMapper;
    private final PlatformTransactionManager txManager;

    @Autowired
    public CallTask(DwhMapper dwhMapper, CallMapper callMapper, PlatformTransactionManager txManager) {
        this.dwhMapper = dwhMapper;
        this.callMapper = callMapper;
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
            processCallPrices();
            processCalls();
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            LogManager.getLogger(CallTask.class).error("An error occured while calls source load, loading is rollbacked.",e);
        }
    }

    private void processCalls() {
        callMapper.getAllCalls().forEach(x -> {

            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_message_id",
                    "message_id",
                    SourceSystem.CALL.value(),
                    String.valueOf(x.getCallId()));

            if (dwhKey == null){
                Call call = MappingUtils.translateCall(x);

                decideIfRejectCall(dwhMapper.findDwhKeyForSourceSystemAndKey("map_client_id",
                        "client_id",
                        SourceSystem.CALL.value(),
                        String.valueOf(x.getClientId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_locality_id",
                                "locality_id",
                                SourceSystem.CALL.value(),
                                String.valueOf(x.getLocalityId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_call_price_id",
                                "call_price_id",
                                SourceSystem.CALL.value(),
                                String.valueOf(x.getCallPriceId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                                "subscription_type_id",
                                SourceSystem.CALL.value(),
                                String.valueOf(x.getSubscriptionTypeId())),
                        x,
                        call
                );
            }

        });
    }

    private void processCallPrices() {
        callMapper.getAllCallPrices().forEach(x -> {
            MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_call_price_id",
                    "call_price_id",
                    SourceSystem.CALL.value(),
                    String.valueOf(x.getCallPriceId()));
            if (dwhKey == null) {
                long callPriceSeq = dwhMapper.getNextKeyForTable("call_price_seq");
                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_call_price_id",
                        "call_price_id",
                        SourceSystem.CALL.value(),
                        String.valueOf(x.getCallPriceId()),
                        callPriceSeq);
                CallPrice callPrice = MappingUtils.translateCallPrice(x);
                callPrice.setCallPriceId((int) callPriceSeq);
                dwhMapper.insertNewCallPriceRow(callPrice);
            } else {
                CallPrice callPriceRow = dwhMapper.findLastActiveCallPriceRow(dwhKey.getDwhId());
                if (x.isDeleted() && callPriceRow.getEndDate() == null) {
                    dwhMapper.closeCallPriceRow(dwhKey.getDwhId());
                } else if (x.getLastActionTime().after(callPriceRow.getStartDate())) {
                    dwhMapper.closeCallPriceRow(dwhKey.getDwhId());
                    CallPrice callPrice = MappingUtils.translateCallPrice(x);
                    callPrice.setCallPriceId((int) dwhKey.getDwhId());
                    dwhMapper.insertNewCallPriceRow(callPrice);
                }
            }
        });
    }

    private void processSubscriptionTypes() {
        CommonLoader.loadAllSubscriptionTypes(callMapper, dwhMapper, SourceSystem.CALL.value());
    }

    private void processLocalities() {
        CommonLoader.loadAllLocalities(callMapper, dwhMapper, SourceSystem.CALL.value());
    }

    private void processDistricts() {
        CommonLoader.loadAllDistricts(callMapper, dwhMapper, SourceSystem.CALL.value());
    }

    private void processProvinces() {
        CommonLoader.loadAllProvinces(callMapper, dwhMapper, SourceSystem.CALL.value());
    }

    private void processClients() {
        CommonLoader.loadAllClients(callMapper, dwhMapper, SourceSystem.CALL.value());
    }

    private void decideIfRejectCall(
            MappingClass clientId,
            MappingClass localityId,
            MappingClass callPriceId,
            MappingClass subscriptionTypeId,
            i5b5.wajaty.hd.projekt.model.sources.call.Call call,
            Call dwhCall
    ) {
        boolean shouldReject = CommonLoader.checkRejected(clientId, localityId, subscriptionTypeId, dwhCall);
        if (callPriceId == null) {
            dwhCall.setCallPriceId(null);
            shouldReject = true;
        } else {
            dwhCall.setCallPriceId((int)callPriceId.getDwhId());
        }

        if (shouldReject) {
            dwhCall.setCallId(null);
            dwhMapper.insertRejectedCall(dwhCall,
                    SourceSystem.CALL.value(),
                    String.valueOf(call.getCallId()));
        } else {
            final long key = dwhMapper.getNextKeyForTable("call_seq");
            dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_call_id",
                    "call_id",
                    SourceSystem.CALL.value(),
                    String.valueOf(call.getCallId()),
                    key);
            dwhCall.setCallId(key);
            dwhMapper.insertNewCallRow(dwhCall);
        }
    }
}
