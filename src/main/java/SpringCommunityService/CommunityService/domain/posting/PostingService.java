package SpringCommunityService.CommunityService.domain.posting;

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
}
