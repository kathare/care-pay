package com.carepay.assignment.helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author  Kelvin @2021
 *  This class has custom message field. This will be useful incases where we have errors in the processes
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomMessage {
     String message;

    public CustomMessage(String message){
        this.message = message;
    }

    public static CustomMessage map(String message){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(message);
        return customMessage;
    }
}
