package io.mcore;

import fj.Unit;
import io.mcore.exceptions.NotAvailableException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static io.mcore.AgentTest.PrintToSysout.printToSysout;
import static io.mcore.AgentTest.Printer.aPrinter;
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
        agent.obtain(aPrinter());
        assertThat(agent.performs(printToSysout("test message")), is(Unit.unit()));
    }

    @Rule public ExpectedException notAvailableToBeThrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionIfToolIsNotAvailable() throws Exception {
        notAvailableToBeThrown.expect(NotAvailableException.class);
        notAvailableToBeThrown.expectMessage("I don't know this skill: class io.mcore.AgentTest$Printer");
        Journey.anAgent().performs(new PrintToSysout("test"));
    }

    public static class PrintToSysout implements Mission<Unit>{

        private final String message;

        private PrintToSysout(String message) {
            this.message = message;
        }

        public static PrintToSysout printToSysout(String message){
            return new PrintToSysout(message);
        }

        @Override
        public Unit accomplishAs(Agent agent) {
            Printer printer = agent.usingThe(Printer.class);
            printer.print(this.message);
            return Unit.unit();
        }
    }

    public static class Printer {

        public static Printer aPrinter(){
            return new Printer();
        }

        public void print(String message){
            System.out.println(message);
        }
    }


}
