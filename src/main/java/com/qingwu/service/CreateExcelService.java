package com.qingwu.service;

import java.util.List;

import com.qingwu.pojo.UserLike;

public interface CreateExcelService {

	public void createExcel(List<UserLike> likes);
	
	public List<UserLike> readExcel();
	
	public void readBigExcel();
	
	public void insert(List<UserLike> likes);
}
