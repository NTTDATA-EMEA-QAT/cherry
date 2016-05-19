package io.magentys.annotations;

import io.magentys.utils.Strings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation specific to missions with narratives
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Narrate {
    public String value();
    public String after() default Strings.empty;
}
