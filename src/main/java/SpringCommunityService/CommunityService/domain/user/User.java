package SpringCommunityService.CommunityService.domain.user;

import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String password;
    private String email;
    private String name;


    public User(){
    }

    public User(String loginId,String password,String email,String name){
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
    }


}
