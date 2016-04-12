package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

import java.util.ArrayList;
import java.util.List;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;

public class Agent {

    private final Memory memory;
    private List<Any> tools = new ArrayList<>();

    public Agent(final Memory memory) {
        this.memory = memory;
    }

    public static Agent withMemory(final Memory mem) {
        return new Agent(mem);
    }

    public <RESULT> RESULT performs(Mission<RESULT> mission) {
        return mission.accomplishAs(this);
    }

    public Agent performAll(Mission... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (Mission mission : missions) {
            mission.accomplishAs(this);
        }
        return this;
    }

    public <TOOL> Agent obtains(TOOL... tools) {
        for (TOOL tool : tools) {
            this.tools.add(any(tool));
        }
        return this;
    }

    public <TOOL> TOOL usingThe(Class<TOOL> toolClass) {

        for (Any tool : tools) {
            if (Clazz.isClassOrSubclass(toolClass, tool.get().getClass())) {
                return (TOOL) tool.get();
            }
        }

        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public <VALUE> void keepsInMind(String s, VALUE value) {
        this.memory.remember(s, value);
    }

    public <VALUE> VALUE recalls(String s, Class<VALUE> clazz) {
        return (VALUE) memory.recall(s, clazz);
    }

    public Agent and(Mission mission) {
        performAll(mission);
        return this;
    }

    public Agent andHe(Mission... missions) {
        return performAll(missions);
    }

    public Agent andShe(Mission... missions) {
        return performAll(missions);
    }


}
