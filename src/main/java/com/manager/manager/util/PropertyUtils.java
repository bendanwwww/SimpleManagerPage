package com.manager.manager.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyUtils {
    private static String propUrl = "prop.properties";
    private static Properties prop = new Properties();

    public PropertyUtils() {
    }

    public static String getString(String name) {
        return prop.getProperty(name);
    }

    public static void main(String[] args) throws IOException {
        Enumeration es = PropertyUtils.class.getClassLoader().getResources(propUrl);

        while(es.hasMoreElements()) {
            System.out.println(((URL)es.nextElement()).getPath());
        }

    }

    static {
        try {
            InputStream in = (new ClassPathResource(propUrl)).getInputStream();
            prop.load(in);
            in.close();
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
