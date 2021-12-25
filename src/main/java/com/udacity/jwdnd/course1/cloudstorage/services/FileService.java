package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
@Service
public class FileService {
    private FileMapper fileMapper;
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }
    public void addFile(File file) {
        fileMapper.addFile(file);
    }
    public Integer addOrEditFile(File file) {
        if (file.getFileId() == null){
            return this.fileMapper.addFile(file);
        }
        else{
            return this.fileMapper.updateFileByefileId(file);
        }
    }
    public File createFile(MultipartFile multipartFile, Integer userId) throws IOException {
        File file = new File();
        file.setUserId(userId);
        file.setName(multipartFile.getOriginalFilename());
        file.setType(multipartFile.getContentType());
        file.setFileData(multipartFile.getBytes());
        file.setFileSize(multipartFile.getSize());
        return file;
    }
    public Integer deleteFile(Integer fileId){
        return this.fileMapper.deleteFile(fileId);
    }
    public List<File> getFiles(Integer userId) {
        return fileMapper.getAllFiles(userId);
    }
    public File getFileById(Integer fileId){ return this.fileMapper.getFileById(fileId); }
    public File getFileByName(String fileName, int userId){ return this.fileMapper.getFileByNameAndUserId(fileName, userId); }
    public Set<File> getFilesById(Integer id) {
        return fileMapper.getFilesById(id);
    }

    public File getFileByName(String filename) {

        return fileMapper.getFileByName(filename);
    }
}