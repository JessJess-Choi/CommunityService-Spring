package SpringCommunityService.CommunityService.api.controller;

import SpringCommunityService.CommunityService.api.dto.MessageDto;
import SpringCommunityService.CommunityService.api.request.RequestForSendMessage;
import SpringCommunityService.CommunityService.domain.message.Message;
import SpringCommunityService.CommunityService.domain.message.MessageService;
import SpringCommunityService.CommunityService.domain.user.User;
import SpringCommunityService.CommunityService.web.argumentresolver.Login;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MessageApiController {

    private final MessageService messageService;

    @GetMapping("/api/directmessage/{receiverId}")
    public List<MessageDto> getMessageList(@Login User loginUser,
                                           @PathVariable("receiverId") String receiverId){
        return messageService.findByUser(loginUser, receiverId)
                .stream().map(m -> new MessageDto(m)).collect(Collectors.toList());
    }

    @PostMapping("/api/directmessage/{receiverId}")
    public List<MessageDto> sendMessage(@Login User loginUser,
                                        @PathVariable("receiverId") String receiverId,
                                        @RequestBody RequestForSendMessage requestForSendMessage){

        messageService.joinJpa(new Message(loginUser,messageService.findOne(receiverId),requestForSendMessage.getContent(), LocalDateTime.now()));

        return messageService.findByUser(loginUser, receiverId)
                .stream().map(m -> new MessageDto(m)).collect(Collectors.toList());
    }
}
