package tools.validators.subValidators;

import tools.validators.IValidator;
import tools.warnings.DateWarning;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class DateValidator implements IValidator {

    public String validate(Object obj) {

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(DateWarning.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    DateWarning annotation = field.getAnnotation(DateWarning.class);
                    String message = annotation.message();

                    if (value instanceof LocalDate && ((LocalDate)value).isAfter(LocalDate.now())) {
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
