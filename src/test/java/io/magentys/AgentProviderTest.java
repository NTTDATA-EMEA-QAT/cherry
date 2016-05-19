package io.magentys;

import io.magentys.utils.Any;
import org.junit.Test;

import static io.magentys.AgentProvider.provideAgent;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class AgentProviderTest {

    CoreMemory coreMemory = CoreMemory.coreMemory();

    @Test
    public void shouldProvideAPlainVanillaAgent() throws Exception {
        final Agent ourAgent = provideAgent().get();
        assertThat(ourAgent.getMemory(),instanceOf(CoreMemory.class));
        assertThat(ourAgent.getMemory().isEmpty(),is(true));
        assertThat(ourAgent.getTools().isEmpty(), is(true));
    }

    @Test
    public void shouldCreateAnAgentWithMemory() throws Exception {
        final Agent ourAgent = provideAgent().withMemory(coreMemory).get();
        assertThat(ourAgent.getMemory(),notNullValue());
        assertThat(ourAgent.getMemory().isEmpty(), is(true));
    }

    @Test
    public void shouldCreateAnAgentWithTools() throws Exception {
        final Agent ourAgent = provideAgent().withTools(new SwissKnife(),new Jack()).get();
        assertThat(ourAgent.getTools().isEmpty(),is(false));
        assertThat(ourAgent.usingThe(SwissKnife.class),instanceOf(SwissKnife.class));
        assertThat(ourAgent.usingThe(Jack.class),instanceOf(Jack.class));
    }

    @Test
    public void shouldOverrideMemoryAndTools() throws Exception {
        final AgentProvider agentProvider = provideAgent();
        final Agent ourAgent = agentProvider
                                    .withMemory(new DummyMemory())
                                    .withTools(new Jack())
                                    .withMemory(coreMemory).get();
        assertThat(ourAgent.getMemory(),instanceOf(CoreMemory.class));
        assertThat(ourAgent.getTools().size(),is(1));

    }

    private class DummyMemory implements Memory<Double> {

        @Override
        public <VALUE> void remember(Double aDouble, VALUE value) {

        }

        @Override
        public void remember(Double aDouble, Any any) {

        }

        @Override
        public <VALUE> VALUE recall(Double aDouble, Class<VALUE> clazz) {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void transferTo(Memory<Double> memory, Double aDouble) {

        }

        @Override
        public Any recall(Double aDouble) {
            return null;
        }
    }

    private class SwissKnife{}
    private class Jack{}
}
