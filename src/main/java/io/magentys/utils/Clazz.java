package io.magentys.utils;

public class Clazz {

    public static <OBJECT> boolean isClassOrSubclass(Class<OBJECT> clazz, Class<?> classOfAny) {
        return classOfAny.equals(clazz) ||
                classOfAny.getSuperclass().equals(clazz) ||
                clazz.isAssignableFrom(classOfAny);
    }
}
