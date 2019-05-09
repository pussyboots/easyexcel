package com.qingwu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.qingwu.listen.ExcelListener;
import com.qingwu.model.UserLikeModel;
import com.qingwu.pojo.UserLike;
import com.qingwu.repository.UserRepository;
import com.qingwu.util.DataUtil;

import lombok.extern.slf4j.Slf4j;

@Service("createExcelServiceImpl")
@Slf4j
public class CreateExcelServiceImpl implements CreateExcelService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public void createExcel(List<UserLike> likes) {
		
		OutputStream out =  null;
		try {
			out = new FileOutputStream("/Users/prempeng/Downloads/2017.xlsx");
			ExcelWriter writer = EasyExcelFactory.getWriter(out);
			
			//写第二个sheet sheet2  模型上打有表头的注解，合并单元格
	        Sheet sheet1 = new Sheet(2, 3, UserLikeModel.class, "第二个sheet", null);
	        sheet1.setTableStyle(DataUtil.createTableStyle());
	        sheet1.setAutoWidth(Boolean.TRUE);
	        
	        List<UserLikeModel> model1s = new ArrayList<UserLikeModel>();
	        for (UserLike userLike : likes) {
	        	UserLikeModel userLikeModel = new UserLikeModel();
	        	BeanUtils.copyProperties(userLike, userLikeModel);
	        	model1s.add(userLikeModel);
			}
	        writer.write(model1s, sheet1);
			writer.finish();
			
		} catch (Exception e) {
			log.info("创建Excel异常");
		} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					log.info("流关闭异常");
				}
		}
	}
	
	@Override
	public List<UserLike> readExcel() {
		InputStream inputStream =  null;
		try {
			File file = new File("/Users/prempeng/Downloads/2017.xlsx");
	    	
	        inputStream = new FileInputStream(file);
	        //Sheet(1, 2) 1表示表格有1页 2表示表头有两行
	        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 2, UserLikeModel.class));
	        
	        return print(data);
		} catch (Exception e) {
			log.info("读取Excel异常");
		} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					log.info("流关闭异常");
			}
		}
		return null;
	}
	
	public List<UserLike> print(List<Object> datas) {
		List<UserLike> likes = new ArrayList<UserLike>();
        for (Object ob:datas) {
        	UserLike userLikeModel = new UserLike();
        	BeanUtils.copyProperties(ob, userLikeModel);
        	likes.add(userLikeModel);
        }
        return likes;
    }
	
	@Override
	public void readBigExcel() {
		InputStream inputStream = null;
		try {
			File file = new File("/Users/prempeng/Downloads/2017.xlsx");
	    	
	        inputStream = new FileInputStream(file);
	        ExcelListener excelListener = new ExcelListener();
	        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 2, UserLikeModel.class), excelListener);
		} catch (Exception e) {
			log.info("读取Excel异常");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.info("流关闭异常");
			}
		}
	}
	
	@Override
	public void insert(List<UserLike> likes) {
		for (UserLike userLike : likes) {
			repository.save(userLike);
		}
//		repository.saveAll(likes);
	}
}
