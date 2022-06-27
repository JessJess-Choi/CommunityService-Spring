package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.request.RequestForLogin;
import SpringCommunityService.CommunityService.api.response.ResponseForLogin;
import SpringCommunityService.CommunityService.api.response.ResponseForLogout;
import SpringCommunityService.CommunityService.domain.login.LoginService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.SessionConst;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/login")
    private ResponseForLogin login(@RequestBody @Valid RequestForLogin requestForLogin,
                                   HttpServletRequest req){
        User loginUser = null;
        try {
            loginUser = loginService.loginJpa(requestForLogin.getLoginId(), requestForLogin.getPassword());
        }catch(NoResultException e) {
            return new ResponseForLogin(false);
        }
        if(loginUser == null){
            return new ResponseForLogin(false);
        }
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_USER,loginUser);

        return new ResponseForLogin(true);
    }

    @GetMapping("/api/logout")
    public ResponseForLogout logout1(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        else
            return new ResponseForLogout(false);
        return new ResponseForLogout(true);
    }

    @PostMapping("/api/logout")
    public ResponseForLogout logout2(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        else
            return new ResponseForLogout(false);
        return new ResponseForLogout(true);
    }

}
