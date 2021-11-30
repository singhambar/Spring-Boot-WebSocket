package com.singhambar.websocket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.singhambar.websocket.configuration.WebSocketConstants.*;


/**
 * @author Ambar Singh
 * @email_id singhambar55@gmail.com
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(WEB_SOCKET_TOPIC);
        config.setApplicationDestinationPrefixes(WEB_SOCKET_APP_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WEB_SOCKET_STOMP_ENDPOINT).withSockJS();
    }

}
