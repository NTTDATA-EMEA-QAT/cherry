package io.mcore;

import static io.mcore.CoreMemory.coreMemory;

public class Journey {
    public static Agent anAgent() {
        return Agent.withMemory(coreMemory());
    }
}
