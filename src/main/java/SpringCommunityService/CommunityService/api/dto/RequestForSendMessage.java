package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestForSendMessage {
    @NotEmpty
    private String content;
}
