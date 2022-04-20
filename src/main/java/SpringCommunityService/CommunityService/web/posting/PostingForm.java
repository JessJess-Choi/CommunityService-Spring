package SpringCommunityService.CommunityService.web.posting;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PostingForm {

    @NotEmpty
    private String content;
    @Nullable
    private List<MultipartFile> imageFiles;
}
