package xaero.spring.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xaero.spring.data.entity.SpringMessage;

@Service
@RequiredArgsConstructor
public class SpringMessageServiceWrapper {

    private final SpringMessageService messageService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveWithOuterRequiredAndInnerSupport(SpringMessage message) {
        messageService.saveWithSupports(message);
    }

    @Transactional
    public void saveWithMandatory(SpringMessage message) {
        messageService.saveWithMandatory(message);
    }

    @Transactional
    public void saveWithNever(SpringMessage message) {
        messageService.saveWithNever(message);
    }

    @Transactional
    public void saveWithNotSupports(SpringMessage message) {
        messageService.saveWithSupports(new SpringMessage(0, "save before 'not supports'"));
        messageService.saveMessage(new SpringMessage(0, "save before 'not supports' required"));
        messageService.saveWithNotSupported(message); // breaks outer transaction (?)
        messageService.saveWithSupports(new SpringMessage(0, "save after 'not supports'"));
        messageService.saveMessage(new SpringMessage(0, "save after 'not supports' required"));
    }

    @Transactional
    public void saveWithRequiredNew(SpringMessage message) {
        messageService.saveWithSupports(new SpringMessage(0, "save before 'required new'"));
        messageService.saveWithRequiredNew(message); // create new inner transaction. not break outer
        messageService.saveWithSupports(new SpringMessage(0, "save after 'required new'"));
    }

    @Transactional
    public void saveWithNested(SpringMessage message) {
        messageService.saveWithSupports(new SpringMessage(0, "save before 'nested'"));
        messageService.saveWithNested(message);
        messageService.saveWithSupports(new SpringMessage(0, "save after 'nested'"));
    }

    @Transactional
    public SpringMessage findWithLock(long id) {
        return messageService.findWithLock(id);
    }
}
