package SpringCommunityService.CommunityService.domain.follow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class FollowRepository {

    private final Map<String, Object> store = new HashMap<>();

    public List<String> save(Follow follow){
        List<String> followList = (ArrayList) store.get(follow.getUserId());
        if(followList == null)
            followList = new ArrayList<>();
        followList.add(follow.getFollowId());
        store.put(follow.getUserId(), followList);
        return followList;
    }

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
        followList.add(followId);
        store.put(loginId,followList);
    }

    public void clearStore(){
        store.clear();
    }

}
