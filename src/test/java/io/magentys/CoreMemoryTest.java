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


}
