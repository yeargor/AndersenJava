package Homework4;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket extends ClassTemplate {

    private String concertHall;
    private int eventCode;
    @NullableWarning
    private long time;
    private Boolean isPromo;
    private Sector stadiumSector;
    private float allowedWeight;
    private long createdAt;
    private final BigDecimal price = new BigDecimal("9.99");

    public Ticket() {

        NullValidator.checkForNulls(this);
        this.createdAt = System.currentTimeMillis() / 1000L;

    }

    public Ticket(String id, String concertHall, int eventCode, long time, Boolean isPromo, Sector stadiumSector, float allowedWeight) {

        NullValidator.checkForNulls(this);
        setId(validateLength(id, "id",4));
        this.concertHall = validateLength(concertHall,"concertHall", 10);
        this.eventCode = validateRange(eventCode,"eventCode",100,999);
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.allowedWeight = allowedWeight;
        this.createdAt = System.currentTimeMillis() / 1000L;

    }

    public Ticket(String concertHall, int eventCode, long time) {

        NullValidator.checkForNulls(this);
        this.concertHall = validateLength(concertHall, "concertHall", 10);
        this.eventCode = validateRange(eventCode, "eventCode",100,999);
        this.time = time;
        this.createdAt = System.currentTimeMillis() / 1000L;

    }

    public Sector getStadiumSector() {
        return stadiumSector;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private String validateLength(String value, String varName, int maxLength) {

        if(value.length() > maxLength){
            throw new IllegalArgumentException(varName+ " must be equal or less than " + maxLength+ " symbols long");
        }

        return value;
    }

    private int validateRange(int value, String varName, int minBorder, int maxBorder){

        if (value < minBorder || value > maxBorder) {
            throw new IllegalArgumentException(varName+ " must be between "+ minBorder+" and "+ maxBorder);
        }

        return value;
    }

    @Override
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {

        return "Ticket{" +
                "id='" + getId() + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + time + "ms" +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", allowedWeight=" + allowedWeight + "g" +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return eventCode == ticket.eventCode && time == ticket.time && Float.compare(allowedWeight, ticket.allowedWeight) == 0 && createdAt == ticket.createdAt && Objects.equals(getId(), ticket.getId()) && Objects.equals(concertHall, ticket.concertHall) && Objects.equals(isPromo, ticket.isPromo) && stadiumSector == ticket.stadiumSector && Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), concertHall, eventCode, time, isPromo, stadiumSector, allowedWeight, createdAt, price);
    }
}

enum Sector {

    A,
    B,
    C

}

