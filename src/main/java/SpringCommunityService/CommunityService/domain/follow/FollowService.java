package SpringCommunityService.CommunityService.domain.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<User> findByUser(User user){
        return followRepository.findByUserIdJpa(user);
    }

    public List<User> findExceptByUser(User user){
        return followRepository.findExceptByUserIdJpa(user);
    }

    @Transactional
    public void unfollowById(Follow follow){
        followRepository.unfollow(follow);
    }

    public Follow findOne(User user, User follow) {
        return followRepository.findOne(user,follow);
    }

    @Transactional
    public void removeAllByUser(User loginUser) {
        followRepository.findAllByUser(loginUser).forEach(f -> followRepository.unfollow(f));
    }
}
