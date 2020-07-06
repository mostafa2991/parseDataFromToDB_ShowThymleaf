package com.mostafa.spring.controller;

import com.mostafa.spring.entities.User;
import com.mostafa.spring.repos.UserRepository;
import com.mostafa.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UploadController2 {
	
	@Autowired
	UserService userService;  
	@Autowired
	UserRepository userRepository;  

    @GetMapping("/")
    public String index() {
        return "index2";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
    	// validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
        	
        	try {
				List<User> returnedUser = userService.uploadCSVFile(file);
				// save users in DB? 
				userRepository.saveAll(returnedUser);
				// show first 10 users
				List<User> findTop10ByOrderByClientDealIdAsc = userService.findTop10ByOrderByClientDealIdAsc();
				model.addAttribute("users", findTop10ByOrderByClientDealIdAsc);
				model.addAttribute("status", true);
			} catch (Exception e) {
				model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
			}
        }
        
        
		return "index2";
        
    }
    
    
   
}




















