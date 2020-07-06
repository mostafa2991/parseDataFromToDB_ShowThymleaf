package com.mostafa.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mostafa.spring.entities.User;

public interface UserService {
	
	public List<User> uploadCSVFile(MultipartFile file);
	public List<User>findTop10ByOrderByClientDealIdAsc();
}
