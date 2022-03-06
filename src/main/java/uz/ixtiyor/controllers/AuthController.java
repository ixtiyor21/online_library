package uz.ixtiyor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.ixtiyor.exceptions.NotFoundException;
import uz.ixtiyor.models.Login;
import uz.ixtiyor.services.auth.LoginService;

@Controller
@RequestMapping("")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String loginPage() {
        return "auth/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(Login login) {

     if(loginService.checkUser(login)){
         return "redirect:/book/list/";
     };
        throw new NotFoundException("User not found");
    }

}
