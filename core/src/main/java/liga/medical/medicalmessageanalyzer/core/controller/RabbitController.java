package liga.medical.medicalmessageanalyzer.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmessageanalyzer.api.RabbitSenderService;
import liga.medical.medicalmessageanalyzer.core.config.RabbitConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import liga.medical.common.dto.RabbitMessageDto;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    private final RabbitSenderService rabbitSenderService;

    public RabbitController(RabbitSenderService rabbitSenderService) {
        this.rabbitSenderService = rabbitSenderService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody RabbitMessageDto message) throws JsonProcessingException {
        rabbitSenderService.sendMessage(message, RabbitConfig.QUEUE_NAME);
    }
}
