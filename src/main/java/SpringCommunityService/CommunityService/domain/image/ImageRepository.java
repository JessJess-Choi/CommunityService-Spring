package SpringCommunityService.CommunityService.domain.image;

import SpringCommunityService.CommunityService.domain.posting.Posting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ImageRepository {

    @PersistenceContext
    private EntityManager em;

    public Image saveJpa(Image image){
        em.persist(image);
        return image;
    }

    public List<Image> findImageListByPosting(Posting posting){
        return em.createQuery("select i from Image i where i.posting = :posting",Image.class)
                .setParameter("posting",posting)
                .getResultList();
    }

    public List<Image> setJpa(Posting posting, List<Image> images){
        List<Image> updateImages = em.createQuery("select i from Image i where i.posting = :posting",Image.class)
                                        .setParameter("posting",posting).getResultList();
        updateImages.removeAll(updateImages);
        updateImages.addAll(images);
        em.merge(updateImages);
        return updateImages;
    }

    public List<Image> removeJpa(Posting posting){
        List<Image> removeImages = em.createQuery("select i from Image i where i.posting = :posting",Image.class)
                                    .setParameter("posting",posting).getResultList();
        removeImages.forEach((removeImage) -> em.remove(removeImage));
        return removeImages;
    }

}
