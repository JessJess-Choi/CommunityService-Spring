package SpringCommunityService.CommunityService.domain.follow;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Follow {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String followingId;



    public Follow(){
    }

    public Follow(String userId,String followId){
        this.userId = userId;
        this.followingId = followId;
    }
}
