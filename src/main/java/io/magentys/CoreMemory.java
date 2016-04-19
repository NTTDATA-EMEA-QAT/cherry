package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.magentys.utils.Any.any;

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
}
