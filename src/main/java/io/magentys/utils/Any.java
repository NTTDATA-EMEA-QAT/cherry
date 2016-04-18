package io.magentys.utils;

import static io.magentys.utils.Requires.requiresNotNull;

public class Any<T> {

    private final T t;

    public Any(final T t){
        this.t = requiresNotNull(t, "You cannot create 'Any' of a null value");
    }

    public static <T> Any<T> any(final T t) {
        return new Any<T>(t);
    }

    public T get(){
        return t;
    }

    public boolean equals(final Object o){
        if(o == null) return false;
        if(o instanceof Any){
            Any any = (Any) o;
            if(any.get() == t) return true;
            return any.get().equals(t);
        }
        else return false;
    }


}
