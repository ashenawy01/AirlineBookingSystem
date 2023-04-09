package Legacy;

import DAO.FlightDB;

public class GeneralTesting {

    // Rondem testing
    // Please Ignore this class

    public  static FlightDB flightDB = new FlightDB();
    public static void main(String[] args) {

        //    public static LinkedList<FlightTrip> findBooking(String origin, String destination, LocalDate flightDate){
//        LinkedList<FlightTrip> results = new LinkedList<>();
//        ArrayList<Object> allFlights = flightDB.retrieveAll();
//        int maxFlights = 3;
//        allFlights.forEach(f -> {
//            FlightTrip flightTrip = new FlightTrip();
//            Flight flight = (Flight) f;
//            if (flight.getOrigin().equalsIgnoreCase(origin)
//                    && flight.getFlightTime().getDayOfYear() == flightDate.getDayOfYear()) {
//                flightTrip.add(flight);
//
//                if (flight.getDestination().equalsIgnoreCase(destination)) {
//                    results.add(flightTrip);
//                } else {
//                    String nextOrigin = flight.getDestination();
//                    LocalDateTime nextFlightTime = flight.getFlightTime().plusHours(1);
//                    Flight nextFlight = new Flight();
//                    int flightsCounter = 1;
//                    for (int i = 0; i < allFlights.size(); i++) {
//                        nextFlight = (Flight) allFlights.get(i);
//                        if (nextFlight.getOrigin().equalsIgnoreCase(nextOrigin)
//                                && nextFlight.getFlightTime().getDayOfYear() == nextFlightTime.getDayOfYear()){
//                            flightTrip.add(nextFlight);
//                            if (nextFlight.getDestination().equalsIgnoreCase(destination)) {
//                                results.add(flightTrip);
//                                break;
//                            } else {
//                                nextOrigin = nextFlight.getDestination();
//                                i = 0;
//                                flightsCounter++;
//                            }
//                        }
//                        if (flightsCounter >= maxFlights) {
//                            break;
//                        }
//                    }
//                }
//            }
//
//        });
//
//        return results;
//    }

//    // Filter the flights and create some complete bookings to meet the client need
//    public static Map<Integer, Booking> findBooking (String origin, String destination, LocalDate flightDate) {
//        // validation
//        if (origin.isEmpty() || origin.isBlank() || origin.length() < 2) {
//            System.out.println("Error - Invalid origin");
//            return null;
//        }
//        if (destination.isEmpty() || destination.isBlank() || destination.length() < 2) {
//            System.out.println("Error - Invalid destination");
//            return null;
//        }
//        if (flightDate.isBefore(LocalDate.now())) {
//            System.out.println("Error - Invalid date (date in past)");
//            return null;
//        }
//        Map<Integer, Booking> matchBookings = new HashMap<>();
//        Booking booking = new Booking();  // Matched booking
//        ArrayList<Object> allFlights = flightDB.retrieveAll(); // All flights in database
//
//        // is trip completed from origin to destination
//        boolean tripComplete = false;
//
//        // loop through all flight
//        int i = 0;
//        Flight flight;
//        do {
//            flight = (Flight) allFlights.get(i);
//            // Unmatched flight
//            if (!(flight.getOrigin().equalsIgnoreCase(origin))
//                    || !(flight.getFlightTime().getDayOfYear() == flightDate.getDayOfYear())) {
//                i++;
//            }
//            else if (flight.getOrigin().equalsIgnoreCase(origin)
//                    && flight.getDestination().equalsIgnoreCase(destination)) {
//                booking.addFlight(flight, flight.getSeats());
//                matchBookings.put(i, booking);
//                i++;
//            } else { // the same origin but different destination
//                ArrayList<Flight> bookedFlights = new ArrayList<>(); // flights per booking
//                bookedFlights.add(flight);
//                String nextOrigin = flight.getDestination(); // the next origin of the second flight
//                int j = 0; // inner counter
//                Flight nextFlight;
//                int maxFlightNum = 4;
//                while (bookedFlights.size() < maxFlightNum && j < allFlights.size()) {
//                    nextFlight = (Flight) allFlights.get(j);
//                    if (nextFlight.getOrigin().equalsIgnoreCase(nextOrigin) // found the last flight
//                            && nextFlight.getDestination().equalsIgnoreCase(destination)) {
//                        bookedFlights.add(nextFlight);
//                        bookedFlights.forEach(flight1 -> {
//                            booking.addFlight(flight1, flight1.getSeats());
//                        });
//                        matchBookings.put(i, booking);
//                        break;
//                    }
//                    if (nextFlight.getOrigin().equalsIgnoreCase(nextOrigin)
//                            && !nextFlight.getDestination().equalsIgnoreCase(destination)) {
//                        nextOrigin = nextFlight.getDestination();
//                    }
//                    j++;
//                }
//            }
//        } while (!tripComplete && i < allFlights.size());
//        return matchBookings;
//    }

//        public static Booking addFlightToBooking(int bookingId, Flight newFlight, ArrayList< Seat >seats){
//            Booking booking = bookingDB.findBooking(bookingId);
//            if (booking == null) {
//                System.out.println("Error 404 - ID is not found....please try again ");
//                return null;
//            }
//            booking.addFlight(newFlight, seats);
//            bookingDB.updateBooking(bookingId, booking);
//            return booking;
//        }
//
//        public static Booking removeFlightFromBooking(int bookingId, int flightID){
//            Booking booking = bookingDB.findBooking(bookingId);
//            if (booking == null) {
//                System.out.println("Error 404 - ID is not found....please try again ");
//                return null;
//            }
//            Flight flight = flightDB.findFlight(flightID);
//            booking.deleteFlight(flight);
//            bookingDB.updateBooking(bookingId, booking);
//            return booking;
//        }


//    public static Booking updateBookingDate(int bookingId, LocalDateTime newDate){
//        Booking booking = bookingDB.findBooking(bookingId);
//        if (booking == null) {
//            System.out.println("Error 404 - ID is not found....please try again ");
//            return null;
//        }
//        booking.setDate(newDate);
//        bookingDB.updateBooking(bookingId, booking);
//        return booking;
//    }
//
//    public static Booking updateBookingTraveler(int bookingId, int travelers){
//        Booking booking = bookingDB.findBooking(bookingId);
//        if (booking == null) {
//            System.out.println("Error 404 - ID is not found....please try again ");
//            return null;
//        }
//        booking.setTravelers(travelers);
//        bookingDB.updateBooking(bookingId, booking);
//        return booking;
//    }



    }
}
