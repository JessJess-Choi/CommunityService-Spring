package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.PostingDto;
import SpringCommunityService.CommunityService.api.dto.ProfileDto;
import SpringCommunityService.CommunityService.api.request.RequestForAddPosting;
import SpringCommunityService.CommunityService.api.request.RequestForEditPosting;
import SpringCommunityService.CommunityService.api.request.RequestForSearchPostingByContent;
import SpringCommunityService.CommunityService.api.request.RequestForSearchPostingByUser;
import SpringCommunityService.CommunityService.api.response.ResponseForEditPosting;
import SpringCommunityService.CommunityService.domain.comment.CommentService;
import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.image.ImageService;
import SpringCommunityService.CommunityService.domain.like.LikeService;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.file.FileStore;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class PostingApiController {

    private final FileStore fileStore;

    private final PostingService postingService;

    private final ImageService imageService;

    private final CommentService commentService;

    private final LikeService likeService;


    @GetMapping("/api/posting")
    public List<PostingDto> getPosting(){
        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/posting/add/photo",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<PostingDto> addPostingWithFile(@RequestPart RequestForAddPosting requestForAddPosting,
                                       @RequestPart List<MultipartFile> files,
                                       @Login User loginUser) throws IOException {

        Posting posting = new Posting(loginUser, requestForAddPosting.getTitle(), requestForAddPosting.getContent(), LocalDateTime.now());
        if(!files.isEmpty() && files != null){
            List<Image> storeImages = fileStore.storeFiles(posting, files);
            posting.setImages(storeImages);
            storeImages.forEach(i -> imageService.joinJpa(i));
        }
        postingService.joinJpa(posting);
        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }

    @PostMapping("/api/posting/add")
    public List<PostingDto> addPostingWithoutFile(@RequestBody RequestForAddPosting requestForAddPosting,
                                               @Login User loginUser) {

        Posting posting = new Posting(loginUser, requestForAddPosting.getTitle(), requestForAddPosting.getContent(), LocalDateTime.now());
        postingService.joinJpa(posting);
        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }
    @GetMapping("/api/posting/search/postingId")
    public ProfileDto getUserInfo1(@Login User loginUser){
        return new ProfileDto(loginUser);
    }


    @PostMapping("/api/posting/search/postingId")
    public List<PostingDto> searchPostingByUser(@RequestBody RequestForSearchPostingByUser requestForSearchPostingByUser){
       return postingService.findByUser(requestForSearchPostingByUser.getUserName())
               .stream().map(p -> new PostingDto(p))
               .collect(Collectors.toList());
    }

    @GetMapping("/api/posting/search/content")
    public ProfileDto getUserInfo2(@Login User loginUser){
        return new ProfileDto(loginUser);
    }

    @PostMapping("/api/posting/search/content")
    public List<PostingDto> searchPostingByContent(@RequestBody RequestForSearchPostingByContent requestForSearchPostingByContent){
        return postingService.findByContent(requestForSearchPostingByContent.getContent())
                .stream().map(p -> new PostingDto(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/posting/edit/{postingId}")
    public ResponseForEditPosting checkValidUser(@PathVariable("postingId") Long postingId,
                                                 @Login User loginUser){

        Posting posting = postingService.findByIdJpa(postingId);
        List<Image> images = imageService.findByPosting(posting);
        posting.setImages(images);

        if(!posting.getUser().getLoginId().equals(loginUser.getLoginId())){
            return new ResponseForEditPosting(false);
        }
        return new ResponseForEditPosting(true);
    }

    @PostMapping("/api/posting/edit/{postingId}")
    public List<PostingDto> editPostingWithoutFile(@PathVariable("postingId") Long postingId,
                                                   @RequestBody RequestForEditPosting requestForEditPosting){

        Posting posting = postingService.findByIdJpa(postingId);
        posting.setTitle(requestForEditPosting.getTitle());
        posting.setContent(requestForEditPosting.getContent());
        posting.setTime(LocalDateTime.now());

        imageService.removeByPosting(postingService.findByIdJpa(postingId));

        postingService.setJpa(postingId,posting);

        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/posting/edit/photo/{postingId}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<PostingDto> editPostingWithFile(@PathVariable("postingId") Long postingId,
                                                @RequestPart RequestForEditPosting requestForEditPosting,
                                                @RequestPart List<MultipartFile> files) throws IOException{

        log.info("start : {}",requestForEditPosting.getContent());
        Posting posting = postingService.findByIdJpa(postingId);
        posting.setTitle(requestForEditPosting.getTitle());
        posting.setContent(requestForEditPosting.getContent());
        posting.setTime(LocalDateTime.now());

        imageService.removeByPosting(postingService.findByIdJpa(postingId));

        List<Image> storeImages = fileStore.storeFiles(posting, files);
        posting.setImages(storeImages);
        storeImages.forEach(i -> imageService.joinJpa(i));

        postingService.setJpa(postingId,posting);

        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/api/posting/edit/remove/{postingId}")
    public List<PostingDto> removePosting(@PathVariable("postingId") Long postingId){

        likeService.removeByPosting(postingService.findByIdJpa(postingId));
        imageService.removeByPosting(postingService.findByIdJpa(postingId));
        commentService.removeAllCommentJpa(postingService.findByIdJpa(postingId));
        postingService.removeByPosting(postingService.findByIdJpa(postingId));

        return postingService.findAll()
                .stream().map(p -> new PostingDto(p)).collect(Collectors.toList());
    }
}
