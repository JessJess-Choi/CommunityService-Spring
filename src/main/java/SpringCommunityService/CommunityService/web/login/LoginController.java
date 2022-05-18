package SpringCommunityService.CommunityService.web.login;

import SpringCommunityService.CommunityService.domain.login.LoginService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.SessionConst;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginCheck(@Valid @ModelAttribute LoginForm loginForm,
                             BindingResult bindingResult, HttpServletRequest req,
                             @RequestParam(defaultValue = "/") String redirectURL){
        log.info("로그인 요청 id : {} password : {}",loginForm.getUserId(),loginForm.getPassword());
        if(bindingResult.hasErrors()){
            log.info("error input");
            return "login/loginForm";
        }

        User loginUser = null;
        try {
            loginUser = loginService.loginJpa(loginForm.getUserId(), loginForm.getPassword());
        }catch(NoResultException e){
            bindingResult.reject("loginFail","잘못된 id or pw");
            return "login/loginForm";
        }
        if(loginUser == null){
            log.info("잘못된 로그인 id : {}, pw : {}",loginForm.getUserId(),loginForm.getPassword());
            bindingResult.reject("loginFail","잘못된 id or pw");
            return "login/loginForm";
        }

        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_USER,loginUser);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req, @Login User loginUser){
        log.info("{}, {} 로그아웃",loginUser.getId(),loginUser.getLoginId());
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
