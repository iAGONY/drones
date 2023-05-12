package com.musala.drones.utility;

import com.musala.drones.model.ServerResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtility {

    public static ServerResponse getSuccessfulResponse(Object data, String message) {
        return ServerResponse.builder()
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static ServerResponse getSuccessfulResponse(String message) {
        return ServerResponse.builder()
                .message(message)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public static ServerResponse getFailureResponse(String message) {
        return ServerResponse.builder()
                .message(message)
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
