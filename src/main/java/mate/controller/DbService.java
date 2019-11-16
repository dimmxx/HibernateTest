package mate.controller;

import mate.model.UserGroup;
import mate.model.UsersDataSet;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class DbService {

    private final static String hibernate_show_sql = "true";
    private final static String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DbService() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = getSessionFactory(configuration);
    }

    public void addUser(String name) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new UsersDataSet(name));
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }

        //return 0;
    }

    public UsersDataSet getUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            return (UsersDataSet) session.get(UsersDataSet.class, id);
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public long getUserByName(String name) {
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(UsersDataSet.class);
            return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return 0;
    }



    public void printConnectionInfo() {

        try {
            Connection connection = sessionFactory.getSessionFactoryOptions().
                    getServiceRegistry().
                    getService(ConnectionProvider.class).
                    getConnection();
            System.out.println("DatabaseProductName: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DataBaseProductVersion: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("DriverName: " + connection.getMetaData().getDriverName());
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("AutoCommit: " + connection.getAutoCommit());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.addAnnotatedClass(UserGroup.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://3.133.58.254:3306/hibernate_test?user=remote_user&password=remote_user&useSSL=false");
        //configuration.setProperty("hibernate.connection.username", "remote_user");
        //configuration.setProperty("hibernate.connection.password", "remote_user");
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
