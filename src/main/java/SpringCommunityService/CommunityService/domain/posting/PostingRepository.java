package SpringCommunityService.CommunityService.domain.posting;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostingRepository {

    private final Map<Long, Posting> store = new HashMap<>();
    private long sequence = 0L;

    @PersistenceContext
    private EntityManager em;

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

    public void remove(long id) {
        store.remove(id);
    }

    //JPA
    public Posting saveJpa(Posting posting){
        em.persist(posting);
        return posting;
    }

    public List<Posting> findAllJpa(){
        return em.createQuery("select p from Posting p",Posting.class)
                .getResultList();
    }

}
