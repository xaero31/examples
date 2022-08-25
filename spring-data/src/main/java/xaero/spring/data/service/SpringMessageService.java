package xaero.spring.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xaero.spring.data.entity.SpringMessage;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PessimisticLockScope;

import static java.util.Collections.singletonMap;

@Service
@RequiredArgsConstructor
public class SpringMessageService {

    private final EntityManager entityManager;

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM SpringMessage").executeUpdate();
    }

    /**
     * required transaction mode will rollback if get runtime exception
     */
    @Transactional
    public void saveMessageWithException(SpringMessage message) {
        entityManager.persist(message);
        throw new RuntimeException("save message with exception: " + message.getMessage());
    }

    /**
     * required transaction mode will save it correctly
     */
    @Transactional
    public long saveMessage(SpringMessage message) {
        entityManager.persist(message);
        return message.getId();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveWithSupports(SpringMessage message) {
        entityManager.persist(message);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveWithMandatory(SpringMessage message) {
        entityManager.persist(message);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void saveWithNever(SpringMessage message) {
        entityManager.persist(message);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveWithNotSupported(SpringMessage message) {
        entityManager.persist(message);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveWithRequiredNew(SpringMessage message) {
        entityManager.persist(message);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveWithNested(SpringMessage message) {
        entityManager.persist(message);
    }

    /**
     * example of using pessimistic lock. requiring transaction
     */
    public SpringMessage findWithLock(long id) {
        return entityManager.find(SpringMessage.class, id, LockModeType.PESSIMISTIC_READ);
    }

    /**
     * example of using pessimistic lock with query
     */
    public SpringMessage findWithQueryLock(long id) {
        final var hql = "FROM SpringMessage s WHERE s.id = :id";
        final var query = entityManager.createQuery(hql, SpringMessage.class);

        query.setParameter("id", id);
        query.setLockMode(LockModeType.PESSIMISTIC_READ);

        return query.getSingleResult();
    }

    /**
     * requires transaction
     */
    public SpringMessage findWithResultLock(long id) {
        final var message = entityManager.find(SpringMessage.class, id);
        entityManager.lock(message, LockModeType.PESSIMISTIC_READ); // transaction required
        return message;
    }

    public void refreshWithLock(SpringMessage message) {
        entityManager.refresh(message, LockModeType.PESSIMISTIC_READ);
    }

    public SpringMessage findWithLockProps(long id) {
        return entityManager.find(SpringMessage.class, id, LockModeType.PESSIMISTIC_READ,
                singletonMap("javax.persistence.lock.scope", PessimisticLockScope.EXTENDED));
    }

    public SpringMessage findWithLockTimeout(long id) {
        return entityManager.find(SpringMessage.class, id, LockModeType.PESSIMISTIC_READ,
                singletonMap("javax.persistence.lock.timeout", 1000L)); // in ms. not all drivers support it
    }
}
