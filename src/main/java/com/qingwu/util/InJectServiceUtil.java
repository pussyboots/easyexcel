package com.qingwu.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qingwu.service.CreateExcelService;

@Component
public class InJectServiceUtil {

	@Autowired
	private CreateExcelService createExcelService;
	
	@PostConstruct
	public void init() {
		InJectServiceUtil.getInStance().createExcelService = this.createExcelService;
	}

	/**
	 *  实现单例 start
	 */
	private static class SingletonHolder {
	    private static final InJectServiceUtil INSTANCE = new InJectServiceUtil();
	}

	private InJectServiceUtil (){}
	public static final InJectServiceUtil getInStance() {
	    return SingletonHolder.INSTANCE;
	}
	/**
	 *  实现单例 end
	 */
	public CreateExcelService getCommodityService(){
	    return InJectServiceUtil.getInStance().createExcelService;
	}
}
