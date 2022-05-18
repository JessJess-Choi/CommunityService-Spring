package SpringCommunityService.CommunityService.web.follow;

import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/follow")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 팔로우 현황 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/follow")
    public String followPageJpa(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 팔로우 현황 접속",loginUser.getId(),loginUser.getLoginId());
        log.info("{} 팔로우 아이디 리스트 : {}",loginUser.getLoginId(),followService.findById(loginUser));
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followService.findById(loginUser));
        model.addAttribute("unFollow",findExceptIdJpa(loginUser));
        return "follow/follow";
    }

    @GetMapping("/unfollowToFollow/{followId}")
    public String unfollowToFollowJpa(@Login User loginUser, Model model, @PathVariable("followId") String followId){
        log.info("{} 유저가 {} 유저 팔로우 시작");
        followService.joinJpa(new Follow(loginUser.getLoginId(),userService.findOne(Long.parseLong(followId)).getLoginId(),loginUser));
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followService.findById(loginUser));
        model.addAttribute("unFollow",findExceptIdJpa(loginUser));
        return "redirect:/follow";
    }

    @GetMapping("/followToUnfollow/{followId}")
    public String followToUnfollowJpa(@Login User loginUser, Model model, @PathVariable("followId") String followId){
        log.info("{} 유저가 {} 유저 언팔로우",loginUser.getLoginId(),followId);
        followService.unfollowById(followService.findOne(loginUser, userService.findOne(Long.parseLong(followId)).getLoginId()));
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followService.findById(loginUser));
        model.addAttribute("unFollow",findExceptIdJpa(loginUser));
        return "redirect:/follow";
    }

    private List<User> findExceptIdJpa(User loginUser) {
        List<User> notFollow = new ArrayList<>();
        List<String> userFollows = followService.findByLoginId(loginUser.getLoginId());
        log.info("userfollow : {}",userFollows);
        userService.findUsers().forEach((user) -> {
            if(!userFollows.contains(user.getLoginId()) && !user.getLoginId().equals(loginUser.getLoginId())) {
                notFollow.add(user);
            }
        });
        return notFollow;
    }
}
