package SpringCommunityService.CommunityService.web.posting;

import SpringCommunityService.CommunityService.domain.UploadFile;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingRepository;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.file.FileStore;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {

    private final PostingRepository postingRepository;
    private final FileStore fileStore;

    @GetMapping("/posting")
    public String userPosting(@Login User loginUser, Model model,
                              @ModelAttribute("postingForm") PostingForm postingForm){
        //db에서 값 읽어와서 모델에 넣고 contents  렌더링 => contents/contents도 수정
        log.info("id : {}, loginId : {} 게시판 접속",loginUser.getId(),loginUser.getUserId());
        log.info("{} posting :  {}",loginUser.getUserId(),postingRepository.findById(1L));

        List<Posting> allPosts = postingRepository.findAll();
        Collections.sort(allPosts, (o1,o2) -> o2.getTime().compareTo(o1.getTime()));

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
                                       @Login User loginUser) throws IOException {
        List<UploadFile> storeImageFiles = fileStore.storeFiles(postingForm.getImageFiles());
        log.info("newContent Mapping");
        Posting posting = new Posting(loginUser.getUserId(),postingForm.getContent(), storeImageFiles, LocalTime.now());
        postingRepository.save(posting);
        log.info("{}",postingForm.getContent());
        return "redirect:/posting";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/posting/edit/{postingId}")
    public String editPosting(@PathVariable("postingId") String postingId, Model model,
                              @ModelAttribute("postingForm") PostingForm postingForm){
        log.info("editPosting : {}",postingId);
        Posting posting = postingRepository.findById(Long.parseLong(postingId));
        model.addAttribute("posting",posting);
        log.info("editPosting End");
        return "posting/edit";
    }

    @PostMapping("/posting/edit/{postingId}")
    public String edit(@Valid @ModelAttribute("postingForm") PostingForm postingForm,
                       @Login User loginUser, @PathVariable("postingId") String postingId) throws IOException {
        log.info("edit : {}",postingId);
        log.info("{}",postingId);
        log.info("{}",postingForm);

        List<UploadFile> storeImageFiles = fileStore.storeFiles(postingForm.getImageFiles());
        Posting posting = new Posting(loginUser.getUserId(),postingForm.getContent(), storeImageFiles, LocalTime.now());
        postingRepository.set(Long.parseLong(postingId),posting);
        log.info("{}",posting);
        log.info("edit End");
        return "redirect:/posting";
    }

    @GetMapping("/posting/search/postingId")
    public String searchPostingId(@Login User user, Model model,
                                  @ModelAttribute("postingForm") PostingForm postingForm){
        model.addAttribute("user",user);
        return "posting/searchById";
    }

    @PostMapping("/posting/search/postingId")
    public String searchByPostingId(@Login User loginUser, Model model,
                                    @ModelAttribute("postingForm") PostingForm postingForm){
        String searchContent = postingForm.getContent();
        log.info("포스팅 작성자 아이디 검색 : {}",searchContent);

        List<Posting> allSearchPosts = postingRepository.findAll().stream()
                .filter(posting -> posting.getUserId().contains(searchContent))
                .collect(Collectors.toList());

        Collections.sort(allSearchPosts, (o1, o2) -> o2.getTime().compareTo(o1.getTime()));

        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }

    @GetMapping("/posting/search/content")
    public String searchPostingContent(@Login User user, Model model,
                                  @ModelAttribute("postingForm") PostingForm postingForm){
        model.addAttribute("user",user);
        return "posting/searchByContent";
    }

    @PostMapping("/posting/search/content")
    public String searchByPostingContent(@Login User loginUser, Model model,
                                    @ModelAttribute("postingForm") PostingForm postingForm){
        String searchContent = postingForm.getContent();
        log.info("포스팅 내용 검색 : {}",searchContent);

        List<Posting> allSearchPosts = postingRepository.findAll().stream().
                filter(posting -> posting.getContent().contains(searchContent)).collect(Collectors.toList());

        Collections.sort(allSearchPosts, (o1, o2) -> o2.getTime().compareTo(o1.getTime()));

        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }
}
