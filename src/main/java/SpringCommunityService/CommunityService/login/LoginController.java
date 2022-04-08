package SpringCommunityService.CommunityService.login;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public String loginCheck(@Valid @ModelAttribute LoginForm loginForm,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("error input");
            return "redirect:/";
        }
        log.info("로그인 요청 id : {} password : {}",loginForm.getUserId(),loginForm.getPassword());
        User user = userRepository.findById(Long.parseLong(loginForm.getUserId()));
        if(user != null)
            return "home";
        return "redirect:/";
    }
}
