package SpringCommunityService.CommunityService.domain.login;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Service
@Transactional(readOnly = true)
public class LoginService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User loginJpa(String userId, String password){
        return em.createQuery("select u from User u where u.loginId = :userId and u.password = :password",User.class)
                .setParameter("userId",userId)
                .setParameter("password",password)
                .getSingleResult();
    }
}
