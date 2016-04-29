package io.magentys.utils;

import io.magentys.exceptions.RequirementException;

public class Requires {

    public static void requires(final Boolean condition, final String errorMessage) {
        if (!condition) {
            throw new RequirementException(errorMessage);
        }
    }

    public static <T> T requiresNotNull(final T value, final String errorMessage) {
        if (value == null) {
            throw new RequirementException(errorMessage);
        }
        return value;
    }

}
