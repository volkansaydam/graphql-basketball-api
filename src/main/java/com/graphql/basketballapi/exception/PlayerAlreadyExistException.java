package com.graphql.basketballapi.exception;

import java.util.Map;

public class PlayerAlreadyExistException extends AbstractGraphQLException {

    public PlayerAlreadyExistException(String message) {
        super(message);
    }

    public PlayerAlreadyExistException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}
