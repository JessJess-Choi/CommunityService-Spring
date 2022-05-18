package SpringCommunityService.CommunityService.domain.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Repository
public class FollowRepository {

    @PersistenceContext
    private EntityManager em;

    public Follow saveJpa(Follow follow){
        em.persist(follow);
        return follow;
    }

    public List<User> findByIdJpa(User user){
        return em.createQuery("select u from User u where u.loginId in (select f.followingId from Follow f where f.user = :id)",User.class)
                                    .setParameter("id",user)
                                    .getResultList();
    }

    public List<Follow> findByLoginIdJpa(String id){
        return em.createQuery("select f from Follow f where f.userId = :id",Follow.class)
                .setParameter("id",id)
                .getResultList();
    }

    public Follow findOne(User user, String followId){
        return em.createQuery("select f from Follow f where f.followingId = :id and f.user = :user",Follow.class)
                .setParameter("id",followId)
                .setParameter("user",user)
                .getSingleResult();
    }

    public void unfollow(Follow follow){
        em.remove(follow);
    }
}
