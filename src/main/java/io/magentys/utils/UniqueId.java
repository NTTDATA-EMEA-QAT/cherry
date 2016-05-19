package io.magentys.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class UniqueId {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    private static AtomicLong idCounter = new AtomicLong();

    public static String incrementalId() {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
