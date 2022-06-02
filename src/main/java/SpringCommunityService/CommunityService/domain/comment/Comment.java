package SpringCommunityService.CommunityService.domain.comment;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting")
    private Posting posting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String content;
    private LocalDateTime time;

    public Comment(User user, Posting posting, String content, LocalDateTime time){
        this.user = user;
        this.posting = posting;
        this.content = content;
        this.time = time;
    }

    public Comment() {
    }
}
