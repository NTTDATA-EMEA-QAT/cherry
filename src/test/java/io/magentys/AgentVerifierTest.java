package io.magentys;

import org.junit.Test;

import static io.magentys.AgentProvider.agent;
import static io.magentys.AgentVerifier.verifyAs;
import static org.hamcrest.core.Is.is;

/**
 * Created by kostasmamalis on 29/04/16.
 */
public class AgentVerifierTest {

    Mission reverseStringOf(final String s) {
        return new Mission<String>() {
            @Override
            public String accomplishAs(Agent agent) {
                return new StringBuilder(s).reverse().toString();
            }
        };
    }

    @Test
    public void shouldPerformVerificationsAsAgent() throws Exception {
        verifyAs(agent()).that(reverseStringOf("string"), is("gnirts"));
    }
}
