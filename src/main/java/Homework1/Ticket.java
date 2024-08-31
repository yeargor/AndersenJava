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

        if (id.length() > 4) {
            throw new IllegalArgumentException("id must be equal or less than 4 symbols long");
        }
        this.id = id;

        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("concertHall must be equal or less than 10 symbols long");
        }
        this.concertHall = concertHall;

        if (eventCode < 100 || eventCode > 999) {
            throw new IllegalArgumentException("eventCode must be 3 digits long");
        }
        this.eventCode = eventCode;

        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.allowedWeight = allowedWeight;
        this.createdAt = System.currentTimeMillis() / 1000L; // will be stored in seconds now

    }

    //limited ticket constructor
    public Ticket(String concertHall, int eventCode, long time) {

        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("concertHall must be equal or less than 10 symbols long");
        }
        this.concertHall = concertHall;

        if (eventCode < 100 || eventCode > 999) {
            throw new IllegalArgumentException("eventCode must be 3 digits long");
        }
        this.eventCode = eventCode;

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

}

enum Sector {

    A,
    B,
    C

}

