package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Long joinJpa(Message message){
        messageRepository.saveJpa(message);
        return message.getId();
    }

    public List<Message> findByUser(User loginUser, String receiverId) {
        return messageRepository.findByUser(loginUser,messageRepository.findOne(receiverId));
    }

    public User findOne(String id) {
        return messageRepository.findOne(id);
    }
}
