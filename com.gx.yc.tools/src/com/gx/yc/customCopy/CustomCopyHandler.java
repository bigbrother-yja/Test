package com.gx.yc.customCopy;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import com.gx.yc.util.TCPreferenceUtil;
import com.teamcenter.rac.aif.AIFClipboard;
import com.teamcenter.rac.aif.AIFPortal;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.ui.common.RACUIUtil;
import com.teamcenter.rac.util.MessageBox;


public class CustomCopyHandler extends AbstractHandler{
	TCSession session = null;
	ArrayList<TCComponentBOMLine>  target_list = new ArrayList<TCComponentBOMLine>();
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		session = RACUIUtil.getTCSession();
        //AIFClipboard localobject = (AIFClipboard)OSGIUtil.getService(Activator.getDefault(), AIFClipboard.class);
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
		
		//解析首选项
		TCPreferenceUtil prefUtil = new TCPreferenceUtil(TCPreferenceService.TC_preference_site);
		prefUtil.pref_CustomCopy();
		
		if(TCPreferenceUtil.store_list == null||TCPreferenceUtil.store_list.size()<=0){
			MessageBox.post("请检查PREF_CUST_CUSTOMCOPY首选项配置！","操作提示",MessageBox.INFORMATION);
			return null;
		}
		if(targets==null||targets.length<=0){
			MessageBox.post("至少选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
			return null;
		}
		TCComponentBOMLine bomline = null;
		if(targets!=null&&targets.length==1){
			  if(targets[0] instanceof TCComponentBOMLine){
				  bomline= (TCComponentBOMLine) targets[0];
				
			      AIFClipboard clipboard = AIFPortal.getClipboard();
			      Transferable localTransferable = clipboard.getContents(this);
			      try {
			    	  List pastes_list = (List)localTransferable.getTransferData(new DataFlavor(Vector.class, "AIF Vector"));
				      for (Object object : pastes_list) {
				     	  if(object instanceof TCComponentBOMLine){
			     		     TCComponentBOMLine paste_bomline = (TCComponentBOMLine) object;
				     		 System.out.println(">>>>>> "+paste_bomline);
				     		 TCComponentBOMLine newbomline = bomline.add(paste_bomline, "");
				     		 //设置属性
				     		for (String string : TCPreferenceUtil.store_list) {
				     			newbomline.setProperty(string, paste_bomline.getProperty(string));
				    		}
				     		 //newbomline.setFormula(paste_bomline.getFormula());
				     	  }
				     	  
					  }
				  } catch (Exception e) {
					e.printStackTrace();
				  }
			  }
		}else{
			MessageBox.post("有且只能选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
			return null;
		}
		return null;
	}

}
