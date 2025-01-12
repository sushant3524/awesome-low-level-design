package kafka;

public class SubscriberWorker implements Runnable {

    Topic topic;
    Subscriber subscriber;

    public SubscriberWorker(Topic topic, Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
    }

    @Override
    public void run() {
        synchronized (subscriber) {
            try {
                do {
                        long offset = subscriber.getOffsetConsumed().get();
                        if (offset >= topic.getMessages().size()) {
                            subscriber.wait();
                            continue;
                        }
                        subscriber.consume(topic.getMessages().get((int) offset));
                        subscriber.getOffsetConsumed().incrementAndGet();

                } while (true);
            } catch (Exception e) {
                System.out.println("Exception occurred while consuming data from topic: " + topic.getTopicName());
            }
        }
    }

    public synchronized void wakeUpIfNeeded() {
        synchronized (subscriber) {
            subscriber.notify();
        }
    }
}
