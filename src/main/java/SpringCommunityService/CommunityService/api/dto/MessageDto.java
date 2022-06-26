package SpringCommunityService.CommunityService.api.dto;

import SpringCommunityService.CommunityService.domain.message.Message;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MessageDto {

    @NotEmpty
    private String sender;
    @NotEmpty
    private String receiver;
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime time;

    public MessageDto(Message message){
        this.sender = message.getUser().getName();
        this.receiver = message.getReceiver().getName();
        this.content = message.getMessage();
        this.time = message.getTime();
    }
}
