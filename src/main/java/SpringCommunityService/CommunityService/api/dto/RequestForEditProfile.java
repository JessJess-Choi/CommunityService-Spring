package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestForEditProfile {

    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

}
