package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MessageRepository {

    private final Map<Long, Message> store = new HashMap<>();
    private long sequence = 0L;

    @PersistenceContext
    private EntityManager em;
/*
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


 */
    //JPA 함수들
    public Message saveJpa(Message message){
        em.persist(message);
        return message;
    }

    public List<Message> findByUser(User loginUser, User receiverUser) {
        em.createQuery("select m from Message m where m.user = :user and m.receiverId = :receiverId",Message.class)
                .setParameter("user",loginUser)
                .setParameter("receiverId",receiverUser.getLoginId())
                .getResultList()
                .forEach((m) -> log.info("first : {}",m.getMessage()));
        em.createQuery("select m from Message m where m.user = :receiver and m.receiverId = :userLoginId",Message.class)
                .setParameter("receiver",receiverUser)
                .setParameter("userLoginId",loginUser.getLoginId())
                .getResultList()
                .forEach((m) -> log.info("second : {}",m.getMessage()));

        em.createQuery("select m from Message m where (m.user = :user and m.receiverId = :receiverId) or (m.user = :receiver and m.receiverId = :userLoginId)",Message.class)
                .setParameter("user",loginUser)
                .setParameter("receiverId",receiverUser.getLoginId())
                .setParameter("receiver",receiverUser)
                .setParameter("userLoginId",loginUser.getLoginId())
                .getResultList()
                .forEach((m) -> log.info("third : {}",m.getMessage()));

        return em.createQuery("select m from Message m where (m.user = :user and m.receiverId = :receiverId) or (m.user = :receiver and m.receiverId = :userLoginId)",Message.class)
                .setParameter("user",loginUser)
                .setParameter("receiverId",receiverUser.getLoginId())
                .setParameter("receiver",receiverUser)
                .setParameter("userLoginId",loginUser.getLoginId())
                .getResultList();
    }

    public String findUserId(User user) {
        return em.createQuery("select u from User u where u.id = :id",User.class)
                .setParameter("id",user.getId())
                .getSingleResult()
                .getLoginId();
    }

    public User findOne(String receiverId) {
        return em.createQuery("select u from User u where u.id = :receiverId",User.class)
                .setParameter("receiverId",Long.parseLong(receiverId))
                .getSingleResult();
    }
}
