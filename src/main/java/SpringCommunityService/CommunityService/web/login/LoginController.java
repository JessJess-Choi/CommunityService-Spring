package SpringCommunityService.CommunityService.web.login;

import SpringCommunityService.CommunityService.domain.login.LoginService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.SessionConst;
import SpringCommunityService.CommunityService.web.session.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final Session session;

    @PostMapping("/login")
    public String loginCheck(@Valid @ModelAttribute LoginForm loginForm,
                             BindingResult bindingResult, HttpServletRequest req,
                             Model model){
        log.info("로그인 요청 id : {} password : {}",loginForm.getUserId(),loginForm.getPassword());
        if(bindingResult.hasErrors()){
            log.info("error input");
            return "redirect:/";
        }

        User loginUser = loginService.login(loginForm.getUserId(),loginForm.getPassword());
        if(loginUser == null){
            log.info("잘못된 로그인 id : {}, pw : {}",loginForm.getUserId(),loginForm.getPassword());
            bindingResult.reject("loginFail","잘못된 id or pw");
            return "redirect:/";
        }

        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_USER,loginUser);
        model.addAttribute("user",loginUser);

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
