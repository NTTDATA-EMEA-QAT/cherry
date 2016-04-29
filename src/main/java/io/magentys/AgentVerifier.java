package io.magentys;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class AgentVerifier {

    public Agent getAgent() {
        return agent;
    }

    protected final Agent agent;

    public AgentVerifier(Agent agent) {
        this.agent = agent;
    }

    public static AgentVerifier verifyAs(Agent agent) {
       return new AgentVerifier(agent);
    }

    public <TYPE> void that(Mission<TYPE> obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj.accomplishAs(agent), objectMatcher);
    }
}
