package com.gx.yc.analysisBOMInfluence;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class AnalysisHandler  extends AbstractHandler{
	
	TCSession session = null;
	ArrayList<TCComponentBOMLine>  target_list = new ArrayList<TCComponentBOMLine>();
	ArrayList<InfluenceBean> bean_list = new ArrayList<InfluenceBean>();

	@Override
	public Object execute(ExecutionEvent executionevent)
			throws ExecutionException {
		
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
		
		if(targets==null||targets.length<=0){
			MessageBox.post("至少选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
		}
		
		if(targets!=null&&targets.length>=1){
			target_list.clear();
			for (InterfaceAIFComponent target : targets) {
				if(target instanceof TCComponentBOMLine){
					target_list.add((TCComponentBOMLine) target);
				}
			}
			
			if(target_list.size()>=1){
				AnalisysInluence();
				//init
				AnalisysDialog adialog = new AnalisysDialog(bean_list);
				adialog.Init();
				
			}else{
				MessageBox.post("请在结构管理器中选择需要分析的组件","操作提示",MessageBox.INFORMATION);
			}
		}
		
		return null;
	}
	
	
	private void AnalisysInluence(){
		try {
			for (TCComponentBOMLine bomline : target_list) {
				
				TCComponentBOMLine parent_bomline = bomline.parent();
				//获取供货代号 --这边以版本为准
				ArrayList<TCComponent> array_comps = new ArrayList<TCComponent>();
				array_comps.clear();
				
				String[] relations = {"IMAN_specification"};
				AIFComponentContext[] aifs = parent_bomline.getItemRevision().whereReferencedByTypeRelation(null, relations);
				for (AIFComponentContext aif : aifs) {
					array_comps.add((TCComponent) aif.getComponent());
				}
				
				InfluenceBean bean = new InfluenceBean();
				bean.setBomline(bomline);
				bean.setSupplierCode_list(array_comps);
				
				bean_list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
