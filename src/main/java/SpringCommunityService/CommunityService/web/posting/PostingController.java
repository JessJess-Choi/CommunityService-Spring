package SpringCommunityService.CommunityService.web.posting;

import SpringCommunityService.CommunityService.domain.posting.PostingRepository;
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
public class PostingController {

    private final PostingRepository postingRepository;

    @GetMapping("/posting")
    public String userPosting(@Login User loginUser, Model model){
        //db에서 값 읽어와서 모델에 넣고 contents  렌더링 => contents/contents도 수정
        log.info("id : {}, loginId : {} 게시판 접속",loginUser.getId(),loginUser.getUserId());
        log.info("{} posting :  {}",loginUser.getUserId(),postingRepository.findById(1L));
        model.addAttribute("user",loginUser);
        model.addAttribute("posting",postingRepository.findAll());
        return "posting/posting";
    }

    @PostMapping("/posting")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 게시판 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }
}
