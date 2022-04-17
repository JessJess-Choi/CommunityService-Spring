package SpringCommunityService.CommunityService.web.posting;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingRepository;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {

    private final PostingRepository postingRepository;

    @GetMapping("/posting")
    public String userPosting(@Login User loginUser, Model model,
                              @ModelAttribute("postingForm") PostingForm postingForm){
        //db에서 값 읽어와서 모델에 넣고 contents  렌더링 => contents/contents도 수정
        log.info("id : {}, loginId : {} 게시판 접속",loginUser.getId(),loginUser.getUserId());
        log.info("{} posting :  {}",loginUser.getUserId(),postingRepository.findById(1L));

        List<Posting> allPosts = postingRepository.findAll();
        Collections.sort(allPosts, (o1,o2) -> o1.getTime().compareTo(o2.getTime()));
        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allPosts);
        return "posting/posting";
    }

    @PostMapping("/posting")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 게시판 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/posting/add")
    public String goPostingForm(@ModelAttribute("postingForm") PostingForm postingForm){
        return "posting/postingForm";
    }

    @PostMapping("/posting/add")
    public String addContent(@Valid @ModelAttribute PostingForm postingForm,
                                       @Login User loginUser){
        log.info("newContent Mapping");
        Posting posting = new Posting(loginUser.getUserId(),postingForm.getContent(), "임시 파일 ID", LocalTime.now());
        postingRepository.save(posting);
        log.info("{}",postingForm.getContent());
        return "redirect:/posting";
    }

    @GetMapping("/posting/edit/{postingId}")
    public String editPosting(@PathVariable("postingId") String postingId, Model model,
                              @ModelAttribute("postingForm") PostingForm postingForm){
        Posting posting = postingRepository.findById(Long.parseLong(postingId));
        model.addAttribute("posting",posting);
        log.info("editPosting : {}",posting);
        return "posting/edit";
    }

    @PostMapping("/posting/edit/{postingId}")
    public String edit(@PathVariable("postingId") String postingId,
                       @Login User user,
                       @ModelAttribute("postingForm") PostingForm postingForm){
        Posting posting = new Posting(user.getUserId(),postingForm.getContent(),"fileId",LocalTime.now());
        postingRepository.set(Long.parseLong(postingId),posting);
        return "redirect:/posting";
    }

    @PostMapping("/posting/search/writer")
    public String searchWriter(@Login User loginUser, Model model,
                         @ModelAttribute("postingForm") PostingForm postingForm) {
        String searchContent = postingForm.getContent();
        log.info("포스팅 작성자 아이디 검색 : {}",searchContent);

        List<Posting> allSearchPosts = postingRepository.findAll().stream().
                filter(posting -> posting.getUserId().equals(searchContent)).collect(Collectors.toList());
        Collections.sort(allSearchPosts, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }

    @PostMapping("/posting/search/content")
    public String searchContent(@Login User loginUser, Model model,
                         @ModelAttribute("postingForm") PostingForm postingForm) {
        String searchContent = postingForm.getContent();
        log.info("포스팅 내용 검색 : {}",searchContent);

        List<Posting> allSearchPosts = postingRepository.findAll().stream().
                filter(posting -> posting.getContent().equals(searchContent)).collect(Collectors.toList());
        Collections.sort(allSearchPosts, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }
}
