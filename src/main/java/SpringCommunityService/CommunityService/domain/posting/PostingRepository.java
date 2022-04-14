package SpringCommunityService.CommunityService.domain.posting;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostingRepository {

    private final Map<Long, Posting> store = new HashMap<>();
    private long sequence = 0L;

    public Posting save(Posting posting){
        posting.setId(++sequence);
        store.put(sequence,posting);
        return posting;
    }

    public Posting set(Long id,Posting posting){
        posting.setId(id);
        store.put(id,posting);
        return posting;
    }

    public Posting findById(Long id){
        return store.get(id);
    }

    public List<Posting> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
