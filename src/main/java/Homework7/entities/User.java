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
    Date creationDate;

    public User(String name, Date creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
