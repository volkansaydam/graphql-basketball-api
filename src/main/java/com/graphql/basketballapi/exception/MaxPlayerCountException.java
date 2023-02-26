package com.graphql.basketballapi.exception;

import java.util.Map;

public class MaxPlayerCountException extends AbstractGraphQLException {
    public MaxPlayerCountException(String message) {
        super(message);
    }

    public MaxPlayerCountException(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}
