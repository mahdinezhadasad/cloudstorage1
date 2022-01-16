package com.udacity.jwdnd.course1.cloudstorage.Exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleFileMaxUploadError(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "File too big");
        return "redirect:/home";
    }


}