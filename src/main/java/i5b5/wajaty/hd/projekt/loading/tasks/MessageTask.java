package i5b5.wajaty.hd.projekt.loading.tasks;

import i5b5.wajaty.hd.projekt.loading.SourceSystem;
import i5b5.wajaty.hd.projekt.loading.commons.CommonLoader;
import i5b5.wajaty.hd.projekt.model.dwh.Message;
import i5b5.wajaty.hd.projekt.model.dwh.MessagePrice;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import i5b5.wajaty.hd.projekt.mybatis.mappers.DwhMapper;
import i5b5.wajaty.hd.projekt.mybatis.mappers.MessageMapper;
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
public class MessageTask {

    private final DwhMapper dwhMapper;
    private final MessageMapper messageMapper;
    private final PlatformTransactionManager txManager;

    @Autowired
    public MessageTask(DwhMapper dwhMapper, MessageMapper messageMapper, @Qualifier("dwhTxMan") PlatformTransactionManager txManager) {
        this.dwhMapper = dwhMapper;
        this.messageMapper = messageMapper;
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
            processMessagePrices();
            processMessages();
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            LogManager.getLogger(MessageTask.class).error("An error occured while messages source load, loading is rollbacked.",e);

        }
    }

    private void processMessages() {
        messageMapper.getAllMessages().forEach(x -> {

            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_message_id",
                    "message_id",
                    SourceSystem.MESSAGE.value(),
                    String.valueOf(x.getMessageId()));

            if (dwhKey == null) {

                Message message = MappingUtils.translateMessage(x);

                decideIfRejectMessage(dwhMapper.findDwhKeyForSourceSystemAndKey("map_client_id",
                        "client_id",
                        SourceSystem.MESSAGE.value(),
                        String.valueOf(x.getClientId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_locality_id",
                                "locality_id",
                                SourceSystem.MESSAGE.value(),
                                String.valueOf(x.getLocalityId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_message_price_id",
                                "message_price_id",
                                SourceSystem.MESSAGE.value(),
                                String.valueOf(x.getMessagePriceId())),
                        dwhMapper.findDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                                "subscription_type_id",
                                SourceSystem.MESSAGE.value(),
                                String.valueOf(x.getSubscriptionTypeId())),
                        x,
                        message
                );

            }

        });
    }

    private void processMessagePrices() {
        messageMapper.getAllMessagePrices().forEach(x -> {
            MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_message_price_id",
                    "message_price_id",
                    SourceSystem.MESSAGE.value(),
                    String.valueOf(x.getMessagePriceId()));
            if (dwhKey == null) {
                long messagePriceSeq = dwhMapper.getNextKeyForTable("message_price_seq");
                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_message_price_id",
                        "message_price_id",
                        SourceSystem.MESSAGE.value(),
                        String.valueOf(x.getMessagePriceId()),
                        messagePriceSeq);
                MessagePrice messagePrice = MappingUtils.translateMessagePrice(x);
                messagePrice.setMessagePriceId(messagePriceSeq);
                dwhMapper.insertNewMessagePriceRow(messagePrice);
            } else {
                MessagePrice messagePriceRow = dwhMapper.findLastActiveMessagePriceRow(dwhKey.getDwhId());
                if (x.isDeleted() && messagePriceRow.getEndDate() == null) {
                    dwhMapper.closeMessagePriceRow(dwhKey.getDwhId());
                } else if (x.getLastActionTime().after(messagePriceRow.getStartDate())) {
                    dwhMapper.closeMessagePriceRow(dwhKey.getDwhId());
                    MessagePrice messagePrice = MappingUtils.translateMessagePrice(x);
                    messagePrice.setMessagePriceId(dwhKey.getDwhId());
                    dwhMapper.insertNewMessagePriceRow(messagePrice);
                }
            }
        });
    }

    private void processSubscriptionTypes() {
        CommonLoader.loadAllSubscriptionTypes(messageMapper, dwhMapper, SourceSystem.MESSAGE.value());
    }

    private void processLocalities() {
        CommonLoader.loadAllLocalities(messageMapper, dwhMapper, SourceSystem.MESSAGE.value());
    }

    private void processDistricts() {
        CommonLoader.loadAllDistricts(messageMapper, dwhMapper, SourceSystem.MESSAGE.value());
    }

    private void processProvinces() {
        CommonLoader.loadAllProvinces(messageMapper, dwhMapper, SourceSystem.MESSAGE.value());
    }

    private void processClients() {
        CommonLoader.loadAllClients(messageMapper, dwhMapper, SourceSystem.MESSAGE.value());
    }

    private void decideIfRejectMessage(
            MappingClass clientId,
            MappingClass localityId,
            MappingClass messagePricePriceId,
            MappingClass subscriptionTypeId,
            i5b5.wajaty.hd.projekt.model.sources.message.Message message,
            Message dwhMessage
    ) {
        boolean shouldReject = CommonLoader.checkRejected(clientId, localityId, subscriptionTypeId, dwhMessage);
        if (messagePricePriceId == null) {
            dwhMessage.setMessagePriceId(null);
            shouldReject = true;
        } else {
            dwhMessage.setMessagePriceId((int)messagePricePriceId.getDwhId());
        }

        if (shouldReject) {
            dwhMessage.setMessageId(null);
            dwhMapper.insertRejectedMessage(dwhMessage,
                    SourceSystem.MESSAGE.value(),
                    String.valueOf(message.getMessageId()));
        } else {
            final long key = dwhMapper.getNextKeyForTable("message_seq");
            dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_message_id",
                    "message_id",
                    SourceSystem.MESSAGE.value(),
                    String.valueOf(message.getMessageId()),
                    key);
            dwhMessage.setMessageId(key);
            dwhMapper.insertNewMessageRow(dwhMessage);
        }
    }

}
