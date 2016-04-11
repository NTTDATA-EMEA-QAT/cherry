package io.magentys;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CoreMemoryTest {

    @Test
    public void shouldReadAndWriteSuccessfullyAnyTypesOfData() throws Exception {
        CoreMemory coreMemory = new CoreMemory();
        coreMemory.remember("kostas",Integer.valueOf(5));
        assertThat(coreMemory.recall("kostas",Integer.class),is(5));
    }

    @Test
    public void shouldOverrideExistingValue() throws Exception {
        CoreMemory coreMemory = new CoreMemory();
        coreMemory.remember("test","test1");
        assertThat(coreMemory.recall("test",String.class),is("test1"));
        coreMemory.remember("test","test2");
        assertThat(coreMemory.recall("test",String.class),is("test2"));
    }

    /*
    throw exception when no value of specific type is found
     */

    /*
    behavior on absence of value
     */
}
