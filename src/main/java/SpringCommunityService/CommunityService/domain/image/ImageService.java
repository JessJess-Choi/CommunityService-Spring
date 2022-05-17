package SpringCommunityService.CommunityService.domain.image;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Long joinJpa(Image image){
        imageRepository.saveJpa(image);
        return image.getId();
    }

    public List<Image> findByPosting(Posting posting){
        return imageRepository.findImageListByPosting(posting);
    }
}
