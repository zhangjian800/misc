package com.dolphin.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.biz.AdsMgr;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.AppMgrImpl;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.PublicMgrImpl;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.biz.RuleMgrImpl;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.vo.App;

public class PreLoadServlet extends HttpServlet {

	private WebApplicationContext wac = null;
	CacheMgr cacheMgr = null;

	/**
	 * Initialize method which will start a new thread to do preloading.
	 */
	public void init() throws ServletException {
		wac = (WebApplicationContext) this.getServletContext().getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		cacheMgr = (CacheMgr) wac.getBean("cacheMgr");
		

		new Thread() {
			public void run() {
				initializeData();
			}
		}.start();
	}

	
	private void initializeAdsData () {
		long startTime = System.currentTimeMillis();
		try {
			AdsMgr adsMgr = (AdsMgr) wac.getBean("adsMgr");
			adsMgr.firstLoadAds();
			adsMgr.commitAds();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out.println("Dolphin System Info>>>>>>>>initializes cache Obj costs "
				+ (System.currentTimeMillis() - startTime));
	}
	
	private void initializeSystem() {
		long startTime = System.currentTimeMillis();
		
		PublicMgr pubMgr = (PublicMgr) wac.getBean("pubMgr");
		try {
			cacheMgr.flushCacheEntry(PublicMgrImpl.CACHE_KEY_SYSTEMRULE);
			
			pubMgr.getSystemConfig();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out.println("Dolphin System Info>>>>>>>>initializes sysemconfig costs "
				+ (System.currentTimeMillis() - startTime));
	}

	private void initializeBasicData() {
		long startTime = System.currentTimeMillis();
		PublicMgr pubMgr = (PublicMgr) wac.getBean("pubMgr");
		try {
			pubMgr.reloadProvinceAndCities();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out
				.println("Dolphin System Info>>>>>>>>initializes all provinces and cities costs "
						+ (System.currentTimeMillis() - startTime));
	}

	private void initializeProducts() {
		long startTime = System.currentTimeMillis();
		PublicMgr pubMgr = (PublicMgr) wac.getBean("pubMgr");
		startTime = System.currentTimeMillis();
		try {
			cacheMgr.flushCacheEntry(PublicMgrImpl.CACHE_KEY_ALL_PRODUCTS);
			pubMgr.getAllProducts();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out
				.println("Dolphin System Info>>>>>>>>initializes all products costs "
						+ (System.currentTimeMillis() - startTime));
	}	
	
	private void initializeVersionAndRules(String versioncode) {
		long startTime = System.currentTimeMillis();
		RuleMgr ruleMgr = (RuleMgr) wac.getBean("ruleMgr");
		startTime = System.currentTimeMillis();
		try {
			ruleMgr.reloadRules(versioncode);
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out
				.println("Dolphin System Info>>>>>>>>initializes all rules costs "
						+ (System.currentTimeMillis() - startTime));

	}

	private void initializeApps(int appID) {
		long startTime = System.currentTimeMillis();
		AppMgr appMgr = (AppMgr) wac.getBean("appMgr");
		startTime = System.currentTimeMillis();
		try {
			appMgr.reloadAllApps();
			List<App> apps = appMgr.listAllApp();
			if(appID>0){
				cacheMgr.flushCacheEntry(AppMgrImpl.CACHE_KEY_APPSTREAM
						+ appID);
				appMgr.reloadAppStream(appID);
			}else{
				for (int i = 0; i < apps.size(); i++) {
					App app = apps.get(i);
					appMgr.reloadAppStream(app.getAppID());
				}
			}
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out.println("Dolphin System Info>>>>>>>>initializes all apps cost "
				+ (System.currentTimeMillis() - startTime));
	}

	//Reload
	private void initializePhoneNums() {
		long startTime = System.currentTimeMillis();
		PublicMgr pubMgr = (PublicMgr) wac.getBean("pubMgr");
		startTime = System.currentTimeMillis();
		try {
			pubMgr.reloadPhoneNumTypes();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		System.out
				.println("Dolphin System Info>>>>>>>>initializes all phone prefix costs "
						+ (System.currentTimeMillis() - startTime));
	}

	public void initializeData() {
		long startTime = System.currentTimeMillis();
		initializeSystem();
		initializeBasicData();
		initializeVersionAndRules(null);
		
		initializeProducts();
		initializeApps(0);
		initializePhoneNums();
		
		initializeAdsData();

		System.out.println("Dolphin System Info>>>>>>>>initializes all data costs "
				+ (System.currentTimeMillis() - startTime));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String action = request.getParameter("action");
		CacheMgr cacheMgr = (CacheMgr) wac.getBean("cacheMgr");
		RuleMgr ruleMgr = (RuleMgr) wac.getBean("ruleMgr");
		if("getcache".equalsIgnoreCase(action)){
			
		} else {
			if ("all".equalsIgnoreCase(type)) {
				try {
					cacheMgr.flushAll();
				} catch (GenericException e) {
					e.printStackTrace();
				}
				initializeData();
			} else if ("system".equalsIgnoreCase(type)) {
				initializeSystem();
			} else if ("app".equalsIgnoreCase(type)) {
				String arg1 =  request.getParameter("arg1");
				int appID = 0;
				if(arg1!=null && arg1.length()>0){
					try {
						appID = Integer.parseInt(arg1);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				initializeApps(appID);
			} else if ("rule".equalsIgnoreCase(type)) {
				String arg1 =  request.getParameter("arg1");
				String arg2 =  request.getParameter("arg2");
				
//				if("accessRule".equalsIgnoreCase(type)){
//					ruleMgr.
//				}
//				
				initializeVersionAndRules(arg1);
			} else  {
				System.out.println("==================do nothing============");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write("OKOKOK");
			pw.flush();
		}

	}
}
