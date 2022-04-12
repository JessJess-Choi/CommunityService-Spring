package SpringCommunityService.CommunityService.web.filter;

import SpringCommunityService.CommunityService.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private final String[] whiteList = {"/","/account","/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String reqURI = req.getRequestURI();

        try{
            log.info("URL : {} 로그인 체크 필터 시작",reqURI);

            if(loginPathCheck(reqURI)){
                log.info("로그인 체크 로직 시작 {}",reqURI);
                HttpSession session = req.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
                    log.info("미인증 사용자 요청 {}", reqURI);
                    res.sendRedirect("/login?redirectURL="+reqURI);
                    return;
                }
            }
            chain.doFilter(request,response);
        }catch (Exception e){
            log.info("로그인 필터 {} 에러 발생",e.getMessage());
            throw e;
        }finally {
            log.info("로그인 필터 종료");
        }
    }

    private boolean loginPathCheck(String reqURI){
        return !PatternMatchUtils.simpleMatch(whiteList,reqURI);
    }
}
