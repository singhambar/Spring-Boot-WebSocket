package com.singhambar.websocket.controller;

import com.singhambar.websocket.model.IncomingMessage;
import com.singhambar.websocket.model.OutgoingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static com.singhambar.websocket.configuration.WebSocketConstants.*;

/**
 * @author Ambar Singh
 * @email_id singhambar55@gmail.com
 */

@Controller
@Slf4j
public class WebSocketController {


    @MessageMapping(WEB_SOCKET_RECEIVER_ENDPOINT)
    @SendTo(WEB_SOCKET_TOPIC + WEB_SOCKET_SUBSCRIBER_ENDPOINT)
    public OutgoingMessage greeting(IncomingMessage incomingMessage) throws Exception {
        log.info("Message received from Browser client:: " + incomingMessage);
        Thread.sleep(1000);
        log.info("Sending reply to Browser client");
        return new OutgoingMessage("Hello, " + HtmlUtils.htmlEscape(incomingMessage.getMessage()) + "!");
    }

}
