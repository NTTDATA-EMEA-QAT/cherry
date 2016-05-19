package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static io.magentys.AgentProvider.agent;
import static io.magentys.AgentProvider.provideAgent;
import static io.magentys.AgentTest.Print.printsTheDocument;
import static io.magentys.AgentTest.Printer.aPrinter;
import static io.magentys.AgentTest.Scan.scansThe;
import static io.magentys.AgentTest.Scanner.aScanner;
import static io.magentys.utils.Sugars.and;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class AgentTest {

    private Agent agent;

    @Rule
    public ExpectedException notAvailableToBeThrown = ExpectedException.none();

    @Before
    public void beforeEachTest() {
        agent = agent();
    }

    @Test
    public void shouldHaveAMemory() throws Exception {
        agent.keepsInMind("test", "test1");
        assertThat(agent.recalls("test", String.class), is("test1"));
    }

    @Test
    public void shouldUseAnAssignedTool() throws Exception {
        agent.obtains(new Printer());
        assertThat(agent.usingThe(Printer.class), notNullValue());
        assertThat(agent.usingThe(Printer.class).getClass().equals(Printer.class), is(true));
    }

    @Test
    public void shouldBeAbleToPerformMissionWithoutResult() throws Exception {
        agent.obtains(aPrinter());
        assertThat(agent.performs(printsTheDocument()), is(agent));
    }

    @Test
    public void shouldThrowExceptionIfToolIsNotAvailable() throws Exception {
        notAvailableToBeThrown.expect(NotAvailableException.class);
        notAvailableToBeThrown.expectMessage("I don't know this skill: class io.magentys.AgentTest$Printer");

        agent.performs(new Print());
    }

    @Test
    public void shouldTransferKnowledgeToAnotherAgent() throws Exception {
        String randomKey = UUID.randomUUID().toString();
        String randomValue = UUID.randomUUID().toString();
        agent.keepsInMind(randomKey,randomValue);
        Agent anotherAgent = provideAgent().get();
        anotherAgent.askThe(agent, randomKey);
        assertThat(anotherAgent.recalls(randomKey, String.class), is(randomValue));

    }

    @Test
    public void shouldAddSyntacticalSugar() throws Exception {
        final Agent Tom = agent();
        Tom.obtains(aPrinter(), and(aScanner()))
                .andHe(scansThe("important Document"), and(printsTheDocument()));


    }

    public static class Print implements Mission<Agent> {


        public static Print printsTheDocument() {
            return new Print();
        }

        @Override
        public Agent accomplishAs(final Agent agent) {
            final Printer printer = agent.usingThe(Printer.class);
            printer.print(agent.recalls("ScannedDocument", String.class));
            return agent;
        }
    }

    public static class Scan implements Mission<Agent> {

        private final String document;

        public Scan(final String document) {
            this.document = document;
        }

        public static Scan scansThe(final String document) {
            return new Scan(document);
        }

        @Override
        public Agent accomplishAs(final Agent agent) {
            agent.keepsInMind("ScannedDocument", this.document);
            return agent;
        }
    }

    public static class Printer {

        public static Printer aPrinter() {
            return new Printer();
        }

        public void print(final String message) {
            System.out.println(message);
        }
    }

    public static class Scanner {

        public static Scanner aScanner() {
            return new Scanner();
        }

        public <ITEM> ITEM scan(final ITEM item) {
            return item;
        }

    }


}
