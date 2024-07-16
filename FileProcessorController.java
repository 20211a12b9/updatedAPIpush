package com.vms.medxbid.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileProcessorController {

    @Value("${upload.directory}")
    private String uploadDirectory1;

    @Value("${upload.directory2}")
    private String uploadDirectory2;


    @PostMapping("/handleFileUpload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Please select a file to upload");
        }

        try {
            // Create the directory if it doesn't exist
            File directory = new File(uploadDirectory1);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directories along the path if they don't exist
            }

            // Define the file path where you want to store the uploaded file
            String filePath = uploadDirectory1 + File.separator + file.getOriginalFilename();

            // Create a new file with the specified path
            File uploadedFile = new File(filePath);

            // Write the bytes to the file
            try (FileOutputStream outputStream = new FileOutputStream(uploadedFile)) {
                outputStream.write(file.getBytes());
            }

            System.out.println("File uploaded successfully: " + file.getOriginalFilename());
            return ResponseEntity.ok(filePath); // Return the file path

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to upload file");
        }
    }

    @PostMapping("/distributorInventory")
    public ResponseEntity<String> handleDistributorInventoryUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Please select a file to upload");
        }

        try {
            // Create the directory if it doesn't exist
            File directory = new File(uploadDirectory2);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directories along the path if they don't exist
            }

            // Define the file path where you want to store the uploaded file
            String filePath = uploadDirectory2 + File.separator + file.getOriginalFilename();

            // Create a new file with the specified path
            File uploadedFile = new File(filePath);

            // Write the bytes to the file
            try (FileOutputStream outputStream = new FileOutputStream(uploadedFile)) {
                outputStream.write(file.getBytes());
            }

            System.out.println("File uploaded successfully: " + file.getOriginalFilename());
            return ResponseEntity.ok(filePath); // Return the file path

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to upload file");
        }
    }
}
