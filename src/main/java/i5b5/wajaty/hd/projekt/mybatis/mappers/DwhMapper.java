package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.dwh.*;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DwhMapper {
    MappingClass findDwhKeyForSourceSystemAndKey(@Param("tablename") String tablename,
                                                 @Param("columnName") String columnName,
                                                 @Param("sourceSystem") int sourceSystem,
                                                 @Param("sourceKey") String sourceKey);

    void insertNewDwhKeyForSourceSystemAndKey(@Param("tablename") String tablename,
                                              @Param("columnName") String columnName,
                                              @Param("sourceSystem") int sourceSystem,
                                              @Param("sourceKey") String sourceKey,
                                              @Param("newKey") long newKey);

    long getNextKeyForTable(@Param("sequenceName") String sequenceName);


    Client findLastActiveClientRow(@Param("dwhKey") long dwhKey);

    void closeClientRow(@Param("dwhKey") long dwhKey);

    void insertNewClientRow(@Param("client") Client client);

    Province findLastActiveProvinceRow(@Param("dwhKey") long dwhKey);

    void closeProvinceRow(@Param("dwhKey") long dwhKey);

    void insertNewProvinceRow(@Param("province") Province province);

    District findLastActiveDistrictRow(@Param("dwhKey") long dwhKey);

    void closeDistrictRow(@Param("dwhKey") long dwhKey);

    void insertNewDistrictRow(@Param("district") District district);

    Locality findLastActiveLocalityRow(@Param("dwhKey") long dwhKey);

    void closeLocalityRow(@Param("dwhKey") long dwhKey);

    void insertNewLocalityRow(@Param("locality") Locality locality);

    CallPrice findLastActiveCallPriceRow(@Param("dwhKey") long dwhKey);

    void closeCallPriceRow(@Param("dwhKey") long dwhKey);

    void insertNewCallPriceRow(@Param("callPrice") CallPrice callPrice);

    SubscriptionType findLastActiveSubscriptionTypeRow(@Param("dwhKey") long dwhKey);

    void closeSubscriptionTypeRow(@Param("dwhKey") long dwhKey);

    void insertNewSubscriptionTypeRow(@Param("subscriptionType") SubscriptionType subscriptionType);

    MessagePrice findLastActiveMessagePriceRow(@Param("dwhKey") long dwhKey);
    Message findLastActiveMessageRow(@Param("dwhKey") long dwhKey);

    void closeMessagePriceRow(@Param("dwhKey") long dwhKey);

    void closeMessageRow(@Param("dwhKey") long dwhKey);

    void insertNewMessagePriceRow(@Param("messagePrice") MessagePrice messagePrice);

    TvChannel findLastActiveTvChannelRow(@Param("dwhKey") long dwhKey);

    void closeTvChannelRow(@Param("dwhKey") long dwhKey);

    void insertNewTvChannelRow(@Param("tvChannel") TvChannel tvChannel);

    NetworkSessionPrice findLastActiveNetworkSessionPriceRow(@Param("dwhKey") long dwhKey);

    void closeNetworkSessionPriceRow(@Param("dwhKey") long dwhKey);

    void insertNewNetworkSessionPriceRow(@Param("networkSessionPrice") NetworkSessionPrice networkSessionPrice);


    void insertNewCallRow(@Param("call") Call call);

    void insertNewMessageRow(@Param("message") Message message);

    void insertNewTvTransmissionRow(@Param("tvTransmission") TvTransmission tvTransmission);

    void insertNewNetworkSessionRow(@Param("networkSession") NetworkSession networkSession);


    void insertRejectedDistrict(@Param("district") District district,
                                @Param("sourceSystem") int sourceSystem,
                                @Param("sourceKey") String sourceKey);

    void insertRejectedLocality(@Param("locality") Locality locality,
                                @Param("sourceSystem") int sourceSystem,
                                @Param("sourceKey") String sourceKey);

    void insertRejectedNetworkSession(@Param("networkSession") NetworkSession networkSession,
                                      @Param("sourceSystem") int sourceSystem,
                                      @Param("sourceKey") String sourceKey);

    void insertRejectedMessage(@Param("message") Message message,
                               @Param("sourceSystem") int sourceSystem,
                               @Param("sourceKey") String sourceKey);

    void insertRejectedCall(@Param("call") Call call,
                            @Param("sourceSystem") int sourceSystem,
                            @Param("sourceKey") String sourceKey);

    void insertRejectedTvTransmission(@Param("tvTransmission") TvTransmission tvTransmission,
                                      @Param("sourceSystem") int sourceSystem,
                                      @Param("sourceKey") String sourceKey);
}
