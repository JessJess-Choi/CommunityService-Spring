package SpringCommunityService.CommunityService.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class FollowDto {

    private List<String> followUserName;

    private List<String> unFollowUserName;
}
