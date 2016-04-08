package io.magentys.utils;

import io.magentys.exceptions.RequirementException;

public class Requires {

    public static void requires(Boolean condition, String errorMessage){
        if(!condition) throw new RequirementException(errorMessage);
    }
}
