package SpringCommunityService.CommunityService.api.dto;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostingDto {

    private String user;
    private String title;
    private String content;
    private List<String> images;
    private LocalDateTime time;
    private Long likeNumber;


    public PostingDto(Posting posting){
        this.user = posting.getUser().getName();
        this.title = posting.getTitle();
        this.content = posting.getContent();
        this.images = posting.getImages().stream()
                .map(i -> i.getStoreFileName()).collect(Collectors.toList());
        this.time = posting.getTime();
        this.likeNumber = posting.getLikeNumber();
    }
}
