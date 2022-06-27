package SpringCommunityService.CommunityService.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestForLogin {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
