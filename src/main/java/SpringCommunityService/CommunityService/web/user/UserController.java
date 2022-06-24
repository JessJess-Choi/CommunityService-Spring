package SpringCommunityService.CommunityService.web.user;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/addUserForm";
        }

        try {
            userService.joinJpa(user);
        }catch(IllegalStateException e){
            bindingResult.reject("회원가입 실패","잘못된 id or email");
            return "users/addUserForm";
        }
        log.info("회원가입 진행 아이디:{}, 이름:{}, 이메일:{}",user.getLoginId(), user.getName(), user.getEmail());
        return "redirect:/";
    }
}
