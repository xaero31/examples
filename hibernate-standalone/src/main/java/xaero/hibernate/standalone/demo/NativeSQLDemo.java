package xaero.hibernate.standalone.demo;

import lombok.extern.java.Log;
import xaero.hibernate.standalone.model.Message;

import static xaero.hibernate.standalone.provider.SessionFactoryProvider.getSessionFactory;

@Log
public class NativeSQLDemo {

    public void selectMessages() {
        try (final var session = getSessionFactory().openSession()) {
            final var sql = "SELECT id, message FROM hibernate.messages";
            final var nativeQuery = session.createNativeQuery(sql);
            final var resultList = nativeQuery.getResultList();
            resultList.forEach(result -> log.info("get message " + ((Object[]) result)[0] +
                    ": " + ((Object[]) result)[1]));
        }
    }

    public void selectEntityMessages() {
        try (final var session = getSessionFactory().openSession()) {
            final var sql = "SELECT * FROM hibernate.messages WHERE id > :id";
            final var nativeQuery = session.createNativeQuery(sql, Message.class);
            nativeQuery.setParameter("id", 400);

            final var resultList = nativeQuery.getResultList();
            resultList.forEach(message -> log.info("get message " + message.getId() +
                    ": " + message.getMessage()));
        }
    }
}
