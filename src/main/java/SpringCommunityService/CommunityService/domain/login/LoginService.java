package SpringCommunityService.CommunityService.domain.login;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String userId, String password){
        log.info("요청 userID : {} password : {}",userId,password);
        return userRepository.findByLoginId(userId)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }
}
