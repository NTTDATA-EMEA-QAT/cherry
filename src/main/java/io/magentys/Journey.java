package io.magentys;

import static io.magentys.CoreMemory.coreMemory;

public class Journey {
    public static Agent coreAgent() {
        return CoreAgent.withMemory(coreMemory());
    }
}
