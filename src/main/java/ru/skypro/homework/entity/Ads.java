package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Entity
//@Table(name = "ads")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
