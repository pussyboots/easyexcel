package com.qingwu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qingwu.pojo.UserLike;
import com.qingwu.repository.UserRepository;
import com.qingwu.service.CreateExcelService;

@RestController
@RequestMapping("/user")
public class UserLikeController {

	@Autowired
	private UserRepository repository;
	@Autowired
	private CreateExcelService createExcelServiceImpl;
	
	@GetMapping("/add")
	public String addData() {
		List<UserLike> userLikes = new ArrayList<UserLike>();
		for (int i = 0; i < 3000; i++) {
			UserLike like = UserLike.builder().name("张三" + i).fav("橘子" + i).createTime(new Date()).build();
			userLikes.add(like);
		}
		repository.saveAll(userLikes);
		return "success";
	}
	
	@GetMapping("/list")
	public String list() {
		List<UserLike> likes = repository.findAll();
		createExcelServiceImpl.createExcel(likes);
		return "success";
	}
	
	@GetMapping("/insert")
	public Long insert() {
		UserLike like = UserLike.builder().name("张三09").fav("橘子09").createTime(new Date()).build();
		UserLike userLike = repository.save(like);
		return userLike.getId();
	}
	
	@GetMapping("/excel")
	public void insetFromExcel() {
		List<UserLike> likes = createExcelServiceImpl.readExcel();
		repository.saveAll(likes);
	}
	
	@GetMapping("/bigExcel")
	public void insertBigExcel() {
		createExcelServiceImpl.readBigExcel();
	}
}
