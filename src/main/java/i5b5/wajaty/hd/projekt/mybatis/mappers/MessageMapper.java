package i5b5.wajaty.hd.projekt.mybatis.mappers;

import i5b5.wajaty.hd.projekt.model.sources.message.Message;
import i5b5.wajaty.hd.projekt.model.sources.message.MessagePrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends CommonMapper{
    List<MessagePrice> getAllMessagePrices();
    List<Message> getAllMessages();
}
