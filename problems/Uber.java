public class Uber {
}

/**
 * list of users
 * list of drivers
 * rides
 * map of rideId vs Driver
 * rideAssigningService
 *      calculateEstimatedCost based on distance, rideType and time
 *      findDriverInProximity for a particular rideType
 *      notifyDrivers
 *
 *  rideProgressTracker(rideID, currentLocation)
 *
 *
 * boolean acceptRide(ride, driver)
 *      synchronized on rideId
 *          add in map if not present, notify user else ignore
 *
 *  boolean startRide(ride, driver)
 *  boolean completeRide(ride, driver)
 *
 * boolean calculateCost(ride, endTime)
 *
 *
 * requestRide(ride)
        * User
        *      name
        *      id
        *      email
        *      phone
        *      rideHistory
        *
        * Ride
        *      id
        *      userId
        *      pickup
        *      destination
        *      rideType
        *      requestedTime
 *              status
        *
        * Driver
        *      id
        *      name
        *      phone
        *      rideHistory
        *      currentLocation
 *              rideType
 *              license
 *              status
 *
 */