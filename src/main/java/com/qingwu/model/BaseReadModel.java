package com.qingwu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseReadModel extends BaseRowModel {

	@ExcelProperty(index = 0)
	protected String str;

	@ExcelProperty(index = 1)
	protected Float ff;
}
