package io.magentys;

import io.magentys.annotations.Narrate;
import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;
import io.magentys.utils.Strings;
import io.magentys.utils.UniqueId;

import java.util.*;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;
import static io.magentys.utils.Requires.requiresNotNull;

public class Agent {

    protected Memory memory;
    protected List<Any> tools = new ArrayList<Any>();

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name = UniqueId.incrementalId();


    public Set<Narrator> getNarrators() {
        return narrators;
    }

    protected Set<Narrator> narrators = new HashSet<Narrator>();

    public Agent(final Memory memory) {
        this.memory = memory;
    }

    public void setMemory(final Memory mem) {
        this.memory = mem;
    }

    public <RESULT> RESULT performs(final Mission<RESULT> mission) {
        narrateBefore(mission);
        RESULT result = mission.accomplishAs(this);
        narrateAfter(mission);
        return result;
    }

    protected <RESULT> void narrateBefore(Mission<RESULT> mission) {
        if(mission.getClass().isAnnotationPresent(Narrate.class)){
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            narrateThat(narrate.value());
        }
    }

    protected <RESULT> void narrateAfter(Mission<RESULT> mission) {
        if(mission.getClass().isAnnotationPresent(Narrate.class)){
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            if(!Strings.empty.equals(narrate.after()) && narrate.after() != null) narrateThat(narrate.after());
        }
    }

    public void narrateThat(String message){
        for(Narrator narrator : narrators) {
            narrator.narrate(name, "info", message);
        }
    }

    public void narrateThat(String level, String message){
        for(Narrator narrator : narrators){
            narrator.narrate(name, "info", message);
        }
    }

    public Agent performAll(final Mission... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (final Mission mission : missions) {
            performs(mission);
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

    public Agent reportsUsing(final Narrator... narrators){
        requiresNotNull(narrators, "narrators were null");
        for(final Narrator narrator : narrators){
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent setTools(List<Any> tools){
        this.tools = tools;
        return this;
    }

    public Agent setNarrators(Set<Narrator> narrators){
        this.narrators = narrators;
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

    public <T> T and(final Mission<T> mission) {
        return mission.accomplishAs(this);
    }

    public <T> T andHe(final Mission<T> mission) {
        return and(mission);
    }

    public <T> T andShe(final Mission<T> mission) {
        return and(mission);
    }


    public List<Any> getTools() {
        return tools;
    }

    public Memory getMemory() {
        return memory;
    }

    public Agent addNarrators(Narrator... narrators){
        requiresNotNull(narrators, "Narrators passed were null");
        for(Narrator narrator : narrators){
            requiresNotNull(narrator, "narrator was null");
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent clone(){
        return new Agent(memory).setTools(tools).setNarrators(narrators);
    }

    public <KEY> Agent askThe(final Agent anotherAgent, KEY key){
        anotherAgent.getMemory().transferTo(memory, key);
        return this;
    }


}
