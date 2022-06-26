package SpringCommunityService.CommunityService.api.dto;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;

@Data
public class ProfileDto {

    private String name;
    private String email;

    public ProfileDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
