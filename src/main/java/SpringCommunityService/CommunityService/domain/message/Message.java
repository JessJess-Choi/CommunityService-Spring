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
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User receiver;
    private String message;
    private LocalTime localTime;

    public Message(){
    }

    public Message(User user,User receiver,String message,LocalTime localTime){
        this.user = user;
        this.receiver = receiver;
        this.message = message;
        this.localTime = localTime;
    }
}
