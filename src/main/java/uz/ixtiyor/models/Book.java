package uz.ixtiyor.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.ixtiyor.dto.file.ResourceDto;

import java.util.UUID;


@Builder
@Getter
@Setter
public class Book {
    private UUID id;
    private String name;
    private Integer pageCount;
    private String author;
    private String description;
    private ResourceDto resourceBook;
    private String pathImage;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book(UUID id, String name, Integer pageCount, String author, String description, ResourceDto resourceBook,String pathImage) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
        this.resourceBook = resourceBook;
        this.pathImage=pathImage;
        this.description=description;
    }

    public String getIdAsString() {
        return this.id.toString();
    }
}
