package SpringCommunityService.CommunityService.web.home;

import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import SpringCommunityService.CommunityService.web.user.EditUserForm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String userProfile(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 프로필 접속",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "users/userProfile";
    }

    @PostMapping("/profile")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 프로필 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/profile/edit")
    public String editPage(@ModelAttribute("editUserForm") EditUserForm editUserForm){
        return "users/editUserForm";
    }

    @PostMapping("/profile/edit")
    public String editUser(@Valid @ModelAttribute("editUserForm") EditUserForm editUserForm,
                           @Login User loginUser, Model model,
                           BindingResult bindingResult){
        if(loginUser.getName().length() > 30 || loginUser.getPassword().length() > 30
                || loginUser.getEmail().length() > 30 || loginUser.getLoginId().length() > 30){
            bindingResult.reject("수정 실패", "모든 회원 정보는 30자 이하");
        }
        userService.editUser(loginUser, editUserForm);
        loginUser.setPassword(editUserForm.getPassword());
        loginUser.setName(editUserForm.getName());
        model.addAttribute("user",userService.findOne(loginUser.getId()));
        return "redirect:/";
    }

    @GetMapping("/profile/drop")
    public String dropUser(@Login User loginUser){
        userService.dropUser(loginUser);
        return "redirect:/logout";
    }
}
