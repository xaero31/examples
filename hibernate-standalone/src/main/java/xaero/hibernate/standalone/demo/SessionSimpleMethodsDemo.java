package xaero.hibernate.standalone.demo;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import xaero.hibernate.standalone.model.Message;
import xaero.hibernate.standalone.model.User;

import static xaero.hibernate.standalone.provider.SessionFactoryProvider.getSessionFactory;

@Log
public class SessionSimpleMethodsDemo {

    public void saveMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                final var author = new User(1, null, null);
                final var message = new Message(0, author, "msg from java", null);
                final var anotherMessage = new Message(309, author, "another msg from java", null);

                session.save(message);
                session.save(anotherMessage);

                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something goes wrong. rollback transaction. " + e);
                session.getTransaction().rollback();
            }
        }
    }

    public void rollbackSaveMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                final var author = new User(1, null, null);
                final var message = new Message(0, author, "msg from java", null);
                final var anotherMessage = new Message(0, author, "another msg from java", null);

                session.save(message);
                session.save(anotherMessage);

                throw new HibernateException("test exception");
            } catch (HibernateException e) {
                log.severe("something goes wrong. rollback transaction. " + e);
                session.getTransaction().rollback();
            }
        }
    }

    public void selectWithoutTransaction() {
        try (final var session = getSessionFactory().openSession()) {
            final var message = session.get(Message.class, 308);
            log.info("found [" + message.getMessage() + "] message");
        }
    }

    /**
     * write will not work without transaction
     */
    public void saveWithoutTransaction() {
        try (final var session = getSessionFactory().openSession()) {
            final var author = new User(1, null, null);
            final var message = new Message(0, author, "msg without transaction", null);

            session.save(message);
        }
    }

    public void saveWithFlushWithoutTransaction() {
        try (final var session = getSessionFactory().openSession()) {
            final var author = new User(1, null, null);
            final var message = new Message(0, author, "msg without transaction with flush", null);

            session.save(message);
            session.flush(); // flush required transaction anyway
        }
    }

    /**
     * it works by finding message by message id
     */
    public void removeTransientMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                final var author = new User(1, null, null);
                final var message = new Message(309, author, "msg from java", null);

                session.delete(message);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something goes wrong. rollback transaction. " + e);
                session.getTransaction().rollback();
            }
        }
    }

    public void getUnexistingMessage() {
        try (final var session = getSessionFactory().openSession()) {
            final var message = session.get(Message.class, 9999);
            log.info("got unexisting message: " + message);
        }
    }

    /**
     * throws IllegalArgumentException if find-args doesn't match by types or id arg is null
     */
    public void findUnexistingMessage() {
        try (final var session = getSessionFactory().openSession()) {
            final var message = session.find(Message.class, 9999);
            log.info("found unexisting message: " + message);
        }
    }

    public void updateMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                final var author = new User(1, null, null);
                final var message = new Message(308, author, "updated msg", null);

                session.update(message);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something goes wrong. rollback transaction. " + e);
                session.getTransaction().rollback();
            }
        }
    }

    /**
     * should throw an exception because message with id 308 already exists in the session and we should use it instead
     * of detached message
     */
    public void updateSessionExistingMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.get(Message.class, 308);

                final var author = new User(1, null, null);
                final var detachedMessage = new Message(308, author, "updated detached msg", null);

                session.update(detachedMessage);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something goes wrong. rollback transaction. " + e);
                session.getTransaction().rollback();
            }
        }
    }
}
