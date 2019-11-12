package mate.controller;

import mate.model.UsersDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class DbService {

    private final static String hibernate_show_sql = "true";
    private final static String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DbService() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = getSessionFactory(configuration);
    }

    private long addUser(UsersDataSet usersDataSet) {
        return 0;
    }

    private UsersDataSet getUserById(long id) {
        return null;
    }

    private UsersDataSet getUserByName(String name) {
        return null;
    }

//String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//        String DB_TYPE = "jdbc:mysql://";
//        String HOST_NAME = "3.133.58.254"; //aws streamddata0719@gmail.com
//        String PORT = "3306";
//        String DB_NAME = "testdb";
//        String USER = "remote_user";
//        String PASS = "remote_user";
//
//        try {
//            DriverManager.registerDriver((Driver) Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance());
//            StringBuilder urlString = new StringBuilder();
//            urlString.
//                    append(DB_TYPE).
//                    append(HOST_NAME + ":").
//                    append(PORT + "/").
//                    append(DB_NAME + "?").
//                    append("user=" + USER + "&").
//                    append("password=" + PASS + "&useSSL=false");


    public void printConnectionInfo() throws SQLException {

        Connection connection = sessionFactory.getSessionFactoryOptions().
                getServiceRegistry().
                getService(ConnectionProvider.class).
                getConnection();
        System.out.println("DatabaseProductName: " + connection.getMetaData().getDatabaseProductName());
        System.out.println("DataBaseProductVersion: " + connection.getMetaData().getDatabaseProductVersion());
        System.out.println("DriverName: " + connection.getMetaData().getDriverName());
        System.out.println("URL: " + connection.getMetaData().getURL());
        System.out.println("AutoCommit: " + connection.getAutoCommit());
    }
    

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://3.133.58.254:3306/hibernate_example");
        configuration.setProperty("hibernate.connection.username", "remote_user");
        configuration.setProperty("hibernate.connection.password", "remote_user");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static final SessionFactory getSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
