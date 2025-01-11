import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DependantTaskExecutor {
    public static void main(String[] args) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Integer sudha = (Integer) objectObjectHashMap.get("sudha");
        System.out.println(sudha);
    }

    class Task {
        String taskId() {
            return null;
        }

        void doWork() throws Exception {

        }
    }
    Set<String> completedTaskIds = Collections.synchronizedSet(new HashSet<>());
    Set<String> encounteredTaskIds = new HashSet<>();

    boolean stopExecution = false;
    Lock lock = new ReentrantLock();
    public void execute() {
        Set<Thread> threads = new HashSet<>();
        // implement this
        while (!stopExecution) {
            Collection<Task> tasks = nextTasks(completedTaskIds);
            for (Task task : tasks) {
                if(encounteredTaskIds.contains(task.taskId())) {
                    continue;
                }
                encounteredTaskIds.add(task.taskId());
                Thread thread = new Thread(() -> {
                    try {
                        task.doWork();
                    } catch (Exception e) {
                        lock.lock();
                        stopExecution = true;
                        lock.unlock();
                    } finally {
                        completedTaskIds.add(task.taskId());
                    }
                });
                lock.lock();
                if (stopExecution)
                    break;
                lock.unlock();
                threads.add(thread);
                thread.start();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        }
    }

    private Collection<Task> nextTasks(Collection<String> completedIDs) {
        // assume this has already been implemented
        return null;
    }
}
/*
 * Click Run to execute the snippet below!
 */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



/*

We run lots of tasks in our nightly pipeline. Tasks can require external resources to run. For example, the network, an API, file server, etc. These external resources can be flaky. If a resource is down, the task will fail with an environment error. When this happens, you know that all subsequent tasks will fail.

Write a task scheduler, with the following requirements:
- Tasks can be dependent on other tasks to complete before running. Only run tasks whose dependent tasks have run. This isn't a graph algo question so you can assume the logic to determine which tasks can be run given completed tasks is given by nextTasks.
- If a task fails with an EnvironmentError, no more tasks should be run.
- execute returns when either:
    - all tasks have completed successfully; or,
    - at least one task has failed with an EnvironmentError.
    - no tasks should be left running on return.

Tasks should be scheduled and run as quickly as we can.

abstract class Task {
  String taskId()
  void doWork() throws EnvError
}

class SchedulerWithStop {
  public void execute() {
    // implement this
  }

  // Given the list of executed tasks, this method provides the tasks whose dependencies have been executed
  private Collection<Task> nextTasks(Collection<String> completedIDs) {
    // assume this has already been implemented
  }
}

// example usage
public class Client {
    public void run() {
        SchedulerWithStop s = new SchedulerWithStop()
        s.execute()
        System.out.println("done")
    }
}
Example usage of nextTasks. I like to stress this is a stateless function.

a <- b <- c
nextTasks([a]) = [b]
// b.doWork()
nextTasks([a,b]) = [c]
nextTasks([a]) = [b]

a <- b <- c
d

nextTasks([]) = [a, d]
nextTasks([a]) = [b, d]
nextTasks([a]) = [b]
nextTasks([a, d]) = [b]
// b.doWork()
nextTasks([a,b]) = [c]
nextTasks([a]) = [b]
*/

