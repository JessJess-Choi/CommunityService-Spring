package SpringCommunityService.CommunityService.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    public String userId;

    @NotEmpty
    public String password;
}
