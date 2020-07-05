package com.mostafa.spring.controller;

import com.mostafa.spring.entities.User;
import com.mostafa.spring.entities.UserIn;
import com.mostafa.spring.repos.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {
	
	@Autowired
	User user;
	@Autowired
	UserIn userIn;
	@Autowired
	UserRepository userRepository;  
	
	List<User> outPutUsers = new ArrayList<>();

	String line = "";
    String cvsSplitBy = ",";
    String[] users;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            // parse CSV file to create a list of `User` objects
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            	// create csv bean reader
                CsvToBean<UserIn> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(UserIn.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                // convert `CsvToBean` object to list of userIn
                List<UserIn> users = csvToBean.parse();
                // convert `UserIn` object to list of user
                List<User> output = convertUserInToUser(users);
              
                // TODO: save users in DB?  
                userRepository.saveAll(output);
                // save users list on model
                model.addAttribute("users", output);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
    
    
    public List<User> convertUserInToUser(List<UserIn> userin) {
    	for (UserIn userIn2 : userin) {
    		// convert client from userin to user
    		String clientNameAndId = userIn2.getClient();
        	String[] devideclientNameAndId = clientNameAndId.split("@");
        	user.setClientName(devideclientNameAndId[0]);	
        	user.setClientId(Long.valueOf(devideclientNameAndId[1].trim()));
    		// convert deal from userin to user
        	String dealnameAndId = userIn2.getDeal();
        	String[] devidedealNameAndId = dealnameAndId.split("#");
        	user.setDealName(devidedealNameAndId[0]);	
        	user.setDealId(Long.valueOf(devidedealNameAndId[1].trim()));
    		// convert hour from userin to user
        	String dateString =userIn2.getHour();
        	user.setDate(dateParser(dateString));
        	// convert accepted from userin to user
        	user.setAccepted(userIn2.getAccepted());
        	// convert refused from userin to user
        	user.setRefused(userIn2.getRefused());
        	outPutUsers.add(user);
		}
		return outPutUsers;
    	
    }
    
    private LocalDateTime dateParser(final String dateString) {

		String[] patterns = { "u-M-d HH:mm", "u-M-d H:mm" };
		boolean checkDateTimePattern = checkDateTimePattern(patterns, dateString);
		if (checkDateTimePattern) {
			return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(patterns[0]));
		} else {
			return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(patterns[1]));

		}

	}
    private boolean checkDateTimePattern(final String[] patterns, final String dateString) {
		boolean formatCheck = true;
		for (String pattern : patterns) {
			try {
				DateTimeFormatter.ofPattern(pattern).parse(dateString);
				break;
			} catch (Exception e) {
				formatCheck = false;
				break;
			}
		}
		return formatCheck;
	}
}




















