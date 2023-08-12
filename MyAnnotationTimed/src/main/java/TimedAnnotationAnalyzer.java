import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class TimedAnnotationAnalyzer {

    public void analyzeTimedMethods(Object object) throws InvocationTargetException, IllegalAccessException {
        if (Objects.isNull(object)) {
            throw new TimedException("Object is null");
        }
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Timed.class)) {
                method.setAccessible(true);
                Timed timed = method.getAnnotation(Timed.class);
                long maxMs = timed.value();
                long start = System.currentTimeMillis();
                method.invoke(object);
                long finish = System.currentTimeMillis() - start;
                if (finish > maxMs) {
                    throw new TimedException("Method \"" + method.getName() + "\" runs " + finish + "ms, limit is " + maxMs + " ms.");
                } else {
                    System.out.println("Методы работают сколько нужно");
                }

            }
        }
    }
}
