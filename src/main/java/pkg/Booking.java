package pkg;

import io.restassured.response.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Booking {
    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
    private String username;
    private String password;

    public static Booking setPostBookingBody() {
        return Booking.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(BookingDates
                        .dfltBookingReqBody().get())
                .additionalneeds("Breakfast")
                .build();
    }

    public static Booking setPostAuthBody(String username, String password) {
        return Booking.builder()
                .username(username)
                .password(password)
                .build();
    }

    public static String createAuthToken(BookerClient bookerClient) {
        String userName = "admin";
        String pwd = "password123";
        Response authToken = bookerClient.createAuthToken(BookingHeaders.
                                                            setPostAuthHeaders(),
                                                          userName, pwd);
        return authToken.jsonPath().get("token");
    }
}
