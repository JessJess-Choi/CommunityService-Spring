package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

@Data
public class ResponseForLogout {

    private boolean logoutComplete;

    public ResponseForLogout(boolean logoutComplete){
        this.logoutComplete = logoutComplete;
    }
}
