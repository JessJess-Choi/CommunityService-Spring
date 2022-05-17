package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Posting {

    @Id @GeneratedValue
    @Column(name="posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String loginId;
    private String content;
    @Transient
    private List<Image> images;
    private LocalTime time;

    public Posting(){
    }

    public Posting(User user,String loginId,String content,LocalTime time){
        this.user = user;
        this.loginId = loginId;
        this.content = content;
        this.time = time;
    }
}
