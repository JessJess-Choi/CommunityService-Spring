package SpringCommunityService.CommunityService.domain.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
