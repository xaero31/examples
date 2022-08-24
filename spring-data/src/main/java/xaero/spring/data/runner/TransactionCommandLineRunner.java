package xaero.spring.data.runner;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xaero.spring.data.entity.SpringMessage;
import xaero.spring.data.service.SpringMessageService;
import xaero.spring.data.service.SpringMessageServiceWrapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionCommandLineRunner implements CommandLineRunner {

    private final SpringMessageService messageService;
    private final SpringMessageServiceWrapper wrapper;

    @Override
    public void run(String... args) throws Exception {
        // clear table before experiments
        Try.run(messageService::deleteAll);

        // will rollback and not save in db
        Try.run(() -> messageService.saveMessageWithException(new SpringMessage(0, "message with exception")));

        // will save in db successfully
        Try.run(() -> messageService.saveMessage(new SpringMessage(0, "message without exception")));

        // will not save in db because doesn't have transaction for writing
        Try.run(() -> messageService.saveWithSupports(new SpringMessage(0, "message with supports")));

        // will save in db, cuz have transaction for writing
        Try.run(() -> wrapper.saveWithOuterRequiredAndInnerSupport(
                new SpringMessage(0, "message with outer required inner supports")));

        // will throw an exception because don't have existing transaction
        Try.run(() -> messageService.saveWithMandatory(new SpringMessage(0, "message with mandatory")))
                .onFailure(e -> log.error("error save message with mandatory", e));

        // will save with outer transaction
        Try.run(() -> wrapper.saveWithMandatory(
                new SpringMessage(0, "message with outer required inner mandatory")));

        // will silently not save because don't have transaction
        Try.run(() -> messageService.saveWithNever(new SpringMessage(0, "message with never")))
                .onFailure(e -> log.error("error save message with never", e));

        // will throw an exception because have transaction
        Try.run(() -> wrapper.saveWithNever(new SpringMessage(0, "message with outer required inner never")))
                .onFailure(e -> log.error("error save message with outer required inner never", e));

        // will not save anything. 'not supports' suspend transaction
        Try.run(() -> wrapper.saveWithNotSupports(new SpringMessage(0, "message with not supported")));

        // creating inner transaction without breaking outer
        Try.run(() -> wrapper.saveWithRequiredNew(new SpringMessage(0, "message with required new")));

        // spring jpa transaction manager support nested transaction only with jdbc
        Try.run(() -> wrapper.saveWithNested(new SpringMessage(0, "message with nested")));
    }
}
