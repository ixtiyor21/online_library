package uz.ixtiyor.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.ixtiyor.dto.file.ResourceDto;
import uz.ixtiyor.models.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("select id,name,author,description,originalName,generatedName,size,contentType,path,pathImage,pageCount from book", new BookRowMapper());

    }


    public static class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book=Book.builder()
                    .id((UUID) rs.getObject("id"))
                    .name(rs.getString("name"))
                    .author(rs.getString("author"))
                    .description(rs.getString("description"))
                    .pageCount(rs.getInt("pageCount"))
                    .pathImage(rs.getString("pathImage"))
                    .build();
            ResourceDto resourceBook=ResourceDto.builder()
                    .originalName(rs.getString("originalName"))
                    .generatedName(rs.getString("generatedName"))
                    .size(rs.getLong("size"))
                    .contentType(rs.getString("contentType"))
                    .path(rs.getString("path"))
                    .build();
            book.setResourceBook(resourceBook);
            return book;
        }
    }


    public Optional<Book> get(String id) {
        return jdbcTemplate.queryForObject("select id,name,author,description,originalName," +
                "generatedName,size,contentType,path,pathImage,pageCount from book where id=?", new Object[]{UUID.fromString(id)}, new RowMapper<Optional<Book>>(){
            @Override
            public Optional<Book> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book=Book.builder()
                        .id((UUID) rs.getObject("id"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .description(rs.getString("description"))
                        .pageCount(rs.getInt("pageCount"))
                        .pathImage(rs.getString("pathImage"))
                        .build();
                ResourceDto resourceBook=ResourceDto.builder()
                        .originalName(rs.getString("originalName"))
                        .generatedName(rs.getString("generatedName"))
                        .size(rs.getLong("size"))
                        .contentType(rs.getString("contentType"))
                        .path(rs.getString("path"))
                        .build();
                book.setResourceBook(resourceBook);
                return Optional.of(book);
            }
        });
    }

    public String create(Book book) {
        jdbcTemplate.execute("insert into book(id,name,author,description,originalName,generatedName,size,contentType,path,pathImage,pageCount) values(?,?,?,?,?,?,?,?,?,?,?)",
                new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.setObject(1, book.getId());
                        ps.setString(2, book.getName());
                        ps.setString(3, book.getAuthor());
                        ps.setString(4, book.getDescription());
                        ps.setString(5, book.getResourceBook().getOriginalName());
                        ps.setString(6, book.getResourceBook().getGeneratedName());
                        ps.setLong(7,book.getResourceBook().getSize());
                        ps.setString(8, book.getResourceBook().getContentType());
                        ps.setString(9, book.getResourceBook().getPath());
                        ps.setString(10, book.getPathImage());
                        ps.setInt(11, book.getPageCount());
                        return ps.execute();
                    }
                });
        return book.getIdAsString();
    }


    public boolean delete(String id) {
        String sql = "DELETE FROM book WHERE id = ?";
        Object[] args = new Object[] {UUID.fromString(id)};

        return jdbcTemplate.update(sql, args) == 1;
    }

    public boolean update(Book book) {
        String SQL = "UPDATE book SET name = ? , author = ? , pageCount = ? , description = ?   where id = ?";
        jdbcTemplate.update(SQL, book.getName(),book.getAuthor(),book.getPageCount(),book.getDescription(),book.getId());
        return true;
    }
}