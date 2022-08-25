package xaero.hibernate.standalone;

import lombok.extern.java.Log;
import xaero.hibernate.standalone.demo.CriteriaAPIDemo;
import xaero.hibernate.standalone.demo.NativeSQLDemo;
import xaero.hibernate.standalone.demo.SessionQueryDemo;
import xaero.hibernate.standalone.demo.SessionSimpleMethodsDemo;

@Log
public class Application {

    public static void main(String[] args) {
//        runSimpleSessionMethodsDemo();
//        runSessionQueryDemo();
//        runCriteriaAPIDemo();
//        runNativeSQLDemo();
    }

    private static void runSimpleSessionMethodsDemo() {
        final var simpleMethodsDemo = new SessionSimpleMethodsDemo();

        simpleMethodsDemo.selectWithoutTransaction();

        simpleMethodsDemo.saveMessage();
        simpleMethodsDemo.saveWithoutTransaction();
        simpleMethodsDemo.saveWithFlushWithoutTransaction();
        simpleMethodsDemo.rollbackSaveMessage();

        simpleMethodsDemo.removeTransientMessage();

        simpleMethodsDemo.getUnexistingMessage();
        simpleMethodsDemo.findUnexistingMessage();

        simpleMethodsDemo.updateMessage();
        simpleMethodsDemo.updateSessionExistingMessage();
    }

    private static void runSessionQueryDemo() {
        final var queryDemo = new SessionQueryDemo();

        queryDemo.selectMessages();
        queryDemo.updateMessages();
        queryDemo.selectFromSecondLevelCacheDemo();
    }

    private static void runCriteriaAPIDemo() {
        final var apiDemo = new CriteriaAPIDemo();

        apiDemo.selectMessages();
        apiDemo.updateMessage();
        apiDemo.deleteMessage();
    }

    private static void runNativeSQLDemo() {
        final var sqlDemo = new NativeSQLDemo();

        sqlDemo.selectMessages();
        sqlDemo.selectEntityMessages();
    }
}
