package com.qingwu.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.qingwu.listen.ExcelListener;
import com.qingwu.model.ReadModel;
import com.qingwu.model.ReadModel2;
import com.qingwu.model.UserLikeModel;
import com.qingwu.util.FileUtil;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReadTest {

	@Test
    public void saxReadUserLike() throws IOException {
		Long begain = System.currentTimeMillis();
    	File file = new File("/Users/prempeng/Downloads/2017.xlsx");
    	
        InputStream inputStream = new FileInputStream(file);//FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 2, UserLikeModel.class), excelListener);
        System.out.println(System.currentTimeMillis() - begain);
        inputStream.close();
    }
	
	@Test
    public void simpleReadUserLike() throws IOException {
		Long begain = System.currentTimeMillis();
		File file = new File("/Users/prempeng/Downloads/2017.xlsx");
    	
        InputStream inputStream = new FileInputStream(file);
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 2, UserLikeModel.class));
        print(data);
        Long time = System.currentTimeMillis() - begain;
        System.err.println(time);
        inputStream.close();
    }
	
	/**
     * 07版本excel读数据量少于1千行数据，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadListStringV2007() throws IOException {
    	File file = new File("/Users/prempeng/Downloads/blank horse/2007.xlsx");
//    	file.
        InputStream inputStream = new FileInputStream(file);//FileUtil.getResourcesFileInputStream("2007.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        print(data);
    }


    /**
     * 07版本excel读数据量少于1千行数据自动转成javamodel，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadJavaModelV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 1, ReadModel.class));
        inputStream.close();
        print(data);
    }

    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadListStringV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
        inputStream.close();

    }
    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadJavaModelV2007() throws IOException {
    	File file = new File("/Users/prempeng/Downloads/blank horse/2007.xlsx");
    	
        InputStream inputStream = new FileInputStream(file);//FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(2, 1, ReadModel.class), excelListener);
        inputStream.close();
    }

    /**
     * 07版本excel读取sheet
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadSheetsV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream,excelListener);
        List<Sheet> sheets = excelReader.getSheets();
        System.out.println("llll****"+sheets);
        System.out.println();
        for (Sheet sheet:sheets) {
            if(sheet.getSheetNo() ==1) {
                excelReader.read(sheet);
            }else if(sheet.getSheetNo() ==2){
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel.class);
                excelReader.read(sheet);
            }else if(sheet.getSheetNo() ==3){
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel2.class);
                excelReader.read(sheet);
            }
        }
        inputStream.close();
    }



    /**
     * 03版本excel读数据量少于1千行数据，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadListStringV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        print(data);
    }

    /**
     * 03版本excel读数据量少于1千行数据转成javamodel，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadJavaModelV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 1, ReadModel.class));
        inputStream.close();
        print(data);
    }

    /**
     * 03版本excel读数据量大于1千行数据，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadListStringV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(2, 1), excelListener);
        inputStream.close();
    }

    /**
     * 03版本excel读数据量大于1千行数据转成javamodel，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadJavaModelV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(2, 1, ReadModel.class), excelListener);
        inputStream.close();
    }

    /**
     * 00版本excel读取sheet
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadSheetsV2003() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream,excelListener);
        List<Sheet> sheets = excelReader.getSheets();
        System.out.println();
        for (Sheet sheet:sheets) {
            if(sheet.getSheetNo() == 1) {
                excelReader.read(sheet);
            }else {
                sheet.setHeadLineMun(2);
                sheet.setClazz(ReadModel.class);
                excelReader.read(sheet);
            }
        }
        inputStream.close();
    }


    public void print(List<Object> datas){
        int i=0;
        for (Object ob:datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }
    
}
