package SpringCommunityService.CommunityService.domain.user;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String userId;
    private String password;
    private String email;

    public User(){
    }

    public User (String userId, String password, String email){
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
