package io.magentys.utils;

import java.util.ArrayList;
import java.util.List;

public class Clazz {

    public static <OBJECT> boolean isClassOrSubclass(Class<OBJECT> clazz, Class<?> classOfAny) {
        return  classOfAny.equals(clazz) ||
                clazz.isAssignableFrom(classOfAny) ||
                fromSuperClass(clazz, classOfAny) || extendedFrom(clazz, classOfAny);
    }

    private static <OBJECT> boolean fromSuperClass(Class<OBJECT> clazz, Class<?> classOfAny) {
        if(classOfAny.getSuperclass() == null) return false;
        return classOfAny.getSuperclass().equals(clazz);
    }

    private static <OBJECT> boolean extendedFrom(Class<OBJECT> clazz, Class<?> classOfAny){
        if(clazz.getSuperclass() == null) return false;
        return clazz.getSuperclass().equals(classOfAny);
    }

}
