package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.sources.tv.TvChannel;
import i5b5.wajaty.hd.projekt.model.sources.tv.TvTransmission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TvMapper extends CommonMapper{
    List<TvChannel> getAllTvChannels();
    List<TvTransmission> getAllTvTransmissions();
}
