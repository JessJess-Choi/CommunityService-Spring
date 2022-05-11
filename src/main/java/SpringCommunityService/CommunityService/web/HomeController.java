package SpringCommunityService.CommunityService.web;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLoginArgumentResolver(@Login User loginUser, Model model){
        log.info("/ 접속");
        if(loginUser == null){
            return "home";
        }
        model.addAttribute("user",loginUser);
        return "loginHome";
    }

    @GetMapping("/ssu_stagram.jpg")
    public String homeLoginArgumentResolver1(@Login User loginUser, Model model){
        log.info("/ssustagram.jpg 연결됨");
        if(loginUser == null){
            return "home";
        }
        model.addAttribute("user",loginUser);
        return "loginHome";
    }
}
