package SpringCommunityService.CommunityService.web.directmessage;

import SpringCommunityService.CommunityService.domain.follow.FollowRepository;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageRepository;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DirectMessageController {

    private final FollowRepository followRepository;
    private final MessageRepository messageRepository;

    @GetMapping("/directmessage")
    public String userProfile(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followRepository.findById(loginUser.getUserId()));
        return "directmessage/directmessage";
    }

    @PostMapping("/directmessage")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getUserId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/directmessage/{receiverId}")
    public String sendMessage(@PathVariable("receiverId") String receiverId,
                              @Login User loginUser, Model model,
                              @ModelAttribute("messageForm") MessageForm messageForm){

        List<Message> messageList = messageRepository.findByLoginId(receiverId,loginUser.getUserId());

        Collections.sort(messageList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getLocalTime().compareTo(o2.getLocalTime());
            }
        });

        log.info("{}",messageList);
        model.addAttribute("user",loginUser);
        model.addAttribute("receiver",receiverId);
        model.addAttribute("messageList",messageList);
        return "directmessage/sendMessage";
    }

    @PostMapping("/directmessage/{receiverId}")
    public String sendMessageToReceiver(@PathVariable("receiverId") String receiverId,
                              @Login User loginUser, Model model,
                              @ModelAttribute("messageForm") MessageForm messageForm){

        messageRepository.save(new Message(loginUser.getUserId(),receiverId,messageForm.getMessage(), LocalTime.now()));
        return "redirect:/directmessage/" + receiverId;
    }

}
