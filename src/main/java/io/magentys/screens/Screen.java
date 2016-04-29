package io.magentys.screens;

import io.magentys.screens.annotations.ScreenElement;

import java.util.List;

public interface Screen {

    void addScreenElement(ScreenElement screenElement);
    Screen addScreenElements(ScreenElement... elements);
    List<? extends ScreenElement> screenElements();

}
