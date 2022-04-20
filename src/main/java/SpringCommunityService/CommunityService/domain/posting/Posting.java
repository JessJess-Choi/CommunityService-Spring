package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.UploadFile;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.List;

@Data
public class Posting {

    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String content;
    @Nullable
    private List<UploadFile> imageFiles;
    @NotEmpty
    private LocalTime time;

    public Posting(){
    }

    public Posting(String userId,String content,List<UploadFile> imageFiles,LocalTime time){
        this.userId = userId;
        this.content = content;
        this.imageFiles = imageFiles;
        this.time = time;
    }
}
