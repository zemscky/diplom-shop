package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "ads_comments")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt;

    private String text;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pk_ads")
    private Ads ad;

}
