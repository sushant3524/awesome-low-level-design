import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Scheduler {

    public static void main(String[] args) throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Thread schedule = new Thread(() -> {
            try {
                scheduler.executeTasksInQueue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread createTask = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Task task = scheduler.createNewTask(String.valueOf(i), (long) (Math.random() * 20000));
                scheduler.addTaskInQueue(task);
                try {
                    long sleep = (long) (Math.random() * 10000);
                    System.out.println("Sleeping for " + sleep);
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        schedule.start();
        createTask.start();
        createTask.join();
        System.out.println(scheduler.getTasksScheduledCount());
        scheduler.setAllTaskCreated(true);
        schedule.join();
        System.out.println(scheduler.getTasksRunCount());
    }

    PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingLong(Task::getStartAfter));
    AtomicBoolean allTaskCreated = new AtomicBoolean(false);
    Object lock = new Object();
    private AtomicLong tasksRunCount = new AtomicLong(0);
    private AtomicLong tasksScheduledCount = new AtomicLong(0);

    public Task createNewTask(String id, long delay) {
        return new Task(id, delay);
    }

    public void addTaskInQueue(Task task) {
        synchronized (lock) {
            queue.add(task);
            tasksScheduledCount.incrementAndGet();
            System.out.println("task scheduled for task " + task.getId() + "start after " + new Date(task.getStartAfter()));
            lock.notifyAll();
        }
    }

    public void executeTasksInQueue() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 500, 100, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));
        while (!allTaskCreated.get()) {
            synchronized (lock) {
                lock.wait();
                while (!queue.isEmpty()) {
                    Task latest = queue.peek();
                    long startAfter = latest.getStartAfter();
                    if (System.currentTimeMillis() >= startAfter) {
                        threadPoolExecutor.submit(latest);
                        queue.poll();
                        tasksRunCount.incrementAndGet();
                    } else {
                        long diff = startAfter - System.currentTimeMillis();
                        lock.wait(diff);
                    }
                }
            }
        }
        threadPoolExecutor.shutdown();
    }

    public AtomicLong getTasksRunCount() {
        return tasksRunCount;
    }

    public void setTasksRunCount(AtomicLong tasksRunCount) {
        this.tasksRunCount = tasksRunCount;
    }

    public AtomicBoolean getAllTaskCreated() {
        return allTaskCreated;
    }

    public void setAllTaskCreated(boolean value) {
        allTaskCreated.set(value);
    }

    public AtomicLong getTasksScheduledCount() {
        return tasksScheduledCount;
    }

    public void setTasksScheduledCount(AtomicLong tasksScheduledCount) {
        this.tasksScheduledCount = tasksScheduledCount;
    }

    public class Task implements Runnable {

        String id;
        long startAfter;

        public Task(String id, long delay) {
            this.id = id;
            this.startAfter = System.currentTimeMillis() + delay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getStartAfter() {
            return startAfter;
        }

        public void setStartAfter(long startAfter) {
            this.startAfter = startAfter;
        }

        @Override
        public void run() {
            System.out.println("Task with id: " + id + " is run. startAfter " + new Date(startAfter));
        }
    }

}
