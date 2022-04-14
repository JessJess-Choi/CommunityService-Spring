package SpringCommunityService.CommunityService.web.posting;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostingForm {

    @NotEmpty
    private String content;
}
