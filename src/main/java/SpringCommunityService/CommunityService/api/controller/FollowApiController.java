package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.FollowDto;
import SpringCommunityService.CommunityService.domain.follow.Follow;
import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserService;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class FollowApiController {

    private final FollowService followService;

    private final UserService userService;

    @GetMapping("/api/follow")
    public FollowDto getFollowList(@Login User loginUser){
        FollowDto followDto = new FollowDto();
        followDto.setFollowUserName(followService.findByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        followDto.setUnFollowUserName(followService.findExceptByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        return followDto;
    }

    @GetMapping("/api/unfollowToFollow/{followId}")
    public FollowDto unfollowToFollow(@Login User loginUser, @PathVariable("followId") Long followId){
        FollowDto followDto = new FollowDto();
        try {
            followService.findOne(loginUser, userService.findOne(followId));
        }catch(EmptyResultDataAccessException e) {
            if(followId != loginUser.getId()) {
                followService.joinJpa(new Follow(loginUser, userService.findOne(followId)));
            }
        }
        followDto.setFollowUserName(followService.findByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        followDto.setUnFollowUserName(followService.findExceptByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        return followDto;
    }

    @GetMapping("/api/followToUnfollow/{followId}")
    public FollowDto followToUnfollow(@Login User loginUser, @PathVariable("followId") Long followId){
        FollowDto followDto = new FollowDto();
        try {
            if (followId != loginUser.getId()) {
                followService.unfollowById(followService.findOne(loginUser, userService.findOne(followId)));
            }
        }catch(Exception e){}
        followDto.setFollowUserName(followService.findByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        followDto.setUnFollowUserName(followService.findExceptByUser(loginUser)
                .stream().map(u -> u.getName()).collect(Collectors.toList()));
        return followDto;
    }
}
