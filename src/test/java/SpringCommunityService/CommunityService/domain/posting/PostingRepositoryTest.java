package SpringCommunityService.CommunityService.domain.posting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


class PostingRepositoryTest {

    PostingRepository postingRepository = new PostingRepository();

    @AfterEach
    void clear(){
        postingRepository.clearStore();
    }

    @Test
    void a(){
        String str1 = "1234";
        String str2 = "abcd";
        System.out.println(Long.parseLong(str1));
    }

    @Test
    void saveTest(){
        String content = "1";
        for(int i=0;i<100;i++)content += "1";
      /*  Posting posting = new Posting("userId1",content,"fileId1", LocalTime.now());
        Posting savePosting = postingRepository.save(posting);
        Posting findPosting = postingRepository.findById(1L);

        assertThat(posting).isEqualTo(savePosting);
        assertThat(savePosting).isEqualTo(findPosting);
        assertThat(findPosting.getId()).isEqualTo(1L);*/
    }

    @Test
    void findAllTest(){
        String content = "1";
        for(int i=0;i<100;i++)content += "1";
 /*       Posting postingA = new Posting("userIdA",content,"fileIdA", LocalTime.now());
        Posting postingB = new Posting("userIdB",content,"fileIdB", LocalTime.now());

        postingRepository.save(postingA);
        postingRepository.save(postingB);
        assertThat(postingRepository.findAll()).contains(postingA,postingB);*/
    }
}