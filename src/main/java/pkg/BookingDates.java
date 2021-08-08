package pkg;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
@Builder
public class BookingDates {
   private String checkin;
    private String checkout;

    public static Supplier<BookingDates> dfltBookingReqBody() {
        return () -> BookingDates.builder().
                checkin("2050-01-01").
                checkout("2050-01-02").
                build();
    }
}
