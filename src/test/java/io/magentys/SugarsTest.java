package io.magentys;

import org.junit.Test;

import static io.magentys.AgentProvider.provideAgent;
import static io.magentys.AgentVerifier.verifyAs;
import static io.magentys.utils.Sugars.given;
import static io.magentys.utils.Sugars.then;
import static io.magentys.utils.Sugars.when;
import static org.hamcrest.core.Is.is;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class SugarsTest {



    @Test
    public void shouldReadTasty() throws Exception {
        Agent agent = provideAgent().get();
        given(agent).performs(The.firstStep).and(The.secondStep);
        when(agent).performs(The.missionUnderTest);
        then(verifyAs(agent)).that(The.result, is("Success!"));
    }

    public static class The {
        static final Mission<Agent> firstStep = new Mission<Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<Agent> secondStep = new Mission<Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<Agent> missionUnderTest = new Mission<Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<String> result = new Mission<String>() {
            @Override
            public String accomplishAs(Agent agent) {
                return "Success!";
            }
        };

    }
}
