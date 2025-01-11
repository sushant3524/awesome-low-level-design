import org.joda.time.LocalDate;
import org.joda.time.Seconds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RailwayStationScheduler {
    int totalPlatforms;
    Map<Integer, Platform> platforms;
    Map<Train, Platform> trains;

    public boolean scheduleTrain(String name, LocalDate arrivalTime, int duration) {
        LocalDate localDate = new LocalDate(arrivalTime);
        LocalDate endDate = localDate.plus(Seconds.seconds(duration));
        for (Platform platform : platforms.values()) {
            if (platform.scheduleTrain(name, arrivalTime, endDate)) {
                trains.put(new Train(name), platform);
                return true;
            }
        }
        return false;
    }

    public int getWaitingTime(LocalDate arrivalTime, int duration) {

    }

    public Train getTrainAtPlatform(int platformNo, long time) {

    }

    public Train findTrain(String name) {
        return trains.get(name);
    }

    class Schedule {
        String trainName;
        int platformNo;
        LocalDate arrivalTime;
        LocalDate departureTime;

        public Schedule(String trainName, int platformNo, LocalDate arrivalTime, LocalDate departureTime) {
            this.trainName = trainName;
            this.platformNo = platformNo;
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }
    }

    class Platform {
        int platformNo;
        Set<Schedule> schedules;

        public boolean scheduleTrain(String name, LocalDate arrivalTime, LocalDate endDate) {
            if (getWaitingTime(arrivalTime, endDate) == 0) {
                Schedule schedule = new Schedule(name, platformNo, arrivalTime, endDate);
                schedules.add(schedule);
                return true;
            }
            return false;
        }

        private int getWaitingTime(LocalDate arrivalTime, LocalDate endDate) {
            int waitingTime = Integer.MAX_VALUE;
            // logic
            return 0;
        }
    }

    class Train {
        String name;

        public Train(String name) {
            this.name = name;
        }
    }
}
