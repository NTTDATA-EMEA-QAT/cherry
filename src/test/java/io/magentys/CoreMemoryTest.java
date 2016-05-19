package io.magentys;

import io.magentys.exceptions.NotAvailableException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CoreMemoryTest {

    private CoreMemory coreMemory;

    @Before
    public void beforeEachTest() {
        coreMemory = new CoreMemory();
    }

    @Test
    public void shouldReadAndWriteSuccessfullyAnyTypesOfData() throws Exception {
        coreMemory.remember("kostas", Integer.valueOf(5));
        assertThat(coreMemory.recall("kostas", Integer.class), is(5));
    }

    @Test
    public void shouldOverrideExistingValue() throws Exception {
        coreMemory.remember("test", "test1");
        assertThat(coreMemory.recall("test", String.class), is("test1"));
        coreMemory.remember("test", "test2");
        assertThat(coreMemory.recall("test", String.class), is("test2"));
    }

    @Test(expected = NotAvailableException.class)
    public void shouldThrowExceptionWhenNoValueOfGivenTypeIsFound() throws Exception {
        coreMemory.remember("foo", "bar");
        coreMemory.recall("foo", Integer.class);
    }

    @Test
    public void shouldReturnNullIfThereIsNoValueAssociatedWithGivenKey() throws Exception {
        final Object result = coreMemory.recall("foo", Object.class);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldTransferKnowledgeFromAnotherMemory() throws Exception {
        CoreMemory coreMemory1 = CoreMemory.coreMemory();
        CoreMemory coreMemory2 = CoreMemory.coreMemory();
        coreMemory1.remember("key1","value1");
        coreMemory1.transferTo(coreMemory2, "key1");
        String transferedValue = coreMemory2.recall("key1", String.class);
        assertThat(transferedValue, is("value1"));
    }
}
