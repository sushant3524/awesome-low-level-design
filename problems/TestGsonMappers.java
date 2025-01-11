import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class TestGsonMappers {

    public static void main(String[] args) {
        String json = "{\"session.timeout.ms\":\"250000\", \"max.poll.records\":\"500\", \"max.partition.fetch.bytes\":\"10485760\", \"nameVsAutoCommitMs\": {\"data_rule_feed_999778\":\"300000\"}}";
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        String check = "\"nameVsAutoCommitMs\": {\"data_rule_feed_999778\":\"300000\"}";
    }

    private Object getLock(String key) {
        Object lock = keyVsLock.get(key);
        if (lock == null) {
            lock = new Object();
            Object oldLock = keyVsLock.putIfAbsent(key, lock);
            if (oldLock != null) {
                lock = oldLock;
            }
        }
        return lock;
    }

    private final ConcurrentMap<String, Integer> feedVsThreadCount = Maps.newConcurrentMap();
    private final ConcurrentMap<String, Object> keyVsLock = Maps.newConcurrentMap();
}
