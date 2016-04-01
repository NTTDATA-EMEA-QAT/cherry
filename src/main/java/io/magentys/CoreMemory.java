package io.magentys;

/**
 * Created by kostasmamalis on 01/04/16.
 */
public class CoreMemory implements Memory {

    public static CoreMemory coreMemory(){
        return new CoreMemory();
    }

    @Override
    public <KEY, VALUE> void remember(KEY key, VALUE value) {

    }

    @Override
    public <KEY, VALUE> VALUE recall(KEY key, Class<VALUE> clazz) {
        return null;
    }
}
