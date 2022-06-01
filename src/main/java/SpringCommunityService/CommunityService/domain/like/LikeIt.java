package SpringCommunityService.CommunityService.domain.like;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;
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

    String userName;

    public LikeIt(String name, Posting posting){
        this.userName = name;
        this.posting = posting;
    }

    public LikeIt() {
    }
}
