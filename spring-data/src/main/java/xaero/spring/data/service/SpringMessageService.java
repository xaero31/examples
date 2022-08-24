package xaero.spring.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xaero.spring.data.entity.SpringMessage;

import javax.persistence.EntityManager;

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
    public void saveMessage(SpringMessage message) {
        entityManager.persist(message);
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
}
