package io.magentys.screens;

import io.magentys.exceptions.ScreenException;
import io.magentys.screens.annotations.ScreenElement;

import java.lang.reflect.Field;
import java.util.*;

public abstract class ScreenFactory {

    public <T extends Screen> T init(T screen) {
        Field[] fields = screen.getClass().getFields();
        for (Field field : fields) {
            try {
                instantiateAndRemember(screen, field);
            } catch (IllegalAccessException e) {
                throw new ScreenException("I wasn't able to instantiate your " + screen.getClass() + ". Issue is:\n" + e.getMessage());
            }
        }
        return screen;
    }


    protected abstract <T extends Screen> void instantiateAndRemember(T screen, Field field) throws IllegalAccessException;

    private static boolean isElement(Field field) {
        java.util.List<Class<?>> interfaces = Arrays.asList(field.getType().getInterfaces());
        return interfaces.contains(ScreenElement.class);
    }



}

