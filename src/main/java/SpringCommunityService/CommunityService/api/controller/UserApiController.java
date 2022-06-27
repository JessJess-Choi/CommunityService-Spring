package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.request.RequestForAdd;
import SpringCommunityService.CommunityService.api.response.ResponseForAdd;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/users/add")
    public ResponseForAdd addUser(@RequestBody @Valid RequestForAdd requestForAdd){
        User user = new User(requestForAdd.getLoginId(), requestForAdd.getPassword(), requestForAdd.getEmail(), requestForAdd.getName());
        try {
            userService.joinJpa(user);
        }catch (IllegalStateException e){
            return new ResponseForAdd(false);
        }
        return new ResponseForAdd(true);
    }
}
