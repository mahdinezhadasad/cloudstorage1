package com.udacity.jwdnd.course1.cloudstorage.Controller;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private  FileService fileService;
    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    EncryptionService encryptionService;

    @GetMapping
    public String homeView(Authentication authentication, Model model){
        Integer currentUserId = userService.getUser(authentication.getName()).getUserId();
        User user = userService.getUser(authentication.getName());
        model.addAttribute("files", fileService.getFiles(currentUserId));
        List<Note> notes = noteService.getAllNotes(userService.getUserById(currentUserId).getUserId());
        List<Credential> credentials = credentialService.getCredentialListById(userService.getUser(authentication.getName()).getUserId());
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService",encryptionService);
        return "/home";}
}