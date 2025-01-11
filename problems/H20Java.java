import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H20Java {
    public static void main(String[] args) throws IOException {
        String test = "sush";
        String test1 = "sush1";
        Thread thread = new Thread(() -> printStr(test));
        Thread thread1 = new Thread(() -> printStr(test));
        thread1.start();
        thread.start();
    }

    Semaphore hy = new Semaphore(2);
    Semaphore ox = new Semaphore(0);
    public H20Java() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hy.acquire();
        releaseHydrogen.run();
        if(hy.availablePermits() == 0) {
            ox.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        ox.acquire();
        releaseOxygen.run();
        hy.release(2);
    }

    private static void printStr(String str) {
        try {
            synchronized (str) {
                System.out.println(str);
                Thread.sleep(10000);
                System.out.println(str);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public class Bathroom {

        private Lock lock = new ReentrantLock();
        Semaphore bathroomMutex = new Semaphore(1, true);
        private int BATHROOM_SIZE = 5;
        Semaphore permitCount = new Semaphore(BATHROOM_SIZE);
        private int womenUsingN = 0;
        private int menUsingN = 0;

        public void womanEnter() throws InterruptedException {
            lock.lock();
            if (womenUsingN == 0) {
                bathroomMutex.acquire();
            }
            permitCount.acquire();
            womenUsingN++;
            lock.unlock();
        }

        public void womanExit() {
            lock.lock();
            womenUsingN--;
            permitCount.release();
            if(womenUsingN==0) {
                bathroomMutex.release();
            }
            lock.unlock();
        }

        public void manEnter() throws InterruptedException {
            lock.lock();
            if(menUsingN == 0) {
                bathroomMutex.acquire();
            }
            permitCount.acquire();
            menUsingN++;
            lock.unlock();
        }

        public void manExit() throws InterruptedException {
            lock.lock();
            menUsingN--;
            permitCount.release();
            if(menUsingN==0) {
                bathroomMutex.release();
            }
            lock.unlock();
        }
    }
    class LRUCache {
        Map<Integer, Integer> cache;
        Map<Integer, Integer> itrVsKey;
        Map<Integer, Integer> keyVsItr;
        Set<Integer> st;
        int sz;
        int itr;
        int currSize;
        public LRUCache(int capacity) {
            sz = capacity;
            cache = new HashMap<>();
            itr = 0;
            itrVsKey = new HashMap<>();
            keyVsItr = new HashMap<>();
            currSize = 0;
            st = new TreeSet<>();
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                Integer currItr = keyVsItr.get(key);
                st.remove(currItr);
                itrVsKey.remove(currItr);
                keyVsItr.put(key, itr);
                st.add(itr);
                itr++;
                return cache.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if(currSize==sz) {
                Optional<Integer> optionalI = st.stream().findFirst();
                if(optionalI.isPresent()) {
                    System.out.println("key: " + key + " value: " + value);
                    Integer removeKey = itrVsKey.remove(optionalI.get());
                    cache.remove(removeKey);
                    keyVsItr.remove(removeKey);
                    currSize--;
                    st.remove(optionalI.get());
                    System.out.println("removed Key " + removeKey);
                }
            }
            cache.put(key, value);
            st.add(itr);
            itrVsKey.put(itr, key);
            keyVsItr.put(key, itr);
            currSize++;
            itr++;
        }
    }

}
