package SpringCommunityService.CommunityService.domain.follow;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Follow {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String followId;

    public Follow(){
    }

    public Follow(String userId,String followId){
        this.userId = userId;
        this.followId = followId;
    }
}
