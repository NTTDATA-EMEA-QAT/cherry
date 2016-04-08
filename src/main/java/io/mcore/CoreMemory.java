package io.mcore;

import io.mcore.exceptions.NotAvailableException;
import io.mcore.utils.Any;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.mcore.utils.Any.any;

public class CoreMemory implements Memory<String> {

    private Map<String, Any> map = new ConcurrentHashMap<String, Any>();

    public static CoreMemory coreMemory(){
        return new CoreMemory();
    }

    @Override
    public <VALUE> void remember(String key, VALUE value) {
         map.put(key, any(value));
    }

    @Override
    public <VALUE> VALUE recall(String key, Class<VALUE> clazz) {
        Any result = map.get(key);
        if(result == null) return null;
        final Object unwrapped = result.get();
        if (unwrapped.getClass() == clazz) {
           return (VALUE) unwrapped;
        }
        throw new NotAvailableException("Expected value in memory was not of type: " + clazz);
    }
}
