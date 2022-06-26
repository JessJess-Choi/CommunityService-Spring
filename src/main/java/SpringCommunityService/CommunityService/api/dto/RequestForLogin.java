package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

@Data
public class RequestForLogin {
    private String loginId;
    private String password;
}
