package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.sources.call.Call;
import i5b5.wajaty.hd.projekt.model.sources.call.CallPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CallMapper extends CommonMapper {
    List<CallPrice> getAllCallPrices();
    List<Call> getAllCalls();
}
