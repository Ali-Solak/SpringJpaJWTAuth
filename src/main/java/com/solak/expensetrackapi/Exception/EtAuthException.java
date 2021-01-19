package com.solak.expensetrackapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthException extends Exception {

    public EtAuthException(String message) {
        super(message);
    }
}
