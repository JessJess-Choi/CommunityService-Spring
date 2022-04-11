package SpringCommunityService.CommunityService.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    private final String[] whiteList = {"/","/account","/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
