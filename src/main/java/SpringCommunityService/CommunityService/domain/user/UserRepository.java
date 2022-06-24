package SpringCommunityService.CommunityService.domain.user;

import SpringCommunityService.CommunityService.web.user.EditUserForm;
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

    public void editUser(User loginUser, EditUserForm editUserForm) {
        User findUser = findOneJpa(loginUser.getId());
        findUser.setName(editUserForm.getName());
        findUser.setPassword(editUserForm.getPassword());
        em.merge(findUser);
    }
}