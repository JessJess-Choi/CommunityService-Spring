package SpringCommunityService.CommunityService.web.argumentresolver;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("LoginUserArgumentResolver supportsParameter 실행");

        return parameter.hasParameterAnnotation(Login.class)
                && User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("LoginUserArgumentResolver resolveArgument 실행");

        HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
        HttpSession session = req.getSession(false);

        if(session == null){
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_USER);
    }
}
