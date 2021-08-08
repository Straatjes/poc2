package pkg;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static pkg.Booking.setPostBookingBody;
import static pkg.ReqSpec.postBookingSpec;

public class BookerClient implements RestAssuredClient {

    @Override
    public Response createBooking(Booking postBookingBody, BookingHeaders postBookingheaders) {
        return given().
                    spec(postBookingSpec()).
                    body(postBookingBody).
                    header(postBookingheaders.getContentTypeHeader()).
                    header(postBookingheaders.getAcceptHeader()).
               when().
                    post();
    }

    @Override
    public Response getBooking(BookingHeaders getBookingHeaders, String bookingId) {
        return given().
                    spec(ReqSpec.getBookingSpec()).
                    pathParam("id", bookingId).
                    body(setPostBookingBody()).
                    header(getBookingHeaders.getAcceptHeader()).
               when().
                    get("/{id}");
    }

    @Override
    public Response deleteBooking(BookingHeaders deleteBookingHeaders, Number bookingId) {
        return given().
                    spec(ReqSpec.deleteBookingSpec()).
                    pathParam("id", bookingId).
                    header(deleteBookingHeaders.getContentTypeHeader()).
                    header(deleteBookingHeaders.getCookieHeader()).
               when().
                    delete("/{id}");
    }

    //@Override
    public Response createAuthToken(BookingHeaders authHeaders, String userName, String pwd) {
        return given().
                spec(ReqSpec.postAuthSpec()).
                body(Booking.setPostAuthBody(userName, pwd)).
                header(authHeaders.getContentTypeHeader()).
                when().
                post();
    }

    @Override
    public Response updateBooking(BookingHeaders updateBookingHeaders, FullBooking fullBooking) {

        return given().
                    spec(ReqSpec.putBookingSpec()).
                    header(updateBookingHeaders.getContentTypeHeader()).
                    header(updateBookingHeaders.getAcceptHeader()).
                    header(updateBookingHeaders.getCookieHeader()).
                    pathParam("id", fullBooking.getBookingid()).
                    body(fullBooking.getBooking()).
               when().
                    put("/{id}");
    }
}
