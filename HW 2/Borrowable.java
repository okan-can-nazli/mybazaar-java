public interface Borrowable {


    //Since "interface" we CAN NOT give action to methods
    void borrow();

    void returnItem();

    boolean isAvailableToBorrow();

    int getRemainingDays();
}
