package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.ProfileDto;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProfileApiController {

    private final UserService userService;

    @GetMapping("/api/profile")
    public ProfileDto userProfile(@Login User loginUser){
        return new ProfileDto(loginUser);
    }
}
