package io.magentys.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class RequirementException extends RuntimeException {

    public RequirementException(final String message) {
        super(message);
    }

    public RequirementException(final Class clazz){
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
