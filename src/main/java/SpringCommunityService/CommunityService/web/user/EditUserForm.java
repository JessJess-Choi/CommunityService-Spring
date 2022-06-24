package SpringCommunityService.CommunityService.web.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditUserForm {

    @NotEmpty
    String password;

    @NotEmpty
    String name;
}
