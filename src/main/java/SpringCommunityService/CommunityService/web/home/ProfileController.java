package SpringCommunityService.CommunityService.web.home;

import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.follow.FollowRepository;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @GetMapping("/profile")
    public String userProfile(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 프로필 접속",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "users/userProfile";
    }

    @PostMapping("/profile")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 프로필 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

}