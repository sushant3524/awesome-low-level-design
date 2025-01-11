import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private static CarRentalSystem INSTANCE;
    private final ReservationService reservationService;
    private Inventory inventory;
    private PaymentProcessor paymentProcessor;
    private CarRentalSystem() {
        inventory = new Inventory();
        reservationService = new ReservationService(inventory);
    }
    public static CarRentalSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarRentalSystem();
        }
        return INSTANCE;
    }

    public List<Car> browseCars(CarType carType, LocalDate startDate, LocalDate endDate) {

    }

    public boolean reserveCar(Car car, LocalDate startDate, LocalDate endDate) {
        return reservationService.reserveCar(car, startDate, endDate);
    }

    public void processPayment(Reservation reservation) {
        paymentProcessor.processPayment(reservation.getTotalPrice());
    }

    public class ReservationService {
        Inventory inventory;
        Map<String, List<Reservation>> reservations;

        public boolean reserveCar(Car car, LocalDate startDate, LocalDate endDate) {
            synchronized (car) {
                if(isCarAvailable(car, startDate, endDate)) {
                    List<Reservation> reservationList = reservations.computeIfAbsent(car.getId(), s -> new ArrayList<>());
                    reservationList.add(new Reservation(car, startDate, endDate));
                    car
                }
            }
        }

        private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {
            for (Reservation reservation : reservations.get(car.getId())) {
                if (reservation.getCar().equals(car)) {
                    if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {
                        return false;
                    }
                }
            }
            return true;
        }

        public long getStartOfDay(Date date) {
            return new DateTime(date).withTimeAtStartOfDay().getMillis();
        }

        public ReservationService(Inventory inventory) {
            this.inventory = inventory;
            reservations = new ConcurrentHashMap<>();
        }
    }

    public interface PaymentProcessor {
        void processPayment(int amount);
    }

    public class Reservation {
        private Car car;
        private LocalDate startDate;
        private LocalDate endDate;
        private int totalPrice;

        public Reservation(Car car, LocalDate startDate, LocalDate endDate) {
            this.car = car;
            this.startDate = startDate;
            this.endDate = endDate;
            this.totalPrice = calculatePrice(car, startDate, endDate);
        }

        private int calculatePrice(Car car, LocalDate startDate, LocalDate endDate) {
            int days = Days.daysBetween(startDate, endDate).getDays();
            return car.getRentPerDay() * days;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }

    public class Inventory {
        Map<String, Car> cars;
    }

    public class Customer {
        private String name;
        private String phone;
        private String licenseInfo;
    }

    public enum CarType {
        A, B, C, D
    }

    public class Car {
        private String model;
        private String make;
        private CarType carType;
        private String year;
        private String licensePlate;
        private Integer rentPerDay;
        private String id;

        public Car(String model, String make, CarType carType, String year, String licensePlate, Integer rentPerDay) {
            this.id = UUID.randomUUID().toString();
            this.model = model;
            this.make = make;
            this.carType = carType;
            this.year = year;
            this.licensePlate = licensePlate;
            this.rentPerDay = rentPerDay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CarType getCarType() {
            return carType;
        }

        public void setCarType(CarType carType) {
            this.carType = carType;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public Integer getRentPerDay() {
            return rentPerDay;
        }

        public void setRentPerDay(Integer rentPerDay) {
            this.rentPerDay = rentPerDay;
        }
    }
}
