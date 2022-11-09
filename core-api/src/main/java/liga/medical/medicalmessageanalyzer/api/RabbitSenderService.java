package liga.medical.medicalmessageanalyzer.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.common.dto.RabbitMessageDto;

public interface RabbitSenderService {

    void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException;
}
