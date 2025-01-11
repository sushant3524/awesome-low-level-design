import java.math.BigInteger;
import java.time.*;
import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Base10To35ConvertRedis {
    public static void main(String[] args) throws InterruptedException {
        String bI = new BigInteger("1597249693414658049",10).toString(36);
//        System.out.println(bI);
//        System.out.println(getMostRecentDate());
//        System.out.println(getYearMonthObject(getMostRecentDate()).toString());
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i=0;i<7;i++) {
            int finalI = i;
            executorService.submit(() -> checkThreads(finalI));
        }
        String accessToken = new String(Base64.getEncoder().encode("parag.mundhada@sprinklr.com/token:qHrueyD5pKEuYC2i4dcT2IpHfIUGxNPfAUwAoXwG".getBytes()));
        System.out.println(accessToken);
        Thread.sleep(1000);
    }

    private static void checkThreads(int j) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i=j;i<j+6;i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    checkThreadsAgain(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void checkThreadsAgain(int finalI) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName());
    }

    public static long getMostRecentDate() {
        ZonedDateTime z = Instant.now().atZone(ZoneId.systemDefault());
        int day = z.getDayOfMonth();
        int month = z.getMonthValue() - 1;
        int year = z.getYear();
        if (day < 15) {                                     // similarweb api -> last month data visible after around 11th of next month
            month--;
        }
        if (month <= 0) {
            month += 12;
            year--;
        }
        return getYearMonthLong(YearMonth.of(year, month));
    }

    public static long getYearMonthLong(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        return (year * 100 + month);
    }

    public static YearMonth getYearMonthObject(long date) {
        int month = (int) (date % 100);
        int year = (int) ((date / 100) % 10000);
        if (month < 1 || month > 12 || year < 0 || year > 3000) {
            return YearMonth.of(0, Month.JANUARY);
        }
        return YearMonth.of(year, month);
    }
}
