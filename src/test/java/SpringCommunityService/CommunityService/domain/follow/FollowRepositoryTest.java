package SpringCommunityService.CommunityService.domain.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FollowRepositoryTest {

    FollowRepository followRepository = new FollowRepository();

    @AfterEach
    void clear(){
        followRepository.clearStore();
    }

    @Test
    void saveTest(){
      /*  Follow follow = new Follow("1","2");
        List<String> saveFollow = followRepository.save(follow);
        List<String> findFollow = followRepository.findById(follow.getUserId());

        assertThat(saveFollow).isEqualTo(findFollow);
        assertThat(saveFollow.size()).isEqualTo(1);
        assertThat(followRepository.findById("2")).isNull();

       */
    }
}