package com.vms.medxbid.models;

public class FileUpload {
    private  String originalFilename;
    private  String contentType;
    private  String content;


    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
