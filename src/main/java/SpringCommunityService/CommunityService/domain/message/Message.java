package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Message {

    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String receiverId;
    private String message;
    private LocalTime localTime;

    public Message(){
    }

    public Message(User user,String receiverId,String message,LocalTime localTime){
        this.user = user;
        this.receiverId = receiverId;
        this.message = message;
        this.localTime = localTime;
    }
}
