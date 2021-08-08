package pkg;

import io.restassured.http.Header;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter @Setter @Builder
public class BookingHeaders {

    private Header contentTypeHeader;
    private Header acceptHeader;
    private Header cookieHeader;
    private Header authorisationHeader;

    public static BookingHeaders setPostBookingHeaders() {
        return  BookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                acceptHeader(new Header("Accept", "application/json")).
                build();
    }

    public static BookingHeaders setGetBookingHeaders() {
        return  BookingHeaders.builder().
                acceptHeader(new Header("Accept", "application/json")).
                build();
    }

    public static BookingHeaders setPostAuthHeaders() {
        return  BookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                build();
    }

    public static BookingHeaders setPutBookingHeaders(String token) {
        return  BookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                acceptHeader(new Header("Accept", "application/json")).
                cookieHeader(new Header("Cookie", "token="+token)).
                build();
    }

    public static BookingHeaders setDeleteBookingHeaders(String token) {
        return  BookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                cookieHeader(new Header("Cookie", "token="+token)).
                build();
    }
}
