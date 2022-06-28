package SpringCommunityService.CommunityService.api.response;

import lombok.Data;

@Data
public class ResponseForDropUser {

    private boolean dropComplete;

    public ResponseForDropUser(boolean dropComplete){
        this.dropComplete = dropComplete;
    }
}
