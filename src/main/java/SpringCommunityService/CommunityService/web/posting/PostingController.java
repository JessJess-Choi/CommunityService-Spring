package SpringCommunityService.CommunityService.web.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.image.ImageService;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.file.FileStore;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostingController {

    private final FileStore fileStore;

    private final PostingService postingService;
    private final ImageService imageService;

    @PostMapping("/posting")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} 게시판 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/posting/add")
    public String goPostingForm(@ModelAttribute("postingForm") PostingForm postingForm){
        return "posting/postingForm";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        log.info("url : {}, {}",filename,"file:" + fileStore.getFullPath(filename));
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/posting/search/postingId")
    public String searchPostingId(@Login User user, Model model,
                                  @ModelAttribute("postingForm") PostingForm postingForm){
        model.addAttribute("user",user);
        return "posting/searchByName";
    }

    @GetMapping("/posting/search/content")
    public String searchPostingContent(@Login User user, Model model,
                                  @ModelAttribute("postingForm") PostingForm postingForm){
        model.addAttribute("user",user);
        return "posting/searchByContent";
    }

    @GetMapping("/posting")
    public String userPostingJpa(@Login User loginUser, Model model){

        log.info("id : {}, loginId : {} 게시판 접속",loginUser.getId(),loginUser.getLoginId());
        List<Posting> allPosts = postingService.findAll();

        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allPosts);
        return "posting/posting";
    }

    @PostMapping("/posting/add")
    public String addContentJpa(@Valid @ModelAttribute PostingForm postingForm,
                                BindingResult bindingResult,
                                @Login User loginUser) throws IOException {

        if(postingForm.getContent().isEmpty()){
            bindingResult.reject("postingFail","포스팅 내용 입력하세요");
            return "posting/postingForm";
        }

        Posting posting = new Posting(loginUser,postingForm.getContent(),LocalTime.now());
        List<Image> storeImages = fileStore.storeFiles(posting,postingForm.getImageFiles());
        posting.setImages(storeImages);
        postingService.joinJpa(posting);
        storeImages.forEach((image) -> imageService.joinJpa(image));
        log.info("newContent Mapping");
        log.info("{}",postingForm.getContent());
        return "redirect:/posting";
    }

    @GetMapping("/posting/edit/{postingId}")
    public String editPostingJpa(@PathVariable("postingId") String postingId, Model model,
                                 @ModelAttribute("postingForm") PostingForm postingForm,
                                 BindingResult bindingResult, @Login User loginUser){
        log.info("editPosting : {}",postingId);
        Posting posting = postingService.findByIdJpa(Long.parseLong(postingId));
        List<Image> images = imageService.findByPosting(posting);
        posting.setImages(images);

        if(!posting.getUser().getLoginId().equals(loginUser.getLoginId())){
            log.info("post user : {},{}",posting.getUser().getLoginId(),posting.getUser());
            log.info("user : {},{}",loginUser.getLoginId(),loginUser);
            bindingResult.reject("editFail","자신이 작성한 글만 수정 가능합니다.");
            model.addAttribute("editCheck",true);
            model.addAttribute("user",loginUser);
            model.addAttribute("posting",postingService.findAll());
            return "posting/posting";
        }

        model.addAttribute("posting",posting);
        log.info("editPosting End");
        return "posting/edit";
    }

    @PostMapping("/posting/edit/{postingId}")
    public String editJpa(@Valid @ModelAttribute("postingForm") PostingForm postingForm,
                          BindingResult bindingResult, @Login User loginUser,
                          @PathVariable("postingId") String postingId) throws IOException {

        if(postingForm.getContent().isEmpty()){
            bindingResult.reject("postingFail","포스팅 내용 입력하세요");
            return "posting/postingForm";
        }

        log.info("edit : {}",postingId);
        log.info("{}",postingId);
        log.info("postingForm content : {}",postingForm.getContent());

        Posting posting = new Posting(loginUser,postingForm.getContent(), LocalTime.now());
        List<Image> storeImageFiles = fileStore.storeFiles(posting,postingForm.getImageFiles());
        posting.setImages(storeImageFiles);

        log.info("log for update : {}",posting.getContent());
        List<Image> removeImages = imageService.removeJpa(postingService.findByIdJpa(Long.parseLong(postingId)));
        removeImages.forEach((i) -> {
            log.info("remove image : {},{}",i.getId(),i.getPosting());
        });
        postingService.setJpa(Long.parseLong(postingId),posting);

        if(storeImageFiles == null){
            log.info("storeImageFiles null");
        }else {
            storeImageFiles.forEach((image) -> {
                log.info("image : {},{}", image.getPosting(), image.getId());
                imageService.joinJpa(image);
            });
        }
        if(posting.getId() != null) {
            postingService.removeJpa(postingService.findByIdJpa(Long.parseLong(postingId)));
        }
        log.info("{}",posting.getContent());
        log.info("edit End");
        return "redirect:/posting";
    }

    @GetMapping("/posting/edit/remove/{postingId}")
    public String removePosting(@PathVariable("postingId") String postingId){
        imageService.removeJpa(postingService.findByIdJpa(Long.parseLong(postingId)));
        Posting removePosting = postingService.removeJpa(postingService.findByIdJpa(Long.parseLong(postingId)));
        log.info("remove contents : {}",removePosting.getContent());
        return "redirect:/posting";
    }

    @PostMapping("/posting/search/postingId")
    public String searchPostingByNameJpa(@Login User loginUser, Model model,
                                         @ModelAttribute("postingForm") PostingForm postingForm){

        String searchContent = postingForm.getContent();
        log.info("포스팅 작성자 검색 : {}",searchContent);
        List<Posting> allSearchPosts = postingService.findByUser(postingForm.getContent());

        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }

    @PostMapping("/posting/search/content")
    public String searchByPostingContentJpa(@Login User loginUser, Model model,
                                         @ModelAttribute("postingForm") PostingForm postingForm){
        String searchContent = postingForm.getContent();
        log.info("포스팅 내용 검색 : {}",searchContent);

        List<Posting> allSearchPosts = postingService.findByContent(postingForm.getContent());

        model.addAttribute("user",loginUser);
        model.addAttribute("posting",allSearchPosts);
        return "posting/posting";
    }
}
