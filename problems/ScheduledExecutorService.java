import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduledExecutorService {
    ExecutorService threadPool;
    PriorityQueue<Task> tasks;
    Lock lock;
    Condition newTaskAdded;

    public ScheduledExecutorService() {
        threadPool = Executors.newFixedThreadPool(5);
        tasks = new PriorityQueue<>(Comparator.comparingLong(Task::getRunAfter));
        lock = new ReentrantLock();
        newTaskAdded = lock.newCondition();
    }

    public void start() throws InterruptedException, ExecutionException {
        while (true) {
            lock.lock();
            if (tasks.isEmpty()) {
                newTaskAdded.await();
            }
            while(!tasks.isEmpty()) {
                Task task = tasks.peek();
                long currentTimeMillis = System.currentTimeMillis();
                if (task.getRunAfter() <= currentTimeMillis) {
                    break;
                }
                newTaskAdded.await(task.getRunAfter() - currentTimeMillis, TimeUnit.MILLISECONDS);
            }
            Task taskToExecute = tasks.poll();
            if (taskToExecute.getTaskType() == 2) {
                threadPool.submit(taskToExecute.getRunnable());
                Task newTask = new Task(taskToExecute.getRunnable(), System.currentTimeMillis() + taskToExecute.getUnit().toMillis(taskToExecute.getPeriod()), 2, taskToExecute.getPeriod(), null, taskToExecute.getUnit());
                tasks.add(newTask);
            } else if (taskToExecute.getTaskType() == 3) {
                Future<?> submit = threadPool.submit(taskToExecute.getRunnable());
                submit.get();
                Task newTask = new Task(taskToExecute.getRunnable(), System.currentTimeMillis() + taskToExecute.getUnit().toMillis(taskToExecute.getDelay()), 3, null, taskToExecute.getDelay(), taskToExecute.getUnit());
                tasks.add(newTask);
            } else {
                threadPool.submit(taskToExecute.getRunnable());
            }
            lock.unlock();
        }

    }
    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    public void schedule(Runnable command, long delay, TimeUnit unit) {
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */
    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
    }

    /*
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given delay between the termination of one execution and the commencement of the next.
     */
    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
    }

    public class Task {
        Runnable runnable;
        long runAfter;
        int taskType;
        Long period;
        Long delay;
        TimeUnit unit;
        public Task(Runnable runnable, long runAfter, int taskType, Long period, Long delay, TimeUnit unit) {
            this.runnable = runnable;
            this.runAfter = runAfter;
            this.taskType = taskType;
            this.period = period;
            this.delay = delay;
            this.unit = unit;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        public long getRunAfter() {
            return runAfter;
        }

        public void setRunAfter(long runAfter) {
            this.runAfter = runAfter;
        }

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        public Long getPeriod() {
            return period;
        }

        public void setPeriod(Long period) {
            this.period = period;
        }

        public Long getDelay() {
            return delay;
        }

        public void setDelay(Long delay) {
            this.delay = delay;
        }

        public TimeUnit getUnit() {
            return unit;
        }

        public void setUnit(TimeUnit unit) {
            this.unit = unit;
        }
    }
}