package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class AdsDto {

    private int pk;

    @NotBlank
    @Size(min = 8)
    private String title;

    @NotBlank
    @Size(min = 8)
    private String description;

    private List<String> imagesDto;

    private int price;

    private int author;

}
