package SpringCommunityService.CommunityService.api.dto;

import SpringCommunityService.CommunityService.domain.comment.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private String user;

    private String content;
    private LocalDateTime time;

    public CommentDto(Comment comment){
        this.user = comment.getUser().getName();
        this.content = comment.getContent();
        this.time = comment.getTime();
    }
}
