package pkg;

import io.restassured.internal.http.Status;
import io.restassured.response.Response;


public interface RestAssuredClient {
    Response createBooking(Booking createBookingBody,BookingHeaders createBookingHeaders);

    Response deleteBooking(BookingHeaders bookingHeaders, Number bookingId);

    Response getBooking(BookingHeaders getBookingHeaders, String bookingId);

    Response createAuthToken(BookingHeaders authHeaders, String userName, String pwd);

    Response updateBooking(BookingHeaders updateBookingHeaders, FullBooking fullBooking);
}

