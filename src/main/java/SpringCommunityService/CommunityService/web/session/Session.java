package SpringCommunityService.CommunityService.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Session {

    private String COOKIE_NAME = "cookie";
    private Map<String, Object> sessionStore = new HashMap<>();

    public void createSession(Object value, HttpServletResponse res){
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId,value);
        Cookie cookie = new Cookie(COOKIE_NAME,sessionId);
        res.addCookie(cookie);
    }

    public Object getSession(HttpServletRequest req){
        Cookie cookie = findCookie(req,COOKIE_NAME);
        if(cookie == null){
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest req, String cookieName){
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            return null;
        }
        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName))
                .findAny().orElse(null);
    }

    public void expire(HttpServletRequest req){
        Cookie cookie = findCookie(req,COOKIE_NAME);
        if(cookie != null){
            sessionStore.remove(cookie);
        }
    }
}
