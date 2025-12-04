package task1_hotel_booking.history;

import task1_hotel_booking.model.BookingRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private static BookingHistory instance;
    private final List<BookingRecord> records;

    private BookingHistory() {
        this.records = new ArrayList<>();
    }

    public static synchronized BookingHistory getInstance() {
        if (instance == null) {
            instance = new BookingHistory();
        }
        return instance;
    }

    public void addRecord(BookingRecord record) {
        records.add(record);
        System.out.println("История обновлена: " + record);
    }

    public List<BookingRecord> getAllRecords() {
        return Collections.unmodifiableList(records);
    }

    public List<BookingRecord> getRecordsByGuest(String guestName) {
        List<BookingRecord> result = new ArrayList<>();
        for (BookingRecord record : records) {
            if (record.getGuestName().equals(guestName)) {
                result.add(record);
            }
        }
        return result;
    }

    public List<BookingRecord> getRecordsByState(String state) {
        List<BookingRecord> result = new ArrayList<>();
        for (BookingRecord record : records) {
            if (record.getFinalState().equals(state)) {
                result.add(record);
            }
        }
        return result;
    }

    public void printHistory() {
        System.out.println("\n=== История бронирований ===");
        if (records.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            for (BookingRecord record : records) {
                System.out.println(record);
            }
        }
        System.out.println("============================\n");
    }

    public void clear() {
        records.clear();
    }
}
