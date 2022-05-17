package SpringCommunityService.CommunityService.web.directmessage;

import SpringCommunityService.CommunityService.domain.follow.FollowRepository;
import SpringCommunityService.CommunityService.domain.follow.FollowService;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageRepository;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final FollowRepository followRepository;
    private final MessageRepository messageRepository;

    private final FollowService followService;
    private final MessageService messageService;
/*
    @GetMapping("/directmessage")
    public String userProfile(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followRepository.findById(loginUser.getLoginId()));
        return "directmessage/directmessage";
    }

    @PostMapping("/directmessage")
    public String goToHome(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속 후 홈으로 돌아감",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        return "redirect:/";
    }

    @GetMapping("/directmessage/{receiverId}")
    public String sendMessage(@PathVariable("receiverId") String receiverId,
                              @Login User loginUser, Model model,
                              @ModelAttribute("messageForm") MessageForm messageForm){

        List<Message> messageList = messageRepository.findByLoginId(receiverId,loginUser.getLoginId());
        Collections.sort(messageList, (o1,o2) -> o1.getLocalTime().compareTo(o2.getLocalTime()));
  /*      Collections.sort(messageList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getLocalTime().compareTo(o2.getLocalTime());
            }
        });
*/
 /*       log.info("{}",messageList);
        model.addAttribute("user",loginUser);
        model.addAttribute("receiver",receiverId);
        model.addAttribute("messageList",messageList);
        return "directmessage/sendMessage";
    }

    @PostMapping("/directmessage/{receiverId}")
    public String sendMessageToReceiver(@PathVariable("receiverId") String receiverId,
                              @Login User loginUser, Model model,
                              @ModelAttribute("messageForm") MessageForm messageForm){

//        messageRepository.save(new Message(loginUser.getLoginId(),receiverId,messageForm.getMessage(), LocalTime.now()));
        return "redirect:/directmessage/" + receiverId;
    }


    */
    @GetMapping("/directmessage")
    public String userProfileJpa(@Login User loginUser, Model model){
        log.info("id : {}, loginId : {} DM 접속",loginUser.getId(),loginUser.getLoginId());
        model.addAttribute("user",loginUser);
        model.addAttribute("follow",followService.findById(loginUser));
//        model.addAttribute("follow",followRepository.findById(loginUser.getLoginId()));
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
//        List<Message> messageList = messageRepository.findByLoginId(receiverId,loginUser.getLoginId());
        Collections.sort(messageList, (o1,o2) -> o1.getLocalTime().compareTo(o2.getLocalTime()));

        messageList.forEach((m) -> log.info("message list : {}",m.getMessage()));

        model.addAttribute("user",loginUser);
        model.addAttribute("receiver",messageService.findOne(receiverId));
        model.addAttribute("messageList",messageList);
        return "directmessage/sendMessage";
    }

    @PostMapping("/directmessage/{receiverId}")
    public String sendMessageToReceiverJpa(@PathVariable("receiverId") String receiverId,
                                        @Login User loginUser, Model model,
                                        @ModelAttribute("messageForm") MessageForm messageForm){
        messageService.joinJpa(new Message(loginUser,messageService.findOne(receiverId).getLoginId(),messageForm.getMessage(),LocalTime.now()));
        return "redirect:/directmessage/" + receiverId;
    }

}
