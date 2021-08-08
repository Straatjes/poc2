import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.core.*;
import org.junit.Test;
import pkg.BookerClient;
import pkg.Booking;
import pkg.BookingHeaders;
import pkg.FullBooking;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class restfulBooker {
    @Test
    public void createBooking() {
        Booking postBookingBody = Booking.setPostBookingBody();

        BookingHeaders postBookingHeaders = BookingHeaders.setPostBookingHeaders();

        BookerClient bookerClient = new BookerClient();
        //create new booking
        Response booking = bookerClient.createBooking(postBookingBody, postBookingHeaders);

        String bookingId = booking.getBody().jsonPath().getString("bookingid");

        //Validating and Printing the response details
        assertThat(booking.statusCode(), IsEqual.equalTo(200));

        System.out.println("Booking ID:" + bookingId);

        System.out.println("New created Booking details : "+booking.body().asString());

        System.out.println("Verify Booking created based on input data:" + booking.getBody().jsonPath().getString("booking").contains("Jim"));

    }

    @Test
    public void updateBooking() {
        BookerClient bookerClient = new BookerClient();

        String token = Booking.createAuthToken(bookerClient);

        FullBooking fullBooking = bookerClient.createBooking(
                Booking.setPostBookingBody(),
                BookingHeaders.setPostBookingHeaders()).as(FullBooking.class);

        //Updating the booking details value
        fullBooking.getBooking().setLastname("william");
        fullBooking.getBooking().setTotalprice(222);

        BookingHeaders updateBookingHeaders = BookingHeaders.setPutBookingHeaders(token);

        Response putBookingResponse = bookerClient.updateBooking(updateBookingHeaders, fullBooking);

        //Validating and Printing the response details

        System.out.println("Updated Booking Details :"+putBookingResponse.body().asString());

        putBookingResponse.
                then().
                assertThat().
                statusCode(200);
        System.out.println("Verify last name updated successfully:" + fullBooking.getBooking().getLastname().equals("william"));
        System.out.println("Verify totalprice value updated successfully:" +  fullBooking.getBooking().getTotalprice().equals(222));
    }

   @Test
    public void deleteBooking() {
        BookerClient bookerClient = new BookerClient();

        String token = Booking.createAuthToken(bookerClient);

        FullBooking fullBooking = bookerClient.createBooking(
                        Booking.setPostBookingBody(),
                        BookingHeaders.setPostBookingHeaders())
                .as(FullBooking.class);
        System.out.println("delete bookig id :"+fullBooking.getBookingid());

        BookingHeaders deleteBookingHeaders =
                BookingHeaders.setDeleteBookingHeaders(token);

        // delete the booking
       Response delresponse= bookerClient.deleteBooking(deleteBookingHeaders,
                fullBooking.getBookingid());

       delresponse.then().assertThat().statusCode(201);
       System.out.println("Booking record is deleted :" + delresponse.statusCode());


       Response getBookingResponse = bookerClient.
                getBooking(BookingHeaders.setGetBookingHeaders(),
                        fullBooking.getBookingid().toString());

       // Checking whether booking is deleted
        getBookingResponse.
                then().
                assertThat().statusCode(404);
       System.out.println("Deleted booking does not exist:" + getBookingResponse.statusCode());

   }
   //@Test
    public void getBooking() {
        BookingHeaders getBookingHeaders = BookingHeaders.setGetBookingHeaders();

        BookerClient bookerClient = new BookerClient();
        Response getBookingResponse = bookerClient.getBooking(getBookingHeaders, "23");

        getBookingResponse.
        then().
            assertThat().statusCode(200);
       System.out.println("The response status is "+getBookingResponse.print());



    }

   // @Test
    public void postAuth() {
        BookingHeaders authHeaders = BookingHeaders.setPostAuthHeaders();

        String userName = "admin";
        String pwd = "password123";

        BookerClient bookerClient = new BookerClient();
        Response authToken = bookerClient.createAuthToken(authHeaders, userName, pwd);
        String body = authToken.body().asString();
        System.out.println(body);
        assertThat(authToken.statusCode(), IsEqual.equalTo(200));

    }

}
