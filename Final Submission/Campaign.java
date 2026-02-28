import java.util.Date;

public class Campaign {
    public static final int MAX_DISCOUNT_RATE = 50;

    private Date startDate;
    private Date endDate;
    private String itemType;
    private int discountRate;

    public Campaign(Date startDate, Date endDate, String itemType, int discountRate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemType = itemType;
        this.discountRate = discountRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getItemType() {
        return itemType;
    }

    public int getDiscountRate() {
        return discountRate;
    }
}