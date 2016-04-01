package io.magentys;

/**
 * Created by kostasmamalis on 01/04/16.
 */
public interface Memory {

    <KEY, VALUE> void remember(final KEY key, final VALUE value);

    <KEY, VALUE> VALUE recall(KEY key, Class<VALUE> clazz);


}
