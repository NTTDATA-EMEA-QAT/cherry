package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;

public class Agent {

    private final Memory memory;
    private List<Any> tools = new ArrayList<>();

    private Agent(final Memory memory){
        this.memory = memory;
    }

    public static Agent withMemory(final Memory mem){
        return new Agent(mem);
    }

    public <RESULT> RESULT performs(Mission<RESULT> mission) {
        return mission.accomplishAs(this);
    }

    public Agent performAll(Mission... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        Stream.of(missions).forEach(mission -> mission.accomplishAs(this));
        return this;
    }

    public <TOOL> Agent obtains(TOOL... tools) {
        Stream.of(tools).forEach(tool -> this.tools.add(any(tool)));
        return this;
    }

    public <TOOL> TOOL usingThe(Class<TOOL> toolClass) {
        Optional<Any> result = tools.stream().filter(tool -> Clazz.isClassOrSubclass(toolClass, tool.get().getClass())).findFirst();
        if(result.isPresent()) return (TOOL) result.get().get();
        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public <VALUE> void keepsInMind(String s, VALUE value) {
        this.memory.remember(s, value);
    }

    public <VALUE> VALUE recalls(String s, Class<VALUE> clazz) {
        return (VALUE) memory.recall(s, clazz);
    }

    public Agent and(Mission mission){
        performAll(mission);
        return this;
    }

    public Agent andHe(Mission... missions){ return performAll(missions); }

    public Agent andShe(Mission... missions) { return performAll(missions); }


}
