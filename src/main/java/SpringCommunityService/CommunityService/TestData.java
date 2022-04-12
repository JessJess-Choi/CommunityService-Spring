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
        User user = new User();
        user.setUserId("1");
        user.setPassword("1");
        user.setEmail("1@naver.com");
        userRepository.save(user);
    }
}
