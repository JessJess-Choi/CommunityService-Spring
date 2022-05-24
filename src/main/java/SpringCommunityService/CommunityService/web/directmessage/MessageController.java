package SpringCommunityService.CommunityService.web.directmessage;

import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageService;
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
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final FollowService followService;
    private final MessageService messageService;

    @GetMapping("/directmessage")
    public String userProfileJpa(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followService.findByUser(loginUser));
        return "directmessage/directmessage";
    }

    @PostMapping("/directmessage")
    public String goToHomeJpa(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/directmessage/{receiverId}")
    public String sendMessageJpa(@PathVariable("receiverId") String receiverId,
                              @Login User loginUser, Model model,
                              @ModelAttribute("messageForm") MessageForm messageForm){

        List<Message> messageList = messageService.findByUser(loginUser,receiverId);
 //       Collections.sort(messageList, (o1,o2) -> o1.getLocalTime().compareTo(o2.getLocalTime()));

        messageList.forEach((m) -> log.info("message list : {}",m.getMessage()));

        model.addAttribute("user",loginUser);
        model.addAttribute("receiver",messageService.findOne(receiverId));
        model.addAttribute("messageList",messageList);
        return "directmessage/sendMessage";
    }

    @PostMapping("/directmessage/{receiverId}")
    public String sendMessageToReceiverJpa(@PathVariable("receiverId") String receiverId,
                                        @Login User loginUser,
                                        @ModelAttribute("messageForm") MessageForm messageForm){
        Message message = new Message(loginUser,messageService.findOne(receiverId),messageForm.getMessage(),LocalTime.now());
        messageService.joinJpa(message);
        return "redirect:/directmessage/" + receiverId;
    }
}
