package SpringCommunityService.CommunityService.api;

import SpringCommunityService.CommunityService.domain.login.LoginService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private ResponseForLogin login(@RequestBody @Valid RequestForLogin requestForLogin){
        try {
            loginService.loginJpa(requestForLogin.getLoginId(), requestForLogin.getPassword());
        }catch(NoResultException e) {
            return new ResponseForLogin(false);
        }
        return new ResponseForLogin(true);
    }

    @Data
    private static class ResponseForLogin {
        private boolean loginComplete;

        public ResponseForLogin(boolean loginComplete){
                this.loginComplete = loginComplete;
        }
    }

    @Data
    private static class RequestForLogin {
        private String loginId;
        private String password;
    }

}
