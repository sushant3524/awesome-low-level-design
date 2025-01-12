package kafka;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    Map<String, SubscriberWorker> subscriberWorkers;
    Topic topic;

    TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish(Message message) {
        topic.addMessage(message);
        for (Subscriber subscriber : topic.getSubscribers().values()) {
            startSubscriberWorker(subscriber);
        }
    }

    public void startSubscriberWorker(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber passed is null");
        }
        SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriber.getId());
        if (subscriberWorker == null) {
            subscriberWorker = new SubscriberWorker(topic, subscriber);
            subscriberWorkers.put(subscriber.getId(), subscriberWorker);
            new Thread(subscriberWorker).start();
        } else {
            subscriberWorker.wakeUpIfNeeded();
        }
    }

}
