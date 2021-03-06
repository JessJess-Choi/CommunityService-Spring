package SpringCommunityService.CommunityService.domain.like;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LikeIt {

    @Id
    @GeneratedValue
    @Column(name="likeIt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting")
    private Posting posting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    public LikeIt(User user, Posting posting){
        this.user = user;
        this.posting = posting;
    }

    public LikeIt() {
    }
}
