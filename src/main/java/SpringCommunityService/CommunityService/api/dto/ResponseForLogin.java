package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

@Data
public class ResponseForLogin {

    private boolean loginComplete;

    public ResponseForLogin(boolean loginComplete){
        this.loginComplete = loginComplete;
    }
}
