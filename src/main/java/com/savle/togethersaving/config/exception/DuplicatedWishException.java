package com.savle.togethersaving.config.exception;

import org.springframework.dao.DataAccessException;

public class DuplicatedWishException extends DataAccessException {
    public DuplicatedWishException(String msg) {
        super(msg);
    }
}
