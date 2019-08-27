import com.busecarik.Food;
import com.busecarik.MenuItems;
import com.busecarik.Time;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MainClass {
    private static final String packageName = "com.busecarik";

    public static void main(String[] args) throws NoSuchFieldException {

        ArrayList<Class> classes = getClasses(packageName);

        ArrayList<MenuItems> listBean = new ArrayList<MenuItems>();

        Food food = null;
        int time = 0;

        for (Class c: classes) {
            if (c.isAnnotationPresent(Food.class)) {
                food = (Food)c.getAnnotation(Food.class);
                time = totalTime(c);
                listBean.add(new MenuItems(c.getName(), food.price(), time));
            }
        }
    }

    private static int totalTime(Class<?> c) {
        int total = 0;

        Method[] methods = c.getDeclaredMethods();
        Time time = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Time.class)) {
                time = method.getAnnotation(Time.class);
                total += time.takes();
            }
        }

        return total;
    }

    private static ArrayList<Class> getClasses(final String packageName){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            try {
                classes.addAll(findClasses(directory, packageName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;

    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
