package xaero.hibernate.standalone.provider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

import static java.util.Objects.isNull;

public class SessionFactoryProvider {

    private static SessionFactory SESSION_FACTORY;

    public static SessionFactory getSessionFactory() {
        if (isNull(SESSION_FACTORY)) {
            SESSION_FACTORY = new Configuration()
                    .configure(new File("./hibernate-standalone/src/main/resources/hibernate.cfg.xml"))
                    .buildSessionFactory();
        }

        return SESSION_FACTORY;
    }
}
