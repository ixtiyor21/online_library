package uz.ixtiyor.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public String errorjon(NotFoundException e, Model model, WebRequest request) {
        String path = ((ServletWebRequest) (request)).getRequest().getRequestURI();
        model.addAttribute("message", e.getMessage());
        model.addAttribute("status", e.getStatus().value());
        model.addAttribute("error", e.getStatus().getReasonPhrase());
        model.addAttribute("path", path);
        return "error/404";
    }

}
