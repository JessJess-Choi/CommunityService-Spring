package SpringCommunityService.CommunityService.web.directmessage;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MessageForm {

    @NotEmpty
    private String message;
}
