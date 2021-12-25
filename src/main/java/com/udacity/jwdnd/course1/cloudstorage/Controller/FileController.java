package com.udacity.jwdnd.course1.cloudstorage.Controller;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FileController {
    private final UserService userService;
    private final FileService fileService;
    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, RedirectAttributes redirectAttributes){
        if(fileUpload.isEmpty()){
            redirectAttributes.addFlashAttribute("fileError",true);
            redirectAttributes.addFlashAttribute("file Error Message","No such file uploaded");
            return "redirect:/home";
        }
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        try{
            File file = this.fileService.createFile(fileUpload,userId);
            if(this.fileService.getFileByName(file.getFileName(), userId)  != null){
                redirectAttributes.addFlashAttribute("fileError", true);
                redirectAttributes.addFlashAttribute("fileErrorMessage","File with this name already exist");
            }
            else{
                this.fileService.addOrEditFile(file);
                redirectAttributes.addFlashAttribute("fileSucess",true);
                redirectAttributes.addFlashAttribute("message","File that name already exist");
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("fileError",true);
            redirectAttributes.addFlashAttribute("fileErrorMessage",
                    "Error: " + e.getMessage());
        }
        redirectAttributes.addFlashAttribute("activeTab", "files");
        return "redirect:/home";
    }
    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId, RedirectAttributes redirectAttributes){
        this.fileService.deleteFile(fileId);
        redirectAttributes.addFlashAttribute("activeTab", "files");
        return "redirect:/home";
    }




    @GetMapping("/download")
    public @ResponseBody
    byte[] getFileContent(@Param(value = "filename") String filename,
                          Model model,
                          RedirectAttributes redirectAttributes,
                          HttpServletResponse res) {
        redirectAttributes.addFlashAttribute("activeTab", "files");
        File file = fileService.getFileByName(filename);
        if (file != null) {
            res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            res.setHeader("Content-Disposition",
                    "attachment; filename=" + file.getName());
            try {
                ServletOutputStream outputStream = res.getOutputStream();
                outputStream.write(file.getFileData());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
            return file.getFileData();
        }
        return null;
    }
 /* @PostMapping("/download")
  public ResponseEntity downloadFile(@RequestParam("fileDownloadId") Integer id, Authentication authentication) {
      Integer userId = userService.getUserId(authentication.getName());
      File file = fileService.getFileById(id);
      if (file == null) {
          //TODO: "File not found!"
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(file.getContentType()))
              .header(HttpHeaders.CONTENT_DISPOSITION,
                      "inline; filename=\"" + file.getFileName() + "\"")
              .body(file.getFileData());
  }
    @GetMapping({"/download","/download/{id:.+}"})
    public String downloadFileGet() {
        return "redirect:/home?view";
    }
*/
}