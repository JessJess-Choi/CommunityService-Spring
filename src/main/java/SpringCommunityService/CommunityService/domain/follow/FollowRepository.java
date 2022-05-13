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

    private final Map<String, Object> store = new HashMap<>();

    @PersistenceContext
    private EntityManager em;

 /*   public List<String> save(Follow follow){
        List<String> followList = (ArrayList) store.get(follow.getUserId());
        if(followList == null)
            followList = new ArrayList<>();
        followList.add(follow.getFollowingId());
        store.put(follow.getUserId(), followList);
        return followList;

    }

  */

    public List<String> findById(String id){
        return (ArrayList) store.get(id);
    }

    public void unfollowById(String loginId, String unfollowId){
        List<String> followList = (ArrayList) store.get(loginId);
        followList.remove(unfollowId);
        store.put(loginId,followList);
    }

    public void followById(String loginId,String followId){
        List<String> followList = (ArrayList) store.get(loginId);
        if(followList == null){
            followList = new ArrayList<>();
        }
        followList.add(followId);
        store.put(loginId,followList);
    }

    public void clearStore(){
        store.clear();
    }


    //JPA 함수들
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
//        em.createQuery("delete from Follow f where f.user = :id and f.followingId = :followId",Follow.class)
  //              .setParameter("id",user)
    //            .setParameter("followId",followId);
    }
}
