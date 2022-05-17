package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;
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

    private String userName;
    private String content;
    @Transient
    private List<Image> images;
    private LocalTime time;

    public Posting(){
    }

    public Posting(User user,String userName,String content,LocalTime time){
        this.user = user;
        this.userName = userName;
        this.content = content;
        this.time = time;
    }
}
