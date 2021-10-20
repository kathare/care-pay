package com.carepay.assignment.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author  Kelvin @2021
 *  This helps in formatting response data depending with the values in custom response object.
 */

public class ResponseUtil {
    public static ResponseEntity<?> getResponse(CustomResponse response) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        return builder.body(response.getData() != null ? response : CustomMessage.map(response.getMessage()));
    }

}
