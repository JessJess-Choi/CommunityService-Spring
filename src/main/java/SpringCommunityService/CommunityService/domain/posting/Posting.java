package SpringCommunityService.CommunityService.domain.posting;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
public class Posting {

    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String content;
    @NotEmpty
    private String fileId;
    @NotEmpty
    private LocalTime time;

    public Posting(){
    }

    public Posting(String userId,String content,String fileId,LocalTime time){
        this.userId = userId;
        this.content = content;
        this.fileId = fileId;
        this.time = time;
    }
}
