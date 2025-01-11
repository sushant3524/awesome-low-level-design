import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private static ParkingLot INSTANCE;
    private final List<ParkingFloor> parkingFloors;
    Map<String, ParkingFloor> vehicleVsFloor;
    private ParkingLot() {
        parkingFloors = new ArrayList<>();
        vehicleVsFloor = new ConcurrentHashMap<>();
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLot();
        }
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if(parkingFloor.parkVehicle(vehicle)) {
                vehicleVsFloor.put(vehicle.getVehicleNumber(), parkingFloor);
                return true;
            }
        }
        return false;
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingFloor parkingFloor = vehicleVsFloor.get(vehicle.getVehicleNumber());
        parkingFloor.unParkVehicle(vehicle);
        vehicleVsFloor.remove(vehicle.getVehicleNumber());
    }

    public enum VehicleType {
        CAR(100), MOTORCYCLE(200), TRUCK(300);
        private Integer price;

        VehicleType(int price) {
            this.price = price;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }

    public class Vehicle {
        private String vehicleNumber;
        private VehicleType vehicleType;

        public Vehicle(String vehicleNumber, VehicleType vehicleType) {
            this.vehicleNumber = vehicleNumber;
            this.vehicleType = vehicleType;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public VehicleType getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
        }
    }

    public class Car extends Vehicle {
        public Car(String number) {
            super(number, VehicleType.CAR);
        }
    }

    public class ParkingSpot {
        private VehicleType parkingSpotType;
        private Vehicle vehicle;
        private Long startTime;

        public ParkingSpot(VehicleType parkingSpotType) {
            this.parkingSpotType = parkingSpotType;
        }

        public VehicleType getParkingSpotType() {
            return parkingSpotType;
        }

        public void setParkingSpotType(VehicleType parkingSpotType) {
            this.parkingSpotType = parkingSpotType;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public int getPrice() {
            if (vehicle != null) {
                return vehicle.getVehicleType().getPrice();
            }
            return 0;
        }

        public synchronized boolean isAvailable() {
            if (vehicle == null) return true;
            return false;
        }
        public synchronized boolean park(Vehicle newVehicle) {
            if (newVehicle != null && vehicle == null && newVehicle.getVehicleType() == parkingSpotType) {
                vehicle = newVehicle;
                startTime = new Date().getTime();
                return true;
            }
            return false;
        }

        public boolean unPark() {
            vehicle = null;
            return true;
        }

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }
    }

    public class ParkingFloor {
        Integer floorNumber;
        List<ParkingSpot> parkingSpots;

        public ParkingFloor(Integer floorNumber, Integer countParkingSpots) {
            this.floorNumber = floorNumber;
        }

        public Integer getFloorNumber() {
            return floorNumber;
        }

        public void setFloorNumber(Integer floorNumber) {
            this.floorNumber = floorNumber;
        }

        public List<ParkingSpot> getParkingSpots() {
            return parkingSpots;
        }

        public void setParkingSpots(List<ParkingSpot> parkingSpots) {
            this.parkingSpots = parkingSpots;
        }

        public boolean parkVehicle(Vehicle vehicle) {
            for (ParkingSpot parkingSpot : parkingSpots) {
                if (parkingSpot.park(vehicle)) {
                    return true;
                }
            }
            return false;
        }

        public boolean unParkVehicle(Vehicle vehicle) {
            for (ParkingSpot parkingSpot : parkingSpots) {
                if (vehicle.getVehicleNumber().equals(parkingSpot.getVehicle().getVehicleNumber())) {
                    parkingSpot.unPark();
                    return true;
                }
            }
            return false;
        }
    }

}
