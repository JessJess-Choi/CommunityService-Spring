package SpringCommunityService.CommunityService.domain.posting;

import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.image.ImageService;
import SpringCommunityService.CommunityService.domain.like.LikeService;
import SpringCommunityService.CommunityService.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostingService {

    private final PostingRepository postingRepository;

    @Autowired
    public PostingService(PostingRepository postingRepository){
        this.postingRepository = postingRepository;
    }

    @Transactional
    public Long joinJpa(Posting posting){
        postingRepository.saveJpa(posting);
        return posting.getId();
    }

    public List<Posting> findAll(){
        return postingRepository.findAllJpa();
    }

    public List<Posting> findByUser(String user){
        return postingRepository.findPostingByUser(user);
    }

    public List<Posting> findByContent(String content){
        return postingRepository.findByContent(content);
    }

    public Posting findByIdJpa(Long postingId){
        return postingRepository.findByIdJpa(postingId);
    }

    @Transactional
    public Posting setJpa(Long id,Posting posting){
        postingRepository.setJpa(id,posting);
        return posting;
    }

    @Transactional
    public void removeJpa(Posting posting){
        postingRepository.removeJpa(posting);
    }
}
