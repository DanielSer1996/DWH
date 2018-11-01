package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.sources.network.NetworkSession;
import i5b5.wajaty.hd.projekt.model.sources.network.NetworkSessionPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetworkMapper extends CommonMapper{
    List<NetworkSessionPrice> getAllNetworkSessionPrices();
    List<NetworkSession> getAllNetworkSessions();
}
