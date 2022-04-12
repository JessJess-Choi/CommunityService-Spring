package SpringCommunityService.CommunityService;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user1 = new User();
        user1.setUserId("1");
        user1.setPassword("1");
        user1.setEmail("1@naver.com");
        user1.setName("testName1");
        User user2 = new User();
        user2.setUserId("2");
        user2.setPassword("2");
        user2.setEmail("2@naver.com");
        user2.setName("testName2");
        User user3 = new User();
        user3.setUserId("3");
        user3.setPassword("3");
        user3.setEmail("3@naver.com");
        user3.setName("testName3");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
