package io.magentys;

import fj.Unit;
import io.magentys.exceptions.NotAvailableException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class AgentTest {

    @Test
    public void shouldHaveAMemory() throws Exception {
        Agent agent = Journey.anAgent();
        agent.remember("test","test1");
        assertThat(agent.recall("test",String.class), is("test1"));
    }

    @Test
    public void shouldUseAnAssignedTool() throws Exception {
         Agent agent = Journey.anAgent();
         agent.obtain(new Printer());
         assertThat(agent.usingThe(Printer.class), notNullValue());
         assertThat(agent.usingThe(Printer.class).getClass().equals(Printer.class), is(true));
    }

    @Test
    public void shouldBeAbleToPerformMissionWithoutResult() throws Exception {
        Agent agent = Journey.anAgent();
        agent.obtain(new Printer());
        assertThat(agent.perform(new PrintToSysout("test message")), is(Unit.unit()));
    }

    @Test(expected = NotAvailableException.class)
    public void shouldThrowExceptionIfToolIsNotAvailable() throws Exception {
        Journey.anAgent().perform(new PrintToSysout("test"));
    }

    private class PrintToSysout implements Mission<Unit>{

        private final String message;

        private PrintToSysout(String message) {
            this.message = message;
        }

        @Override
        public Unit accomplishAs(Agent agent) {
            Printer printer = agent.usingThe(Printer.class);
            printer.print(this.message);
            return Unit.unit();
        }
    }

    private class Printer {

        public void print(String message){
            System.out.println(message);
        }
    }


}
