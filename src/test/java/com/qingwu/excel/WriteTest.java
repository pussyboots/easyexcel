package com.qingwu.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.qingwu.listen.AfterWriteHandlerImpl;
import com.qingwu.model.UserLikeModel;
import com.qingwu.model.WriteModel;
import com.qingwu.util.FileUtil;
import com.qingwu.util.DataUtil;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WriteTest {

	@Test
	public void writeUserLike() throws IOException {
		OutputStream out = new FileOutputStream("/Users/prempeng/Downloads/2017.xlsx");
		ExcelWriter writer = EasyExcelFactory.getWriter(out);
		Sheet sheet1 = new Sheet(0);
        sheet1.setSheetName("第一个sheet");
        //设置列宽 设置每列的宽度
//        Map columnWidth = new HashMap();
//        columnWidth.put(0,100);columnWidth.put(1,400);columnWidth.put(2,100);columnWidth.put(3,100);
//        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setAutoWidth(Boolean.TRUE);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        writer.write1(DataUtil.createTestListObject(), sheet1);
        
        writer.finish();
        out.close();
	}
	
	@Test
	public void writeUserLike1() throws IOException {
		OutputStream out = new FileOutputStream("/Users/prempeng/Downloads/2017.xlsx");
		ExcelWriter writer = EasyExcelFactory.getWriter(out);
		
		//写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet1 = new Sheet(2, 3, UserLikeModel.class, "第二个sheet", null);
        sheet1.setTableStyle(DataUtil.createTableStyle());
        //writer.write1(null, sheet2);
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write(DataUtil.createTestListUserLikeModel(), sheet1);
        //需要合并单元格
//        writer.merge(5,20,1,1);
		
		writer.finish();
		out.close();
	}
	
	@Test
    public void writeV2007() throws IOException {
        OutputStream out = new FileOutputStream("/Users/prempeng/Downloads/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        //writer.write1(null, sheet2);
        writer.write(DataUtil.createTestListJavaMode(), sheet2);
        //需要合并单元格
        writer.merge(5,20,1,1);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();

    }


    @Test
    public void writeV2007WithTemplate() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("temp.xlsx");
        OutputStream out = new FileOutputStream("/Users/prempeng/Downloads/20071.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream,out,ExcelTypeEnum.XLSX,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");
        sheet1.setStartRow(20);

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        sheet2.setStartRow(20);
        writer.write(DataUtil.createTestListJavaMode(), sheet2);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        sheet3.setStartRow(30);
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();

    }

    @Test
    public void writeV2007WithTemplateAndHandler() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("temp.xlsx");
        OutputStream out = new FileOutputStream("/Users/jipengfei/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(inputStream,out,ExcelTypeEnum.XLSX,true,
            new AfterWriteHandlerImpl());
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");
        sheet1.setStartRow(20);

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        sheet2.setStartRow(20);
        writer.write(DataUtil.createTestListJavaMode(), sheet2);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        sheet3.setStartRow(30);
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();

    }



    @Test
    public void writeV2003() throws IOException {
        OutputStream out = new FileOutputStream("/Users/jipengfei/2003.xls");
        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        writer.write(DataUtil.createTestListJavaMode(), sheet2);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();
    }
}
