package SpringCommunityService.CommunityService.domain.message;

import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalDateTime time;

    public Message(){
    }

    public Message(User user,User receiver,String message,LocalDateTime time){
        this.user = user;
        this.receiver = receiver;
        this.message = message;
        this.time = time;
    }
}
