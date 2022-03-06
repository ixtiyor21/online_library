package uz.ixtiyor.services.auth;

import org.springframework.stereotype.Service;
import uz.ixtiyor.dao.AuthUserDao;
import uz.ixtiyor.models.Login;

import java.util.List;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 06.03.2022 11:30
 * Project : Spring_mvc_book_crud_my_version
 */
@Service
public class LoginService {

    private final AuthUserDao authUserDao;

    public LoginService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    public List<Login> getAll() {
        return authUserDao.getAll();
    }

    public Boolean checkUser(Login login) {
        List<Login> users = getAll();
        for (Login user : users) {
            if(user.getUsername().equals(login.getUsername())&&user.getPassword().equals(login.getPassword())
            &&user.getRole().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }
}
