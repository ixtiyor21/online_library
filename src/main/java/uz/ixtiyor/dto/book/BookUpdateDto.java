package uz.ixtiyor.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 11.02.2022 11:13
 * Project : spring-b3
 */
@Getter
@Setter
public class BookUpdateDto {
    private UUID uuid;
    private String name;
    private String author;
    private String description;
    private Integer pageCount;
}
