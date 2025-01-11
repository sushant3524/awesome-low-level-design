import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TaskSystem {
    Map<String, Task> tasks;

    public Task createTask(String title, String desc, Date dueDate, int priority, Status status) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, desc, dueDate, priority, status);
        tasks.put(id, task);
        return task;
    }

    public void updateTask(Task task) {
        synchronized (task.getId()) {
            tasks.put(task.getId(), task);
        }
    }

    public class Task {
        String id;
        String title;
        String desc;
        Date dueDate;
        int priority;
        Status status;

        public Task(String id, String title, String desc, Date dueDate, int priority, Status status) {
            this.id = id;
            this.title = title;
            this.desc = desc;
            this.dueDate = dueDate;
            this.priority = priority;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

    public class User {
        String id;
        String name;
        String email;
        Map<String, Task> tasks;

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public void assignTaskToUser(User user, Task task) {
            user.assignTask(task);
        }

        public void markTaskCompleted(Task task) {

        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public synchronized void assignTask(Task task) {
            tasks.put(task.getId(), task);
        }
    }

    public enum Status {
        pending, inprogress, completed
    }
}
