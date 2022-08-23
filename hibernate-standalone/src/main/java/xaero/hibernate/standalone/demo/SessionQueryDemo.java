package xaero.hibernate.standalone.demo;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import xaero.hibernate.standalone.model.Message;

import static xaero.hibernate.standalone.provider.SessionFactoryProvider.getSessionFactory;

@Log
public class SessionQueryDemo {

    public void selectMessages() {
        try (final var session = getSessionFactory().openSession()) {
            final var hql = "FROM Message m WHERE m.id > :id";
            final var query = session.createQuery(hql, Message.class);
            query.setParameter("id", 400);
            query.setFirstResult(2); // starts with 0 by default
            query.setMaxResults(3);

            final var messageList = query.list();
            messageList.forEach(message -> log.info("got message " + message.getId() + ": " + message.getMessage()));
        }
    }

    /**
     * transaction required or we will get a TransactionRequiredException with update/delete queries
     */
    public void updateMessages() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                final var hql = "UPDATE Message m SET m.message = :message WHERE m.id = :id";
                final var query = session.createQuery(hql);

                query.setParameter("message", "updated value from hql");
                query.setParameter("id", 8);
                query.executeUpdate();

                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something went wrong. " + e);
                session.getTransaction().rollback();
            }
        }
    }
}
