package SpringCommunityService.CommunityService.web.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FollowController {

    @GetMapping("/follow")
    public String userProfile(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 팔로우 현황 접속",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "follow/follow";
    }

    @PostMapping("/follow")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 팔로우 현황 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }
}
