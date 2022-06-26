package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

@Data
public class ResponseForEditProfile {

    private boolean editComplete;

    public ResponseForEditProfile(boolean editComplete){
        this.editComplete = editComplete;
    }
}
