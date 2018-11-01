package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.sources.entities.Client;
import i5b5.wajaty.hd.projekt.model.sources.entities.SubscriptionType;
import i5b5.wajaty.hd.projekt.model.sources.localisation.District;
import i5b5.wajaty.hd.projekt.model.sources.localisation.Locality;
import i5b5.wajaty.hd.projekt.model.sources.localisation.Province;

import java.util.List;

public interface CommonMapper {
    List<Province> getAllProvinces();
    List<District> getAllDistricts();
    List<Locality> getAllLocalities();
    List<Client> getAllClients();
    List<SubscriptionType> getAllSubscriptionTypes();
}
