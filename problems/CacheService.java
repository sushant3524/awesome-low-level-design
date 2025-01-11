public interface CacheService {

    public void put(String key, String value);

    public interface EvictionPolicy {

    }
}