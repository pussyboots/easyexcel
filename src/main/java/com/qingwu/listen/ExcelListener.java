package com.qingwu.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qingwu.pojo.UserLike;
import com.qingwu.service.CreateExcelService;
import com.qingwu.util.InJectServiceUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ExcelListener extends AnalysisEventListener {

	private List<Object>  data = new ArrayList<Object>();
	
	@Override
    public void invoke(Object object, AnalysisContext context) {
        data.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }
    
    public void doSomething(){
//        for (Object o:data) {
//            System.out.println(o);
//        }
        List<UserLike> likes = print(data);
        try {
        	InJectServiceUtil inJectServiceUtil = InJectServiceUtil.getInStance();
        	CreateExcelService excelService = inJectServiceUtil.getCommodityService();
        	excelService.insert(likes);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
