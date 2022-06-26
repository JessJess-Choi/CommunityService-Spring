package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;
@Data
public class RequestForAdd {
        private String loginId;
        private String password;
        private String email;
        private String name;
}
