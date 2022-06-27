package SpringCommunityService.CommunityService.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestForAdd {

        @NotEmpty
        private String loginId;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;
        @NotEmpty
        private String name;
}
