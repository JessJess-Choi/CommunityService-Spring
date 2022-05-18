package SpringCommunityService.CommunityService.domain.image;

import SpringCommunityService.CommunityService.domain.UploadFile;
import SpringCommunityService.CommunityService.domain.posting.Posting;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "posting")
    private Posting posting;

    private String uploadFileName;
    private String storeFileName;

    public Image(){
    }

    public Image(Posting posting, String uploadFileName, String storeFileName){
        this.posting = posting;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}
