package uz.ixtiyor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.ixtiyor.models.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 06.03.2022 11:16
 * Project : Spring_mvc_book_crud_my_version
 */
@Repository
public class AuthUserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Login> getAll() {
        return jdbcTemplate.query("select id,username,password,role from auth_user", new AuthUserDao.UserRowMapper());
    }


    public static class UserRowMapper implements RowMapper<Login> {
        public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
            Login login=Login.builder()
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .role(rs.getString("role"))
                    .build();
            return login;
        }
    }


}
