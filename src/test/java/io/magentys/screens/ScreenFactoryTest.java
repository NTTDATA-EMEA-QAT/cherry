package io.magentys.screens;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ScreenFactoryTest {


    Screen myScreen = mock(Screen.class);
    ScreenFactory myScreenFactory = mock(ScreenFactory.class, Mockito.CALLS_REAL_METHODS);

    @Test
    public void shouldInitializeAScreen() throws Exception {
        Screen aScreen = myScreenFactory.init(myScreen);
        assertThat(aScreen, CoreMatchers.notNullValue());
    }
}
