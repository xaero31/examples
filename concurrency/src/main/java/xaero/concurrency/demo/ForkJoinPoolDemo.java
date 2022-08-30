package xaero.concurrency.demo;

import lombok.extern.java.Log;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.currentThread;
import static java.util.stream.Collectors.joining;

@Log
public class ForkJoinPoolDemo {

    /**
     * each task defines threshold after which it could divide task into few subtasks and run it separatedly
     */
    public static void main(String[] args) {
        final var recursiveAction = new CustomRecursiveAction("some.text.through.the.dot.sentence.come.on");
        final var recursiveTask = new CustomRecursiveTask("some.text.through.the.dot.sentence.come.on");

        /*
         need to join after
         getJavaEightPool().submit(recursiveAction);
         getJavaEightPool().execute(recursiveAction);
         recursiveAction.join();
         */

        getJavaEightPool().invoke(recursiveAction);
        log.info("recursive task result:\n" + getJavaEightPool().invoke(recursiveTask));
    }

    /**
     * @param parallelism - how many processor cores will be used for task completing
     */
    private static ForkJoinPool getJavaSevenPool(int parallelism) {
        return new ForkJoinPool(parallelism);
    }

    /**
     * using the predefined common pool reduces resource consumption since this discourages the creation of a separate
     * thread pool per task
     */
    private static ForkJoinPool getJavaEightPool() {
        return ForkJoinPool.commonPool();
    }

    @Log
    private static class CustomRecursiveAction extends RecursiveAction {

        private static final String DOT = ".";

        private final String inputText;

        public CustomRecursiveAction(String inputText) {
            this.inputText = inputText;
        }

        @Override
        protected void compute() {
            if (inputText.contains(DOT)) {
                ForkJoinTask.invokeAll(splitToSubtasks());
            } else {
                process(inputText);
            }
        }

        private List<CustomRecursiveAction> splitToSubtasks() {
            final var dotIndex = inputText.indexOf(DOT);
            return List.of(
                    new CustomRecursiveAction(inputText.substring(0, dotIndex)),
                    new CustomRecursiveAction(inputText.substring(dotIndex + 1))
            );
        }

        private void process(String inputText) {
            log.info("text without dot: [" + inputText + "] from thread [" + currentThread().getName() + "]");
        }
    }

    private static class CustomRecursiveTask extends RecursiveTask<String> {

        private static final String DELIMITER = "-";
        private static final String DOT = ".";

        private final String inputText;

        private CustomRecursiveTask(String inputText) {
            this.inputText = inputText;
        }

        @Override
        protected String compute() {
            if (inputText.contains(DOT)) {
                return ForkJoinTask.invokeAll(splitToSubtasks())
                        .stream()
                        .map(ForkJoinTask::join)
                        .collect(joining(DELIMITER));
            } else {
                return process(inputText);
            }
        }

        private List<CustomRecursiveTask> splitToSubtasks() {
            final var dotIndex = inputText.indexOf(DOT);
            return List.of(
                    new CustomRecursiveTask(inputText.substring(0, dotIndex)),
                    new CustomRecursiveTask(inputText.substring(dotIndex + 1))
            );
        }

        private String process(String inputText) {
            return inputText.toUpperCase();
        }
    }
}
