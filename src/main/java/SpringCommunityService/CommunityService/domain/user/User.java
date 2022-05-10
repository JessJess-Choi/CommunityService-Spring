package SpringCommunityService.CommunityService.domain.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String name;

    public User(){
    }

    public User(String userId,String password,String email,String name){
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
