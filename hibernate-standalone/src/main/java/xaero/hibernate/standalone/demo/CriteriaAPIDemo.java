package xaero.hibernate.standalone.demo;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import xaero.hibernate.standalone.model.Message;

import static xaero.hibernate.standalone.provider.SessionFactoryProvider.getSessionFactory;

@Log
public class CriteriaAPIDemo {

    public void selectMessages() {
        try (final var session = getSessionFactory().openSession()) {
            final var criteriaBuilder = session.getCriteriaBuilder();
            final var criteriaQuery = criteriaBuilder.createQuery(Message.class);
            final var criteriaRoot = criteriaQuery.from(Message.class);
            criteriaQuery.select(criteriaRoot)
                    .where(criteriaBuilder.gt(criteriaRoot.get("id"), 400))
                    .orderBy(criteriaBuilder.desc(criteriaRoot.get("id")));

            final var query = session.createQuery(criteriaQuery);
            query.getResultList().forEach(message -> log.info("criteria message " + message.getId() +
                    ": " + message.getMessage()));
        }
    }

    public void updateMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                final var criteriaBuilder = session.getCriteriaBuilder();
                final var criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Message.class);
                final var criteriaRoot = criteriaUpdate.from(Message.class);

                criteriaUpdate.set("message", "message from criteria");
                criteriaUpdate.where(criteriaBuilder.equal(criteriaRoot.get("id"), 458));

                session.beginTransaction();
                session.createQuery(criteriaUpdate).executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                log.severe("something went wrong. " + e);
                session.getTransaction().rollback();
            }
        }
    }

    public void deleteMessage() {
        try (final var session = getSessionFactory().openSession()) {
            try {
                final var criteriaBuilder = session.getCriteriaBuilder();
                final var criteriaDelete = criteriaBuilder.createCriteriaDelete(Message.class);
                final var criteriaRoot = criteriaDelete.from(Message.class);

                criteriaDelete.where(criteriaBuilder.equal(criteriaRoot.get("id"), 108));

                session.beginTransaction();
                session.createQuery(criteriaDelete).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                log.severe("something went wrong. " + e);
                session.getTransaction().rollback();
            }
        }
    }
}
