package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Posting {

    @Id @GeneratedValue
    @Column(name="posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String content;
    @Transient
    private List<Image> images;
    private LocalTime time;
    private Long likeNumber;

    public Posting(){
    }

    public Posting(User user,String content,LocalTime time){
        this.user = user;
        this.content = content;
        this.time = time;
        this.likeNumber = 0L;
    }

    public void addLike(){
        this.likeNumber++;
    }

    public void removeLike(){
        this.likeNumber--;
    }
}
