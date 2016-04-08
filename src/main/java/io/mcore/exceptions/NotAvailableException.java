package io.mcore.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class NotAvailableException extends RuntimeException {

    public NotAvailableException(String message) {
        super(message);
    }

    public NotAvailableException(Class clazz){
        super("Tool of type: \"" + clazz + "\" not found in agent's toolset... Use agent.obtain(<tool>) method to assign tools to agent.");
    }
}
