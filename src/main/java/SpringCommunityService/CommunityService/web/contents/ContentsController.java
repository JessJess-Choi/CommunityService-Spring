package SpringCommunityService.CommunityService.web.contents;

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
public class ContentsController {

    @GetMapping("/contents")
    public String userProfile(@Login User loginUser, Model model){
        //db에서 값 읽어와서 모델에 넣고 contents  렌더링 => contents/contents도 수정
        log.info("id : {}, loginId : {} 게시판 접속",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "contents/contents";
    }

    @PostMapping("/contents")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 게시판 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }
}
