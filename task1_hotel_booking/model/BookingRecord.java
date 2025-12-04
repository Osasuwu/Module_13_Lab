package task1_hotel_booking.model;

import java.time.LocalDateTime;

public class BookingRecord {
    private final String bookingId;
    private final String roomNumber;
    private final String guestName;
    private final String finalState;
    private final double amountPaid;
    private final double discountApplied;
    private final LocalDateTime timestamp;

    public BookingRecord(String bookingId, String roomNumber, String guestName, 
                         String finalState, double amountPaid, double discountApplied) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.finalState = finalState;
        this.amountPaid = amountPaid;
        this.discountApplied = discountApplied;
        this.timestamp = LocalDateTime.now();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getFinalState() {
        return finalState;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("BookingRecord{id='%s', room='%s', guest='%s', state='%s', paid=%.2f, discount=%.1f%%, time=%s}",
                bookingId, roomNumber, guestName, finalState, amountPaid, discountApplied, timestamp);
    }
}
