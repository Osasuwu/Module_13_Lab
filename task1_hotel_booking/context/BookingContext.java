package task1_hotel_booking.context;

import task1_hotel_booking.history.BookingHistory;
import task1_hotel_booking.model.BookingRecord;
import task1_hotel_booking.states.BookingState;
import task1_hotel_booking.states.IdleState;

import java.util.UUID;

public class BookingContext {
    private BookingState currentState;
    private String bookingId;
    private String selectedRoom;
    private String guestName;
    private double roomPrice;
    private double amountPaid;
    private double discountApplied;

    public BookingContext(String guestName) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.guestName = guestName;
        this.currentState = new IdleState();
        this.roomPrice = 0;
        this.amountPaid = 0;
        this.discountApplied = 0;
    }

    public void setState(BookingState state) {
        this.currentState = state;
        System.out.println("[Состояние изменено на: " + state.getStateName() + "]");
    }

    public BookingState getState() {
        return currentState;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
        if (selectedRoom != null) {
            this.roomPrice = calculateRoomPrice(selectedRoom);
        }
    }

    public String getGuestName() {
        return guestName;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = discountApplied;
    }

    private double calculateRoomPrice(String roomNumber) {
        if (roomNumber.startsWith("S")) {
            return 5000.0;
        } else if (roomNumber.startsWith("D")) {
            return 3500.0;
        } else if (roomNumber.startsWith("L")) {
            return 10000.0;
        }
        return 2500.0;
    }

    public void selectRoom(String roomNumber) {
        currentState.selectRoom(this, roomNumber);
    }

    public void changeRoom(String newRoomNumber) {
        currentState.changeRoom(this, newRoomNumber);
    }

    public void confirmBooking() {
        currentState.confirmBooking(this);
    }

    public void pay(double amount) {
        currentState.pay(this, amount, 0);
    }

    public void payWithDiscount(double amount, double discountPercent) {
        currentState.pay(this, amount, discountPercent);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void saveToHistory() {
        BookingRecord record = new BookingRecord(
                bookingId,
                selectedRoom,
                guestName,
                currentState.getStateName(),
                amountPaid,
                discountApplied
        );
        BookingHistory.getInstance().addRecord(record);
    }

    public void printStatus() {
        System.out.println("\n--- Статус бронирования ---");
        System.out.println("ID: " + bookingId);
        System.out.println("Гость: " + guestName);
        System.out.println("Номер: " + (selectedRoom != null ? selectedRoom : "не выбран"));
        System.out.println("Цена номера: " + roomPrice);
        System.out.println("Состояние: " + currentState.getStateName());
        System.out.println("---------------------------\n");
    }
}
