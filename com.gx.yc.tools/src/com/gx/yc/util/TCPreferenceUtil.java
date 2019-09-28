package com.gx.yc.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;



public class TCPreferenceUtil {
	
	private static TCPreferenceService tcps = null;
	private String PREF_CUST_CUSTOMCOPY = "PREF_CUST_CUSTOMCOPY";
	
	
	public static HashMap<String, String> store_hashmap = new HashMap<String, String>();
	
	public static ArrayList<String> store_list = new ArrayList<String>();
	
	public TCPreferenceUtil(int preferenceType) {
		TCSession session = (TCSession) AIFUtility.getDefaultSession();
		tcps = session.getPreferenceService();
	}
	
	public void pref_CustomCopy(){
		store_list.clear();
		String[] pres = tcps.getStringValues("PREF_CUST_CUSTOMCOPY");
		if(pres==null||pres.length==0){
			MessageBox.post("首选项" + PREF_CUST_CUSTOMCOPY
							+"配置有误，请与系统管理员联系！","操作异常",MessageBox.ERROR);
			return;
		}
		
		for (String pre : pres) {
//			if(pre!=null&&pre.indexOf("=")!=-1){
//				String[] splits = pre.split("=");
//				store_hashmap.put(splits[0], splits[1]);
//			}else{
//				MessageBox.post("首选项" + PREF_CUST_CUSTOMCOPY
//						+"配置值"+pre+"有误，请与系统管理员联系！","操作异常",MessageBox.ERROR);
//				return;
//			}
			if(pre==null||"".equals(pre)||pre.equalsIgnoreCase("null")) continue;
			store_list.add(pre);
		}
	}
}
