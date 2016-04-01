package io.magentys;

public interface Agent {

    <RESULT> RESULT perform(Mission<RESULT> mission);

    void performAll(Mission... missions);

    void acquireTool(Object tool);

    <TOOL> TOOL usingThe(Class<TOOL> toolClass);


}
