package io.mcore;

import io.mcore.exceptions.NotAvailableException;
import io.mcore.utils.Any;
import io.mcore.utils.Clazz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.mcore.utils.Any.any;

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

    public void performAll(Mission... missions) {
        // require missions not null --> guava
        Stream.of(missions).forEach(mission -> mission.accomplishAs(this));
    }

    public <TOOL> void obtain(TOOL... tools) {
        Stream.of(tools).forEach(tool -> this.tools.add(any(tool)));
    }

    public <TOOL> TOOL usingThe(Class<TOOL> toolClass) {
        Optional<Any> result = tools.stream().filter(tool -> Clazz.isClassOrSubclass(toolClass, tool.get().getClass())).findFirst();
        if(result.isPresent()) return (TOOL) result.get().get();
        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public <VALUE> void remember(String s, VALUE value) {
        this.memory.remember(s, value);
    }

    public <VALUE> VALUE recall(String s, Class<VALUE> clazz) {
        return (VALUE) memory.recall(s, clazz);
    }


}
