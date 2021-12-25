package com.udacity.jwdnd.course1.cloudstorage.Exceptions;


import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ExceptionController  implements HandlerExceptionResolver{
    @Autowired
    private NoteService notesService;
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialService credentialsService;

    private User user;
    @Autowired
    private FileService filesService;



    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandle(Model model)
    {
        model.addAttribute("error","URL Not Found");
        setModelAttributes(model);
        return "home";
    }

    public void setModelAttributes(Model model)
    {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                user=userService.getUser(authentication.getName());
            }
            model.addAttribute("newNote",new Note());
            model.addAttribute("newCredential",new Credential());
            model.addAttribute("Notes",notesService.getAllNotes(user.getUserId()));
            model.addAttribute("credentials",credentialsService.getCredentials(user.getUserId()));
            model.addAttribute("newFiles",filesService.getFiles(user.getUserId()));
        }
        catch (Exception e)
        {
            System.out.println(" msg "+e);
        }

    }

    public void setModelAndView(ModelAndView modelAndView){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                user=userService.getUser(authentication.getName());
            }
            modelAndView.addObject("newNote",new Note());
            modelAndView.addObject("newCredential",new Credential());
            modelAndView.addObject("Notes",notesService.getAllNotes(user.getUserId()));
            modelAndView.addObject("credentials",credentialsService.getCredentials(user.getUserId()));
            modelAndView.addObject("newFiles",filesService.getFiles(user.getUserId()));
        }
        catch (Exception e)
        {
            System.out.println(" msg "+e);
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();

        if(e instanceof MaxUploadSizeExceededException ){
            modelAndView = new ModelAndView();
            setModelAndView(modelAndView);
            modelAndView.addObject("error", "File too large too large too upload");
            modelAndView.setViewName("home.html");
            return modelAndView;
        }
        modelAndView.setViewName("home.html");
        return modelAndView;
    }
}
