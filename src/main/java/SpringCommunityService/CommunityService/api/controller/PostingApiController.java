package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.PostingDto;
import SpringCommunityService.CommunityService.api.dto.RequestForAddPosting;
import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.image.ImageService;
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


}
