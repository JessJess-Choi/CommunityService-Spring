package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.UploadFile;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Posting {

    @Id @GeneratedValue
    @Column(name="posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private String loginId;
    private String content;
 //   private List<UploadFile> imageFiles;
    private LocalTime time;

    public Posting(){
    }

    public Posting(String loginId,String content,List<UploadFile> imageFiles,LocalTime time){
        this.loginId = loginId;
        this.content = content;
//        this.imageFiles = imageFiles;
        this.time = time;
    }
}
