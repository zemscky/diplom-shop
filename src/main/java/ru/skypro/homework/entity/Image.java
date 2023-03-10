package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filePath;

    private long fileSize;

    private String mediaType;

    @Lob
    @Type(type = "binary")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    private Ads ad;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String toString() {
        return "Ads(id=" + this.getId() + ", image=" + Arrays.toString((this.getData())) + ")";
    }

}
