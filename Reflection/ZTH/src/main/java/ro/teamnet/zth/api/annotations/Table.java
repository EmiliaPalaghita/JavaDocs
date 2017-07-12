package ro.teamnet.zth.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {

    String name() default "";
}
