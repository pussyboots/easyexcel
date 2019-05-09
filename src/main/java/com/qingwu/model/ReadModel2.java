package com.qingwu.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadModel2 extends BaseRowModel {

	@ExcelProperty(index = 0)
    private String str;

    @ExcelProperty(index = 1)
    private Float ff;

    @ExcelProperty(index = 2)
    private Integer mm;

    @ExcelProperty(index = 3)
    private BigDecimal money;

    @ExcelProperty(index = 4)
    private Long times;

    @ExcelProperty(index = 5)
    private Double activityCode;

    @ExcelProperty(index = 6,format = "yyyy-MM-dd")
    private Date date;

    @ExcelProperty(index = 7)
    private String lx;

    @ExcelProperty(index = 8)
    private String name;

    @ExcelProperty(index = 18)
    private String kk;

	@Override
	public String toString() {
		return "ReadModel2 [str=" + str + ", ff=" + ff + ", mm=" + mm + ", money=" + money + ", times=" + times
				+ ", activityCode=" + activityCode + ", date=" + date + ", lx=" + lx + ", name=" + name + ", kk=" + kk
				+ "]";
	}
    
}
