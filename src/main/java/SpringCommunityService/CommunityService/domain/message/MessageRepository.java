package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MessageRepository {

    private final Map<Long, Message> store = new HashMap<>();
    private long sequence = 0L;

    public Message save(Message message){
        message.setId(++sequence);
        store.put(sequence,message);
        return message;
    }

    public List<Message> findByLoginId(String senderId, String receiverId){
        List<Message> messageLog = new ArrayList<>();
        store.forEach((key,value) -> {
            if(findMessage(value,senderId,receiverId)){
                messageLog.add(value);
            }
        });
        log.info("{}",messageLog);
        return messageLog;
    }

    private boolean findMessage(Message message, String senderId, String receiverId){
        return (message.getSenderId().equals(senderId) && message.getReceiverId().equals(receiverId))
                || (message.getSenderId().equals(receiverId) && message.getReceiverId().equals(senderId));
    }

    public Message findById(Long id){
        return store.get(id);
    }

    public void clearStore(){
        store.clear();
    }
}
