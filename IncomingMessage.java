package com.singhambar.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ambar Singh
 * @email_id singhambar55@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingMessage {

    private String message;

    private  String ip;

    public String toString(){
        return "message-> "+message+", client-ip->" + ip;
    }

}
