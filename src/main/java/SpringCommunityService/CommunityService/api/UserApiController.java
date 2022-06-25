package SpringCommunityService.CommunityService.api;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/add")
    public ResponseForAdd addUser(@RequestBody @Valid RequestForAdd requestForAdd){
        User user = new User(requestForAdd.getLoginId(), requestForAdd.getPassword(), requestForAdd.getEmail(), requestForAdd.getName());
        userService.joinJpa(user);
        return new ResponseForAdd(user);
    }

    @Data
    private static class RequestForAdd{
        private String loginId;
        private String password;
        private String email;
        private String name;
    }

    @Data
    private static class ResponseForAdd{
        private String loginId;
        private String name;

        public ResponseForAdd(User user){
            this.loginId = user.getLoginId();
            this.name = user.getName();
        }
    }
}
