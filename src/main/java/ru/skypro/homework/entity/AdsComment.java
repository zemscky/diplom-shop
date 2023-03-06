package ru.skypro.homework.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "ads_comment")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Ads ads;

    @Column(name = "comment_time")
    private LocalDateTime createdAt;

    @Column(name = "comment_text")
    @NotNull
    private String text;

}
