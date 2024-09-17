package tools.warnings;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NullableWarning {
    String message() default "Value cannot be null";
}
