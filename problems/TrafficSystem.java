import java.util.List;
import java.util.Map;

public class TrafficSystem {
    private Map<String, Road> roads;

    public void handleEmergency(String roadId) {
        Road road = roads.get(roadId);
        road.getTrafficLight().changeSignal(TrafficLightType.RED);
    }

    public void startTrafficControl() {
        for (Road road : roads.values()) {
            Thread thread = new Thread(() -> {
                while(true) {
                    try {
                        road.getTrafficLight().changeSignal(TrafficLightType.GREEN);
                        Thread.sleep(road.getTrafficLight().getGreenLightDuration());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        }
    }

    public class Road {
        private String id;
        private TrafficLight trafficLight;

        public Road(String id, TrafficLight trafficLight) {
            this.id = id;
            this.trafficLight = trafficLight;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TrafficLight getTrafficLight() {
            return trafficLight;
        }

        public void setTrafficLight(TrafficLight trafficLight) {
            this.trafficLight = trafficLight;
        }
    }

    public class TrafficLight {
        private String id;
        private TrafficLightType currentLight;
        private int redLightDuration;
        private int greenLightDuration;
        private int yellowLightDuration;

        public TrafficLight(String id, TrafficLightType currentLight, int redLightDuration, int greenLightDuration, int yellowLightDuration) {
            this.id = id;
            this.currentLight = currentLight;
            this.redLightDuration = redLightDuration;
            this.greenLightDuration = greenLightDuration;
            this.yellowLightDuration = yellowLightDuration;
        }

        public synchronized void changeSignal(TrafficLightType trafficLightType) {
            currentLight = trafficLightType;
            notifyObservers();
        }

        private void notifyObservers() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TrafficLightType getCurrentLight() {
            return currentLight;
        }

        public void setCurrentLight(TrafficLightType currentLight) {
            this.currentLight = currentLight;
        }

        public int getRedLightDuration() {
            return redLightDuration;
        }

        public void setRedLightDuration(int redLightDuration) {
            this.redLightDuration = redLightDuration;
        }

        public int getGreenLightDuration() {
            return greenLightDuration;
        }

        public void setGreenLightDuration(int greenLightDuration) {
            this.greenLightDuration = greenLightDuration;
        }

        public int getYellowLightDuration() {
            return yellowLightDuration;
        }

        public void setYellowLightDuration(int yellowLightDuration) {
            this.yellowLightDuration = yellowLightDuration;
        }
    }
    public enum TrafficLightType {
        GREEN, RED, YELLOW
    }
}
