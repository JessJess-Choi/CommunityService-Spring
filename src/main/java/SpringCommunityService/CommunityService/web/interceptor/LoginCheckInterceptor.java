package SpringCommunityService.CommunityService.web.interceptor;

import SpringCommunityService.CommunityService.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String reqURI = request.getRequestURI();
        HttpSession session = request.getSession();
        log.info("{} 인터셉터 인증 시작",reqURI);
        log.info("loginId : {}",request);

        if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
            log.info("미인증 사용자 요청 : {}",reqURI);
            log.info("{}, {}",session,session.getAttribute(SessionConst.LOGIN_USER));
            log.info("url : {}",reqURI);
            if(reqURI.contains("/api"))
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            else
                response.sendRedirect("/login?redirectURL=" + reqURI);
            return false;
        }
        return true;
    }
}
