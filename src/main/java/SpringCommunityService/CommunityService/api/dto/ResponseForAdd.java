package SpringCommunityService.CommunityService.api.dto;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;

@Data
public class ResponseForAdd {
    private boolean addComplete;

    public ResponseForAdd(boolean addComplete){
        this.addComplete = addComplete;
    }
}
