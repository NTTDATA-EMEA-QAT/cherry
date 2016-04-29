package io.magentys;

import static io.magentys.CoreMemory.coreMemory;

public class AgentProvider {
    public static Agent agent() {
        return Agent.withMemory(coreMemory());
    }
}
