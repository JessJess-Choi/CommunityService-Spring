package SpringCommunityService.CommunityService.domain.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository){
        this.followRepository = followRepository;
    }

    @Transactional
    public Long joinJpa(Follow follow){
        followRepository.saveJpa(follow);
        return follow.getId();
    }

    public List<User> findById(User user){
        return followRepository.findByIdJpa(user);
    }

    public List<String> findByLoginId(String id){
        List<Follow> following = followRepository.findByLoginIdJpa(id);
        List<String> followString = new ArrayList<>();
        following.forEach((follow -> {
            followString.add(follow.getFollowingId());
        }));
        return followString;
    }

    @Transactional
    public void unfollowById(Follow follow){
        followRepository.unfollow(follow);
    }

    public Follow findOne(User user, String followId) {
        return followRepository.findOne(user,followId);
    }
}
