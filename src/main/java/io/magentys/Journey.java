package io.magentys;

import static io.magentys.CoreMemory.coreMemory;

public class Journey {
    public static Agent anAgent() {
        return Agent.withMemory(coreMemory());
    }
}
