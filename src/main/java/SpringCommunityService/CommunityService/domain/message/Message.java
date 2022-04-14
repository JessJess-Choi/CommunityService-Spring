package SpringCommunityService.CommunityService.domain.message;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
public class Message {

    private Long id;

    @NotEmpty
    private String senderId;
    @NotEmpty
    private String receiverId;
    @NotEmpty
    private String message;
    @NotEmpty
    private LocalTime localTime;

    public Message(){
    }

    public Message(String senderId,String receiverId,String message,LocalTime localTime){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.localTime = localTime;
    }
}
