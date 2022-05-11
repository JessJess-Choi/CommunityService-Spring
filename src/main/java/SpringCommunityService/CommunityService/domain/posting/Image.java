package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.UploadFile;
import SpringCommunityService.CommunityService.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Image {

    @Id @GeneratedValue
    @Column(name="image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting")
    private Posting posting;

    private String uploadFileName;
    private String storeFileName;

    public Image(){
    }

    public Image(String uploadFileName, String storeFileName){
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
