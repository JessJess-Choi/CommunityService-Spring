package SpringCommunityService.CommunityService.domain.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class UserRepository {

    private final Map<Long, User> store = new HashMap<>();
    private long sequence = 0L;

    @PersistenceContext
    private EntityManager em;

    public User save(User user){
        user.setId(++sequence);
        store.put(user.getId(),user);
        return user;
    }

    public User find(Long id){
        return em.find(User.class,id);
    }

    public User findById(Long id){
        return store.get(id);
    }

    public Optional<User> findByLoginId(String loginId){
        return findAll().stream().filter(user -> user.getLoginId()
                .equals(loginId)).findFirst();
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

    //JPA 관련 함수들
    public User saveJpa(User user){
        em.persist(user);
        return user;
    }

    public User findOneJpa(Long id){
        return em.find(User.class,id);
    }

    public List<User> findAllJpa(){
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }

    public List<User> findByNameJpa(String name){
        return em.createQuery("select u from User u where u.name = :name",User.class)
                .setParameter("name",name)
                .getResultList();
    }
}