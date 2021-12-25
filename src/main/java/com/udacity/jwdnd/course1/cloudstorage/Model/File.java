package com.udacity.jwdnd.course1.cloudstorage.Model;
public class File {
    private Integer fileId;
    private String fileName;
    private String ContentType;
    private Long fileSize;
    private Integer userId;
    private byte[]  fileData;
    public String getName() {
        return fileName;
    }
    public void setName(String name) {
        this.fileName = name;
    }
    public String getType() {
        return ContentType;
    }
    public void setType(String type) {
        ContentType = ContentType;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getContentType() {
        return ContentType;
    }
    public void setContentType(String contentType) {
        ContentType = contentType;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public byte[] getFileData() {
        return fileData;
    }
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}