package Homework7.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class User {

    int id;
    String name;
    Date creation_date;

    //id field is generated
    public User(String name, Date creation_date) {
        this.name = name;
        this.creation_date = creation_date;
    }
}
