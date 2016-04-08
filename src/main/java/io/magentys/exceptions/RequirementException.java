package io.magentys.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class RequirementException extends RuntimeException {

    public RequirementException(String message) {
        super(message);
    }

    public RequirementException(Class clazz){
        super("Tool of type: \"" + clazz + "\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.");
    }
}
