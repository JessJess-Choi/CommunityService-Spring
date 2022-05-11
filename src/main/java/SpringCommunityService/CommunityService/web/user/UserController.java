package SpringCommunityService.CommunityService.web.user;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import SpringCommunityService.CommunityService.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

//    private final UserRepository userRepository;
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
 /*       if(userRepository.findByLoginId(user.getLoginId()).isPresent()){
            return "users/addUserForm";
        }
        userRepository.save(user);

  */
        try {
            userService.joinJpa(user);
        }catch(IllegalStateException e){
            bindingResult.reject("회원가입 실패","잘못된 id or email");
            return "users/addUserForm";
        }
        return "redirect:/";
    }
}
