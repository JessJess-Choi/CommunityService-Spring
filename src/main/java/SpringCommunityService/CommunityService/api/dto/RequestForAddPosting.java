package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestForAddPosting {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
