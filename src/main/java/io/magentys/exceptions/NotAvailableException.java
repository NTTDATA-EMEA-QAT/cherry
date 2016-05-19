package io.magentys.exceptions;

/**
 * Not Available Exception for tools and memory entries
 */
public class NotAvailableException extends RuntimeException {

    /**
     * Main Contructor
     * @param message
     */
    public NotAvailableException(final String message) {
        super(message);
    }

    /**
     * Not available Memory entry for class
     * @param clazz
     */
    public NotAvailableException(final Class clazz){
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
