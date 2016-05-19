package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;

public class CoreMemory implements Memory<String> {

    private Map<String, Any> map = new ConcurrentHashMap<String, Any>();

    public static CoreMemory coreMemory(){
        return new CoreMemory();
    }

    @Override
    public <VALUE> void remember(final String key, final VALUE value) {
         map.put(key, any(value));
    }

    @Override
    public void remember(String key, Any any) {
        map.put(key, any);
    }

    @Override
    public <VALUE> VALUE recall(final String key, final Class<VALUE> clazz) {
        final Any result = map.get(key);
        if(result == null) {
            return null;
        }
        final Object unwrapped = result.get();
        if (Clazz.isClassOrSubclass(clazz, unwrapped.getClass())) {
           return (VALUE) unwrapped;
        }
        throw new NotAvailableException("Expected value in memory was not of type: " + clazz);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Any recall(String key) {
        return map.get(key);
    }

    @Override
    public void transferTo(Memory memory, String key) {
        requires(memory instanceof CoreMemory, "It's not a Core Memory");
        CoreMemory casted = (CoreMemory) memory;
        memory.remember(key, map.get(key));
    }
}
