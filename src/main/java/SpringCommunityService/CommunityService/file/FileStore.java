package SpringCommunityService.CommunityService.file;

import SpringCommunityService.CommunityService.domain.UploadFile;
import SpringCommunityService.CommunityService.domain.image.Image;
import SpringCommunityService.CommunityService.domain.posting.Posting;
import SpringCommunityService.CommunityService.domain.posting.PostingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public List<Image> storeFiles(Posting posting, List<MultipartFile> multipartFiles) throws IOException {
        List<Image> storeFileResult = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(posting,multipartFile));
            }
        }
        return storeFileResult;
    }

    public Image storeFile(Posting posting, MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = createStoreFileName(originalFileName, uuid);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new Image(posting, originalFileName, storeFileName);
    }


    private String createStoreFileName(String originalFileName, String uuid) {
        String ext = extractExt(originalFileName);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}
