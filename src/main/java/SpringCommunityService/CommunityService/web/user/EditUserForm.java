package SpringCommunityService.CommunityService.web.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditUserForm {

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    public EditUserForm(String password, String name){
        this.password = password;
        this.name = name;
    }
}
