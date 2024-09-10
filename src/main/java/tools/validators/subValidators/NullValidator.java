package tools.validators.subValidators;

import tools.validators.IValidator;
import tools.warnings.NullableWarning;
import java.lang.reflect.Field;

public class NullValidator implements IValidator {

    public String validate(Object obj) {

        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    NullableWarning annotation = field.getAnnotation(NullableWarning.class);
                    String message = annotation.message();

                    if (value == null) {
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
