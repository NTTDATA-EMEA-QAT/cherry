package io.magentys.utils;

public class Clazz {

    public static <OBJECT> boolean isClassOrSubclass(final Class<OBJECT> clazz, final Class<?> classOfAny) {
        return classOfAny.equals(clazz) ||
                clazz.isAssignableFrom(classOfAny) ||
                fromSuperClass(clazz, classOfAny) || extendedFrom(clazz, classOfAny);
    }

    private static <OBJECT> boolean fromSuperClass(final Class<OBJECT> clazz, final Class<?> classOfAny) {
        if (classOfAny.getSuperclass() == null) {
            return false;
        }
        return classOfAny.getSuperclass().equals(clazz);
    }

    private static <OBJECT> boolean extendedFrom(final Class<OBJECT> clazz, final Class<?> classOfAny) {
        if (clazz.getSuperclass() == null) {
            return false;
        }
        return clazz.getSuperclass().equals(classOfAny);
    }

}
