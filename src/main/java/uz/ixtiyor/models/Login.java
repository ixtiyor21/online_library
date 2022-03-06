package uz.ixtiyor.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 06.03.2022 11:17
 * Project : Spring_mvc_book_crud_my_version
 */
@Getter
@Setter
@Builder
public class Login {
    private String username;
    private String password;
    private String role;

}
