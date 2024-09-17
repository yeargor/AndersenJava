package tools.validators.subValidators;

import tools.validators.IValidator;
import tools.warnings.EvenWarning;
import java.lang.reflect.Field;

public class EvenValidator implements IValidator {

    public String validate(Object obj) {

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(EvenWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    EvenWarning annotation = field.getAnnotation(EvenWarning.class);
                    String message = annotation.message();

                    if (value instanceof Integer && (Integer) value % 2 != 0) {
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
