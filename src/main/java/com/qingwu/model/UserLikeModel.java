package com.qingwu.model;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLikeModel extends BaseRowModel {

	@ExcelProperty(value = {"主键ID", "主键ID"},index = 0)
	private Long id;

	@ExcelProperty(value = {"名字", "name"},index = 1)
	private String name;

	@ExcelProperty(value = {"爱好", "favorite"},index = 2)
	private String fav;

	@ExcelProperty(value = {"时间", "时间"}, index = 3,format = "yyyy-MM-dd")
	private Date createTime;

	@Override
	public String toString() {
		return "UserLikeModel [id=" + id + ", name=" + name + ", fav=" + fav + ", createTime=" + createTime + "]";
	}
	
}
