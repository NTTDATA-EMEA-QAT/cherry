package io.magentys.screens.annotations;

/**
 * Created by kostasmamalis on 21/04/16.
 */
public interface ScreenElement {

    String getAlias();

    ScreenElement withAlias(String aliasValue);

    ScreenElement withDefaultValue(String defaultValue);

    ScreenElement withMemoryKey(String memoryKey);
}
