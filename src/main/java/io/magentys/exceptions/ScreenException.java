package io.magentys.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class ScreenException extends RuntimeException {

    public ScreenException(final String message) {
        super(message);
    }

    public ScreenException(final Class clazz){
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
