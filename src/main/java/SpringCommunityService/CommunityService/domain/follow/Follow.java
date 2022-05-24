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
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User follow;

    public Follow(){
    }

    public Follow(User user,User follow){
        this.user = user;
        this.follow = follow;
    }
}
