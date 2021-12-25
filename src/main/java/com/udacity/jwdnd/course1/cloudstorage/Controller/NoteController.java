package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class NoteController {

    @Autowired
     private UserService userService;

    @Autowired
    private NoteService noteService;


    @PostMapping("/note")
    public String saveNote(@ModelAttribute Note note,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           Authentication authentication){

        redirectAttributes.addFlashAttribute("activeTab","notes");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userService.getUser(username);

        String userName = authentication.getName();
        Integer userId = userService.getUserId(username);

        if(user == null){

            redirectAttributes.addFlashAttribute("message","user is not found");
            return "redirect:/logout";

        }
        if (note.getNoteTitle() == null
                || note.getNoteTitle().isEmpty()
                || note.getNoteDescription() == null
                || note.getNoteDescription().isEmpty()) {
            model.addAttribute("message", "Note fields can't be void!");
        }
        Note noteDb = noteService.getNoteById(note.getNoteId());
        if (noteDb == null) {
            note.setUserId(userId);
            Integer id = noteService.addOrEdit(note);
            redirectAttributes.addFlashAttribute("success", true);
        } else {
            note.setNoteId(user.getUserId());
            Integer id = noteService.update(note);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/result";
        }
        return "redirect:/result";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        int rowsUpdated = noteService.delete(noteId);
        if (rowsUpdated == 1) {
            model.addAttribute("success", true);
            return "result";
        } else {
            model.addAttribute("isError", true);
            return "result";
        }

}
}

