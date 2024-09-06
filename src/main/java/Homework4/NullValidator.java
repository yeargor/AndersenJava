package Homework4;

import java.lang.reflect.Field;

public class NullValidator {

    public static void checkForNulls(Object obj) {

        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NullableWarning.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);

                    if (value == null) {
                        System.out.println("Variable " + field.getName() + " is null in " + aClass.getSimpleName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
