package SpringCommunityService.CommunityService.domain.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

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

    public List<User> findOneByNameJpa(String userName){
        return em.createQuery("select u from User u where u.name like concat('%',:name,'%')",User.class)
                .setParameter("name",userName)
                .getResultList();
    }

    public List<User> findByLoginIdJpa(String loginId){
        return em.createQuery("select u from User u where u.loginId = :loginId",User.class)
                .setParameter("loginId",loginId)
                .getResultList();
    }

    public List<User> findByEmailJpa(String email){
        return em.createQuery("select u from User u where u.email = :email",User.class)
                .setParameter("email",email)
                .getResultList();
    }
}