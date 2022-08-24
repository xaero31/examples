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
            query.setHint("org.hibernate.cacheable", true); // hint to cache the query
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

    public void selectFromSecondLevelCacheDemo() {
        var messageId = 0;

        try (final var session = getSessionFactory().openSession()) {
            final var hql = "FROM Message m WHERE m.id > :id";
            final var query = session.createQuery(hql, Message.class);
            query.setParameter("id", 400);

            final var messageList = query.list();
            messageId = messageList.stream().findFirst().orElseThrow().getId();
        }

        try (final var session = getSessionFactory().openSession()) {
            /*
              it is sufficient to understand that when we use hql or sql query directly, it is compiles into sql
              query anyway, but if we are trying to find an entity from the session - it will not use sql query if
              entity exists in the L2 cache
             */
            final var message = session.get(Message.class, messageId);
            log.info("found message from cache " + message.getId() + ": " + message.getMessage());
        }
    }
}
