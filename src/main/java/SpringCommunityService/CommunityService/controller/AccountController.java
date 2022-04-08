package SpringCommunityService.CommunityService.controller;

import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;


    @GetMapping("/account")
    public String accountUser(){
        return "account";
    }

    @PostMapping("/account")
    public String joinUser(@ModelAttribute User user){

        userRepository.save(user);
        log.info("id : {} userId : {} pw : {} email : {}", user.getId(),user.getUserId(),user.getPassword(),user.getEmail());
        return "redirect:/";
    }
}
