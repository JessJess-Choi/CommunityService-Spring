package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.ProfileDto;
import SpringCommunityService.CommunityService.api.request.RequestForEditProfile;
import SpringCommunityService.CommunityService.api.response.ResponseForEditProfile;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import SpringCommunityService.CommunityService.web.user.EditUserForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ProfileApiController {

    private final UserService userService;

    @GetMapping("/api/profile")
    public ProfileDto userProfile(@Login User loginUser){
        return new ProfileDto(loginUser);
    }

    @PostMapping("/api/profile/edit")
    public ResponseForEditProfile editUserProfile(@Login User loginUser,
                                                  @RequestBody @Valid RequestForEditProfile requestForEditProfile){
        userService.editUser(loginUser,new EditUserForm(requestForEditProfile.getPassword(), requestForEditProfile.getName()));
        loginUser.setName(requestForEditProfile.getName());
        loginUser.setPassword(requestForEditProfile.getPassword());
        return new ResponseForEditProfile(true);
    }
}
