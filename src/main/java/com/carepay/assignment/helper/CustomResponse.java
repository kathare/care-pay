package com.carepay.assignment.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author  Kelvin @2021
 *  This class has message and body. Useful for clean responses especially when integrating our APIs
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> extends CustomMessage {
    private T data;

    public CustomResponse(CustomMessage customMessage){
        this.message = customMessage.getMessage();
    }

    public CustomResponse(String message, T obj){
        this.message = message;
        this.data = obj;
    }
}
