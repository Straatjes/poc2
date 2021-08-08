package pkg;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FullBooking {
    private Number bookingid;
    private Booking booking;
}
