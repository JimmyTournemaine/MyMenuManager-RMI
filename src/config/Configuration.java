package config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Configuration {
    
    private static Configuration instance;
    private ClassLoader loader;
    
    private Configuration() {
        try {
            File file = new File("config");
            URL[] urls = new URL[1];
            urls[0] = file.toURI().toURL();
            loader = new URLClassLoader(urls);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public static Configuration getInstance() {
        if(instance == null)
            instance = new Configuration();
        return instance;
    }

    public ResourceBundle getBundle(String name) {
        return ResourceBundle.getBundle(name, Locale.getDefault(), loader);
    }
}
