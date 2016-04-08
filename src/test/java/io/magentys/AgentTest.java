package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static io.magentys.AgentTest.Print.printsTheDocument;
import static io.magentys.AgentTest.Printer.aPrinter;
import static io.magentys.AgentTest.Scan.scansThe;
import static io.magentys.AgentTest.Scanner.aScanner;
import static io.magentys.Journey.anAgent;
import static io.magentys.utils.Sugars.and;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class AgentTest {

    @Test
    public void shouldHaveAMemory() throws Exception {
        Agent agent = anAgent();
        agent.remember("test","test1");
        assertThat(agent.recall("test",String.class), is("test1"));
    }

    @Test
    public void shouldUseAnAssignedTool() throws Exception {
         Agent agent = anAgent();
         agent.obtains(new Printer());
         assertThat(agent.usingThe(Printer.class), notNullValue());
         assertThat(agent.usingThe(Printer.class).getClass().equals(Printer.class), is(true));
    }

    @Test
    public void shouldBeAbleToPerformMissionWithoutResult() throws Exception {
        Agent agent = anAgent();
        agent.obtains(aPrinter());
        assertThat(agent.performs(printsTheDocument()), is(agent));
    }

    @Rule public ExpectedException notAvailableToBeThrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionIfToolIsNotAvailable() throws Exception {
        notAvailableToBeThrown.expect(NotAvailableException.class);
        notAvailableToBeThrown.expectMessage("I don't know this skill: class io.magentys.AgentTest$Printer");
        anAgent().performs(new Print());
    }

    @Test
    public void shouldAddSyntacticalSugar() throws Exception {
        Agent Tom = anAgent();
        Tom.obtains(aPrinter(), and(aScanner()))
                .andHe( scansThe("important Document"), and(printsTheDocument()));


    }

    public static class Print implements Mission<Agent>{


        public static Print printsTheDocument(){
            return new Print();
        }

        @Override
        public Agent accomplishAs(Agent agent) {
            Printer printer = agent.usingThe(Printer.class);
            printer.print(agent.recall("ScannedDocument", String.class));
            return agent;
        }
    }

    public static class Scan implements Mission<Agent> {

        private final String document;

        public Scan(String document) {
            this.document = document;
        }

        public static Scan scansThe(String document) {
            return new Scan(document);
        }

        @Override
        public Agent accomplishAs(Agent agent) {
            agent.remember("ScannedDocument", this.document);
            return agent;
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

    public static class Scanner {

        public static Scanner aScanner() { return new Scanner(); }

        public <ITEM> ITEM scan(ITEM item) { return item; }

    }


}
