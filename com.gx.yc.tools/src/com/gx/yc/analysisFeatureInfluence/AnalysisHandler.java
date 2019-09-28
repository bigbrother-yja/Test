package com.gx.yc.analysisFeatureInfluence;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentCAE0ConfigRevision;
import com.teamcenter.rac.kernel.TCComponentCfgAbsOccAttachmentLine;
import com.teamcenter.rac.kernel.TCComponentCfgActivityLine;
import com.teamcenter.rac.kernel.TCComponentCfgAttachmentLine;
import com.teamcenter.rac.kernel.TCComponentCfgBlAttachmentLine;
import com.teamcenter.rac.kernel.TCComponentConfigurationFamily;
import com.teamcenter.rac.kernel.TCComponentOccType;
import com.teamcenter.rac.kernel.TCComponentVariantRule;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class AnalysisHandler  extends AbstractHandler{
	
	TCSession session = null;
	ArrayList<TCComponentBOMLine>  target_list = new ArrayList<TCComponentBOMLine>();
	@Override
	public Object execute(ExecutionEvent executionevent)
			throws ExecutionException {
		
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
		
		if(targets==null||targets.length<=0){
			MessageBox.post("至少选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
		}
		
		if(targets!=null&&targets.length>=1){
			
			for (InterfaceAIFComponent target : targets) {

//				System.out.println(">>>>>> TCComponentOccType");
//				System.out.println(">>>>>> TCComponentCfgAttachmentLine");
//				System.out.println(">>>>>> TCComponentConfigurationFamily");
//				System.out.println(">>>>>> TCComponentCfgActivityLine");
//				System.out.println(">>>>>> TCComponentCfgAbsOccAttachmentLine");
//				System.out.println(">>>>>> TCComponentCfgBlAttachmentLine");
				
				if(target instanceof TCComponentBOMLine){
					System.out.println(">>>>>> TCComponentBOMLine");
				}
				TCComponent comp = (TCComponent) target;
				

				
				//cfg0Family  cfg0OptionFamily
				try {
					String value = "";
					TCComponent family = comp.getRelatedComponent("cfg0Family");
					String familyName = family.getProperty("object_name");
					
					value = familyName+" = ";
					String featureName = comp.getProperty("object_name");
					
					value = value + featureName;
					System.out.println(">>>>>> value:"+value);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//Cfg0LiteralOptionValue
			}
		}
		
		return null;
	}

}
