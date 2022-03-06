package uz.ixtiyor.services.book;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.ixtiyor.dao.BookDao;
import uz.ixtiyor.dto.book.BookCreateDto;
import uz.ixtiyor.dto.book.BookUpdateDto;
import uz.ixtiyor.dto.file.ResourceDto;
import uz.ixtiyor.exceptions.NotFoundException;
import uz.ixtiyor.mappers.BookMapper;
import uz.ixtiyor.models.Book;
import uz.ixtiyor.services.file.FileStorageService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private final BookMapper mapper;

    private final BookDao bookDao;

    private final FileStorageService fileStorageService;


    public BookServiceImpl(BookMapper mapper, BookDao bookDao, FileStorageService fileStorageService) {
        this.mapper = mapper;
        this.bookDao = bookDao;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public Book get(String id) {
        Optional<Book> bookOptional=bookDao.get(id);
        if (bookOptional.isEmpty())
            throw new NotFoundException(String.format("Book with id %s not found", id), HttpStatus.NOT_FOUND);
        return bookOptional.get();
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public String create(BookCreateDto dto, MultipartFile file, MultipartFile image) throws IOException {
        ResourceDto resourceBook = fileStorageService.store(file);
        ResourceDto resourceImage = fileStorageService.store(image);
        Book book = mapper.toEntity(dto);
        book.setResourceBook(resourceBook);
        book.setPathImage(resourceImage.getPath());
        return bookDao.create(book);
    }

    @Override
    public void delete(String id) {
        if(!bookDao.delete(id)) {
            throw new NotFoundException(String.format("Book with id %s not found", id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean update(BookUpdateDto dto) {
        Book book = get(dto.getUuid().toString());
        if (dto.getName().equals(""))
            dto.setName(book.getName());

        if (dto.getAuthor().equals(""))
            dto.setAuthor(book.getAuthor());

        if (Objects.isNull(dto.getPageCount()))
            dto.setPageCount(book.getPageCount());

        if (dto.getDescription().equals(""))
            dto.setDescription(book.getDescription());

        Book book1 = mapper.toEntity(dto);
        return bookDao.update(book1);
    }
}
