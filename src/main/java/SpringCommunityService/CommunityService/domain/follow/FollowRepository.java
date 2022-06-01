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

    public List<User> findByUserIdJpa(User user){
        return em.createQuery("select u from User u where u in (select f.follow from Follow f where f.user = :id)",User.class)
                                    .setParameter("id",user)
                                    .getResultList();
    }

    public List<User> findExceptByUserIdJpa(User user){
        return em.createQuery("select u from User u where u <> :user and u not in (select f.follow from Follow f where f.user = :user)",User.class)
                .setParameter("user",user)
                .getResultList();
    }

    public Follow findOne(User user, User follow){
        return em.createQuery("select f from Follow f where f.follow = :id and f.user = :user",Follow.class)
                .setParameter("id",follow)
                .setParameter("user",user)
                .getSingleResult();
    }

    public void unfollow(Follow follow){
        em.remove(follow);
    }
}
