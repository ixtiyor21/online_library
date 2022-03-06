package uz.ixtiyor.dto.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookCreateDto {
    private String name;
    private String author;
    private String description;
    private Integer pageCount;
}
