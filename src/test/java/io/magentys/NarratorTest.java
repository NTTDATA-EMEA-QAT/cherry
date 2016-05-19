package io.magentys;

import io.magentys.annotations.Narrate;
import org.junit.Test;

import static io.magentys.AgentProvider.agent;
import static io.magentys.narrators.SysoutNarrator.sysout;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class NarratorTest {

    @Test
    public void shouldNarrateSuccessfully() throws Exception {
        Agent agent = agent();
        agent.addNarrators(sysout());
        final MyMission mission = new MyMission();
        agent.performs(mission);
        final MyMissionWithBeforeAndAfter mission1 = new MyMissionWithBeforeAndAfter();
        agent.performs(mission1);
        agent.performAll(mission, mission1);
    }


    @Narrate("Print this before")
    private class MyMission implements Mission<Agent>{
        @Override
        public Agent accomplishAs(Agent agent) {
            return agent;
        }
    }

    @Narrate(value = "print before", after = "print after")
    private class MyMissionWithBeforeAndAfter implements Mission<Agent>{
        @Override
        public Agent accomplishAs(Agent agent) {
            agent.narrateThat("hello world");
            agent.narrateThat("info", "hello world!");
            return agent;
        }
    }
}
