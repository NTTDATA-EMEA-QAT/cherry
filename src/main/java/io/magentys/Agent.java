package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

import java.util.ArrayList;
import java.util.List;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;
import static io.magentys.utils.Requires.requiresNotNull;

public class Agent {

    protected Memory memory;
    protected List<Any> tools = new ArrayList<Any>();

    public Agent(final Memory memory) {
        this.memory = memory;
    }

    protected void setMemory(final Memory mem) {
        this.memory = mem;
    }

    public <RESULT> RESULT performs(final Mission<RESULT> mission) {
        return mission.accomplishAs(this);
    }

    public Agent performAll(final Mission... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (final Mission mission : missions) {
            mission.accomplishAs(this);
        }
        return this;
    }

    public Agent obtains(final Object... tools) {
        requiresNotNull(tools, "tools were empty");
        for (final Object tool : tools) {
            this.tools.add(any(tool));
        }
        return this;
    }

    protected Agent setTools(List<Any> tools){
        this.tools = tools;
        return this;
    }

    public <TOOL> TOOL usingThe(final Class<TOOL> toolClass) {

        for (final Any tool : tools) {
            if (Clazz.isClassOrSubclass(toolClass, tool.get().getClass())) {
                return (TOOL) tool.get();
            }
        }

        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public <VALUE> void keepsInMind(final String key, final VALUE value) {
        this.memory.remember(key, value);
    }

    public <VALUE> VALUE recalls(final String key, final Class<VALUE> clazz) {
        return (VALUE) memory.recall(key, clazz);
    }

    public Agent and(final Mission mission) {
        performAll(mission);
        return this;
    }

    public Agent andHe(final Mission... missions) {
        return performAll(missions);
    }

    public Agent andShe(final Mission... missions) {
        return performAll(missions);
    }


    public List<Any> getTools() {
        return tools;
    }

    public Memory getMemory() {
        return memory;
    }

    public Agent clone(){
        return new Agent(memory).setTools(tools);
    }

    public <KEY> Agent askThe(final Agent anotherAgent, KEY key){
        anotherAgent.getMemory().transferTo(memory, key);
        return this;
    }


}
