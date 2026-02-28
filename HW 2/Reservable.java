
//same "interface" issue on Borrowable interface
public interface Reservable {
    void reserve(String userName);

    //I guess we hold these methods for next versions ....
    void cancelReservation();
    boolean isReserved();
    String getReservationHolder();
}
