package i5b5.wajaty.hd.projekt.loading.commons;

import i5b5.wajaty.hd.projekt.model.dwh.*;
import i5b5.wajaty.hd.projekt.model.dwh.mappers.MappingClass;
import i5b5.wajaty.hd.projekt.mybatis.mappers.CommonMapper;
import i5b5.wajaty.hd.projekt.mybatis.mappers.DwhMapper;
import i5b5.wajaty.hd.projekt.utils.MappingUtils;


public class CommonLoader {
    public static <T extends CommonMapper> void loadAllClients(T sourceMapper, DwhMapper dwhMapper, int sourceSystemNo) {
        System.out.println("Starting loading clients from system " + sourceSystemNo);
        sourceMapper.getAllClients().forEach(client -> {
            System.out.println("Loading client with id " + client.getClientId());
            MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_client_id",
                    "client_id",
                    sourceSystemNo,
                    String.valueOf(client.getClientId()));

            if (dwhKey == null) {
                final long keyForTable = dwhMapper.getNextKeyForTable("client_seq");
                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_client_id",
                        "client_id",
                        sourceSystemNo,
                        String.valueOf(client.getClientId()),
                        keyForTable);

                Client translatedCliennt = MappingUtils.translateClient(client);
                translatedCliennt.setClientId(keyForTable);

                dwhMapper.insertNewClientRow(translatedCliennt);
            } else {
                final Client clientRow = dwhMapper.findLastActiveClientRow(dwhKey.getDwhId());
                if (client.isDeleted() && clientRow.getEndDate() == null) {
                    dwhMapper.closeClientRow(clientRow.getClientId());
                } else if (client.getLastActionTime().after(clientRow.getStartDate())) {
                    dwhMapper.closeClientRow(clientRow.getClientId());
                    final Client translateClient = MappingUtils.translateClient(client);

                    translateClient.setClientId(dwhKey.getDwhId());
                    dwhMapper.insertNewClientRow(translateClient);
                }
            }
        });

    }

    public static <T extends CommonMapper> void loadAllSubscriptionTypes(T sourceMapper, DwhMapper dwhMapper, int sourceSystemNo) {
        System.out.println("Starting loading subscription types from system " + sourceSystemNo);
        sourceMapper.getAllSubscriptionTypes().forEach(subscriptionType -> {
            System.out.println("Loading subscription type with id " + subscriptionType.getSubscriptionTypeId());
            MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                    "subscription_type_id",
                    sourceSystemNo,
                    String.valueOf(subscriptionType.getSubscriptionTypeId()));

            if (dwhKey == null) {
                final long key = dwhMapper.getNextKeyForTable("subscription_type_seq");
                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_subscription_type_id",
                        "subscription_type_id",
                        sourceSystemNo,
                        String.valueOf(subscriptionType.getSubscriptionTypeId()),
                        key);

                final SubscriptionType transalateSubscriptionType = MappingUtils.transalateSubscriptionType(subscriptionType);
                transalateSubscriptionType.setSubscriptionTypeId((int) key);

                dwhMapper.insertNewSubscriptionTypeRow(transalateSubscriptionType);
            } else {
                final SubscriptionType subscriptionTypeRow = dwhMapper.findLastActiveSubscriptionTypeRow(dwhKey.getDwhId());

                if (subscriptionType.isDeleted() && subscriptionTypeRow.getEndDate() == null) {
                    dwhMapper.closeSubscriptionTypeRow(subscriptionTypeRow.getSubscriptionTypeId());
                } else if (subscriptionType.getLastActionTime().after(subscriptionTypeRow.getStartDate())) {
                    dwhMapper.closeSubscriptionTypeRow(subscriptionTypeRow.getSubscriptionTypeId());

                    final SubscriptionType transalateSubscriptionType = MappingUtils.transalateSubscriptionType(subscriptionType);
                    transalateSubscriptionType.setSubscriptionTypeId((int) dwhKey.getDwhId());

                    dwhMapper.insertNewSubscriptionTypeRow(transalateSubscriptionType);
                }
            }
        });
    }

    public static <T extends CommonMapper> void loadAllProvinces(T sourceMapper, DwhMapper dwhMapper, int sourceSystemNo) {
        System.out.println("Starting loading provinces from system " + sourceSystemNo);
        sourceMapper.getAllProvinces().forEach(province -> {
            System.out.println("Loading province with name " + province.getProvinceName());
            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_province_id",
                    "province_id",
                    sourceSystemNo,
                    String.valueOf(province.getProvinceId()));

            if (dwhKey == null) {
                final long key = dwhMapper.getNextKeyForTable("province_seq");

                dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_province_id",
                        "province_id",
                        sourceSystemNo,
                        String.valueOf(province.getProvinceId()),
                        key);

                final Province translateProvince = MappingUtils.translateProvince(province);
                translateProvince.setProvinceId((int) key);

                dwhMapper.insertNewProvinceRow(translateProvince);
            } else {
                final Province provinceRow = dwhMapper.findLastActiveProvinceRow(dwhKey.getDwhId());
                if (province.isDeleted() && provinceRow.getEndDate() == null) {
                    dwhMapper.closeProvinceRow(provinceRow.getProvinceId());
                } else if (province.getLastActionTime().after(provinceRow.getStartDate())) {
                    dwhMapper.closeProvinceRow(provinceRow.getProvinceId());

                    final Province translateProvince = MappingUtils.translateProvince(province);
                    translateProvince.setProvinceId((int) dwhKey.getDwhId());

                    dwhMapper.insertNewProvinceRow(provinceRow);
                }
            }
        });
    }

    public static <T extends CommonMapper> void loadAllDistricts(T sourceMapper, DwhMapper dwhMapper, int sourceSystemNo) {
        System.out.println("Starting loading districts from system " + sourceSystemNo);
        sourceMapper.getAllDistricts().forEach(district -> {
            System.out.println("Loading district with name " + district.getDistrictName());
            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_district_id",
                    "district_id",
                    sourceSystemNo,
                    String.valueOf(district.getDistrictId()));

            if (dwhKey == null) {
                final MappingClass provinceId = dwhMapper.findDwhKeyForSourceSystemAndKey("map_province_id",
                        "province_id",
                        sourceSystemNo,
                        String.valueOf(district.getProvinceId()));

                final District translateDistrict = MappingUtils.translateDistrict(district);

                if (provinceId != null) {
                    final long key = dwhMapper.getNextKeyForTable("district_seq");

                    dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_district_id",
                            "district_id",
                            sourceSystemNo,
                            String.valueOf(district.getDistrictId()),
                            key);

                    translateDistrict.setDistrictId((int) key);
                    translateDistrict.setProvinceId((int) provinceId.getDwhId());
                    dwhMapper.insertNewDistrictRow(translateDistrict);
                } else {
                    translateDistrict.setProvinceId(null);
                    translateDistrict.setDistrictId(null);
                    dwhMapper.insertRejectedDistrict(translateDistrict,
                            sourceSystemNo,
                            String.valueOf(district.getDistrictId()));
                }
            } else {
                final District districtRow = dwhMapper.findLastActiveDistrictRow(dwhKey.getDwhId());
                if (district.isDeleted() && districtRow.getEndDate() == null) {
                    dwhMapper.closeDistrictRow(districtRow.getDistrictId());
                } else if (district.getLastActionTime().after(districtRow.getStartDate())) {

                    final District trDistrict = MappingUtils.translateDistrict(district);
                    trDistrict.setDistrictId((int) dwhKey.getDwhId());

                    final MappingClass provinceId = dwhMapper.findDwhKeyForSourceSystemAndKey("map_province_id",
                            "province_id",
                            sourceSystemNo,
                            String.valueOf(district.getProvinceId()));

                    if (provinceId != null) {
                        dwhMapper.closeDistrictRow(districtRow.getDistrictId());

                        trDistrict.setProvinceId((int) provinceId.getDwhId());
                        dwhMapper.insertNewDistrictRow(trDistrict);
                    } else {
                        trDistrict.setProvinceId(null);
                        dwhMapper.insertRejectedDistrict(trDistrict,
                                sourceSystemNo,
                                String.valueOf(district.getDistrictId()));
                    }
                }
            }
        });
    }

    public static <T extends CommonMapper> void loadAllLocalities(T sourceMapper, DwhMapper dwhMapper, int sourceSystemNo) {
        System.out.println("Starting loading localities from system " + sourceSystemNo);
        sourceMapper.getAllLocalities().forEach(locality -> {
            System.out.println("Loading locality with name " + locality.getLocalityName());
            final MappingClass dwhKey = dwhMapper.findDwhKeyForSourceSystemAndKey("map_locality_id",
                    "locality_id",
                    sourceSystemNo,
                    String.valueOf(locality.getLocalityId()));
            if (dwhKey == null) {
                final MappingClass districtId = dwhMapper.findDwhKeyForSourceSystemAndKey("map_district_id",
                        "district_id",
                        sourceSystemNo,
                        String.valueOf(locality.getDistrictId()));
                final Locality translateLocality = MappingUtils.translateLocality(locality);
                if (districtId != null){
                    final long key = dwhMapper.getNextKeyForTable("locality_seq");
                    dwhMapper.insertNewDwhKeyForSourceSystemAndKey("map_locality_id",
                            "locality_id",
                            sourceSystemNo,
                            String.valueOf(locality.getLocalityId()),
                            key);
                    translateLocality.setLocalityId((int) key);
                    translateLocality.setDistrictId((int) districtId.getDwhId());

                    dwhMapper.insertNewLocalityRow(translateLocality);
                }else {
                    translateLocality.setDistrictId(null);
                    translateLocality.setLocalityId(null);

                    dwhMapper.insertRejectedLocality(translateLocality,
                            sourceSystemNo,
                            String.valueOf(locality.getLocalityId()));
                }
            } else {
                final Locality localityRow = dwhMapper.findLastActiveLocalityRow(dwhKey.getDwhId());
                if (locality.isDeleted() && localityRow.getEndDate() == null) {
                    dwhMapper.closeLocalityRow(localityRow.getLocalityId());
                } else if (locality.getLastActionTime().after(localityRow.getStartDate())) {

                    final Locality translateLocality = MappingUtils.translateLocality(locality);
                    translateLocality.setLocalityId((int) dwhKey.getDwhId());

                    final MappingClass districtId = dwhMapper.findDwhKeyForSourceSystemAndKey("map_district_id",
                            "district_id",
                            sourceSystemNo,
                            String.valueOf(locality.getDistrictId()));

                    if (districtId != null){
                        dwhMapper.closeLocalityRow(localityRow.getLocalityId());

                        translateLocality.setDistrictId((int)districtId.getDwhId());

                        dwhMapper.insertNewLocalityRow(translateLocality);
                    }else {
                        translateLocality.setDistrictId(null);
                        dwhMapper.insertRejectedLocality(translateLocality,
                                sourceSystemNo,
                                String.valueOf(locality.getLocalityId()));
                    }

                }
            }
        });
    }

    public static boolean checkRejected(
            MappingClass clientId,
            MappingClass localityId,
            MappingClass subscriptionTypeId,
            FactEntity dwh) {
        boolean shouldReject = false;
        if (clientId == null) {
            dwh.setClientId(null);
            shouldReject = true;
        } else {
            dwh.setClientId(clientId.getDwhId());
        }
        if (localityId == null) {
            dwh.setLocalityId(null);
            shouldReject = true;
        } else {
            dwh.setLocalityId((int) localityId.getDwhId());
        }
        if (subscriptionTypeId == null) {
            dwh.setSubscriptionTypeId(null);
            shouldReject = true;
        } else {
            dwh.setSubscriptionTypeId((int) subscriptionTypeId.getDwhId());
        }
        return shouldReject;
    }
}
