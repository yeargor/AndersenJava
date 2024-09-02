package Homework1;

import java.math.BigDecimal;

public class Ticket {

    private String id;
    private String concertHall;
    private int eventCode;
    private long time;
    private Boolean isPromo;
    private Sector stadiumSector;
    private float allowedWeight;
    private long createdAt;

    private final BigDecimal price = new BigDecimal("9.99");


    //empty ticket constructor, time of creation only
    public Ticket() {

        this.createdAt = System.currentTimeMillis() / 1000L; // will be stored in seconds now

    }

    //filled ticket constructor
    public Ticket(String id, String concertHall, int eventCode, long time, Boolean isPromo, Sector stadiumSector, float allowedWeight) {

        this.id = validateLength(id, "id",4);
        this.concertHall = validateLength(concertHall,"concertHall", 10);
        this.eventCode = validateRange(eventCode,"eventCode",100,999);
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.allowedWeight = allowedWeight;
        this.createdAt = System.currentTimeMillis() / 1000L; // will be stored in seconds now

    }

    //limited ticket constructor
    public Ticket(String concertHall, int eventCode, long time) {

        this.concertHall = validateLength(concertHall, "concertHall", 10);
        this.eventCode = validateRange(eventCode, "eventCode",100,999);
        this.time = time;
        this.createdAt = System.currentTimeMillis() / 1000L; // will be stored in seconds now

    }

    @Override
    public String toString() {

        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + time + "ms" +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", allowedWeight=" + allowedWeight + "g" +
                '}';

    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
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

}

enum Sector {

    A,
    B,
    C

}

