package com.singhambar.websocket.scheduler;

import com.singhambar.websocket.model.OutgoingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import static com.singhambar.websocket.configuration.WebSocketConstants.*;

/**
 * @author Ambar Singh
 * @email_id singhambar55@gmail.com
 */

@Component
@EnableScheduling
@Slf4j
public class PushNotificationScheduler {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * It will send notification in every 30s (default: 10s)
     */
    @Scheduled(fixedRateString = "${scheduler.push-notification.rate:10000}")
    public void sendPushNotification() {
        log.info("Sending push notification to Browser client");
        simpMessagingTemplate.convertAndSend(WEB_SOCKET_TOPIC + WEB_SOCKET_PUSH_NOTIFICATION_ENDPOINT, new OutgoingMessage("Push Notification from Server!!"));
    }
}
