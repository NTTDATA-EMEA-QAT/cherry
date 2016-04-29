package io.magentys.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class NotAvailableException extends RuntimeException {

    public NotAvailableException(final String message) {
        super(message);
    }

    public NotAvailableException(final Class clazz){
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
