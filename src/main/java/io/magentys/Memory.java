package io.magentys;

import io.magentys.utils.Any;

/**
 * Created by kostasmamalis on 01/04/16.
 */
public interface Memory<KEY> {

    <VALUE> void remember(final KEY key, final VALUE value);

    void remember(final KEY key, final Any any);

    <VALUE> VALUE recall(KEY key, Class<VALUE> clazz);

    boolean isEmpty();

    void transferTo(Memory<KEY> memory, KEY key);

    Any recall(KEY key);


}
