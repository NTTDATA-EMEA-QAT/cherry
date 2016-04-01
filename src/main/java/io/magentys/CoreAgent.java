package io.magentys;

public class CoreAgent implements Agent {

    private final Memory memory;

    private CoreAgent(final Memory memory){
        this.memory = memory;
    }

    public static CoreAgent withMemory(final Memory mem){
        return new CoreAgent(mem);
    }

    @Override
    public <RESULT> RESULT perform(Mission<RESULT> mission) {
        return null;
    }

    @Override
    public void performAll(Mission... missions) {

    }

    @Override
    public void acquireTool(Object tool) {

    }

    @Override
    public <TOOL> TOOL usingThe(Class<TOOL> toolClass) {
        return null;
    }
}
