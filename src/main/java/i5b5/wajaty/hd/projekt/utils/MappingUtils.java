package i5b5.wajaty.hd.projekt.utils;

import i5b5.wajaty.hd.projekt.model.dwh.*;
import org.dozer.DozerBeanMapper;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MappingUtils {
    private static final DozerBeanMapper dozer = new DozerBeanMapper();

    public static Client translateClient(i5b5.wajaty.hd.projekt.model.sources.entities.Client client){
        final Client tranlated = dozer.map(client, Client.class);
        if (client.getClientBirthdate() != null){
            tranlated.setClientAge((int)((Timestamp.valueOf(LocalDateTime.now()).getTime() - client.getClientBirthdate().getTime())/1000/60/60/24/31/12));
        }

        tranlated.setClientIncome(client.getClientMonthlyIncome());
        return tranlated;
    }

    public static SubscriptionType transalateSubscriptionType(i5b5.wajaty.hd.projekt.model.sources.entities.SubscriptionType subscriptionType){
        return dozer.map(subscriptionType, SubscriptionType.class);
    }

    public static Province translateProvince(i5b5.wajaty.hd.projekt.model.sources.localisation.Province province){
        return dozer.map(province, Province.class);
    }

    public static District translateDistrict(i5b5.wajaty.hd.projekt.model.sources.localisation.District district){
        return dozer.map(district, District.class);
    }

    public static Locality translateLocality(i5b5.wajaty.hd.projekt.model.sources.localisation.Locality locality){
        return dozer.map(locality, Locality.class);
    }

    public static Call translateCall(i5b5.wajaty.hd.projekt.model.sources.call.Call call){
        return dozer.map(call, Call.class);
    }

    public static CallPrice translateCallPrice(i5b5.wajaty.hd.projekt.model.sources.call.CallPrice callPrice){
        final CallPrice price = dozer.map(callPrice, CallPrice.class);
        price.setCallPricePerMin(price.getCallPricePerMin().setScale(2, RoundingMode.HALF_UP));

        return price;
    }

    public static Message translateMessage(i5b5.wajaty.hd.projekt.model.sources.message.Message message){
        return dozer.map(message, Message.class);
    }

    public static MessagePrice translateMessagePrice(i5b5.wajaty.hd.projekt.model.sources.message.MessagePrice messagePrice){
        final MessagePrice price = dozer.map(messagePrice, MessagePrice.class);
        price.setMessagePrice(messagePrice.getMessagePrice().setScale(2,RoundingMode.HALF_UP));

        return price;
    }

    public static NetworkSession translateNetworkSession(i5b5.wajaty.hd.projekt.model.sources.network.NetworkSession networkSession){
        final NetworkSession session = dozer.map(networkSession, NetworkSession.class);
        session.setNetworkSessionKbsAmount(networkSession.getNetworkSessionKbAmount());

        return session;
    }

    public static NetworkSessionPrice translateNetworkSessionPrice(i5b5.wajaty.hd.projekt.model.sources.network.NetworkSessionPrice networkSessionPrice){
        final NetworkSessionPrice price = dozer.map(networkSessionPrice, NetworkSessionPrice.class);
        price.setNetworkSessionPricePerKb(price.getNetworkSessionPricePerKb().setScale(2, RoundingMode.HALF_UP));

        return price;
    }

    public static TvChannel translateTvChannel(i5b5.wajaty.hd.projekt.model.sources.tv.TvChannel tvChannel){
        final TvChannel channel = dozer.map(tvChannel, TvChannel.class);
        channel.setTvChannelName(tvChannel.getName());

        return channel;
    }

    public static TvTransmission translateTvTransmission(i5b5.wajaty.hd.projekt.model.sources.tv.TvTransmission tvTransmission){
        final TvTransmission transmission = dozer.map(tvTransmission, TvTransmission.class);
        if (tvTransmission != null && tvTransmission.getTvTransmissionStartTime() != null && tvTransmission.getTvTransmissionEndTime() != null){
            transmission.setTvTransmissionLength((tvTransmission.getTvTransmissionEndTime().getTime() - tvTransmission.getTvTransmissionStartTime().getTime())/1000);
            transmission.setTvTransmissionDate(tvTransmission.getTvTransmissionStartTime());
        }

        return transmission;
    }
}
