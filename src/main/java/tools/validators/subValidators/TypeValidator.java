package tools.validators.subValidators;

import tools.validators.IValidator;
import tools.warnings.TypeWarning;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class TypeValidator  implements IValidator {

    public String validate(Object obj) {

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(TypeWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    TypeWarning annotation = field.getAnnotation(TypeWarning.class);
                    String message = annotation.message();
                    List<String> types = Arrays.asList("DAY", "WEEK", "MONTH", "YEAR");

                    if (value instanceof String && !types.contains(value)) {
                        return message;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }
}
