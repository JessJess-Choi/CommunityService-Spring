package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    public Message saveJpa(Message message){
        em.persist(message);
        return message;
    }

    public List<Message> findByUser(User loginUser, User receiverUser) {
        return em.createQuery("select m from Message m where (m.user = :user and m.receiver = :receiver) or (m.user = :receiver and m.receiver = :user) order by m.time",Message.class)
                .setParameter("user",loginUser)
                .setParameter("receiver",receiverUser)
                .getResultList();
    }

    public User findOne(String receiverId) {
        return em.createQuery("select u from User u where u.id = :receiverId",User.class)
                .setParameter("receiverId",Long.parseLong(receiverId))
                .getSingleResult();
    }
}
