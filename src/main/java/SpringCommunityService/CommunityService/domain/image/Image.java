package SpringCommunityService.CommunityService.domain.image;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id @GeneratedValue
    @Column(name="image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "posting")
    @JsonIgnore
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
