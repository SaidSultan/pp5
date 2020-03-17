package com.said.util;

import com.said.factory.UserDaoFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperty() {
        Properties properties = new Properties();
        try {
            properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            System.out.println("ошибка чтения файла properties");
            e.printStackTrace();
        }
        return properties;
    }
}
