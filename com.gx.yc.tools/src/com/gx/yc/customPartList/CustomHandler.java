package com.gx.yc.customPartList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

import org.eclipse.swt.widgets.Shell;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.ServiceData;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentManager;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.schemas.soa._2006_03.exceptions.ServiceException;
import com.teamcenter.services.rac.core.DataManagementService;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateIn;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateInput;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateOut;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateResponse;
import com.teamcenter.services.rac.core._2016_09.DataManagement;



public class CustomHandler extends AbstractHandler {
	TCSession session = null;
//	public int progress = 0;
//	ArrayList<String> svr_List = new ArrayList<String>();
//	ArrayList<TCComponentBOMLine> custom_Item  = new ArrayList<TCComponentBOMLine>();
//	
//	TCComponentItemRevision tccomponentrev = null;
	
	//temp 
    
	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
	    session = (TCSession) AIFUtility.getCurrentApplication().getSession();
	    
		TCComponentManager manager = session.getComponentManager();
		try {
			TCComponent svr=manager.getTCComponent("QOQxxzNaLwqyPB");
			System.out.println("svr11"+svr);
			
			DataManagementService dm = DataManagementService.getService(session);
			
			
			CreateInput input = new CreateInput();
			input.boName = "YJATest01";
			
			CreateIn cbean = new CreateIn();
			cbean.clientId = "AppX-Test";
			cbean.data = input;
			try {
				CreateResponse reponse = dm.createObjects(new DataManagementService.CreateIn[]{cbean});
				ServiceData wuyu = reponse.serviceData;
				CreateOut[] out = reponse.output;
				for (CreateOut createOut : out) {
					TCComponent[] tcs = createOut.objects;
					for (TCComponent tcComponent : tcs) {
						System.out.println("tcComponent :"+tcComponent);
					}
					
				}
				
				System.out.println(wuyu.toString());
			} catch (ServiceException e) {
				e.printStackTrace();
			}

			
			
			//DataManagement.CreateIn[] www  = new DataManagement.CreateIn[]{null};
		} catch (TCException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
//	public Object wwexecute111(ExecutionEvent event) throws ExecutionException {
//		
//		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
//		InterfaceAIFComponent[] targets=app.getTargetComponents();
//	    session = (TCSession) AIFUtility.getCurrentApplication().getSession();
//	    
//	    localVariantManagementService = VariantManagementService.getService(session);
//	    structureService = StructureManagementService.getService(session);
//		if(targets==null||targets.length<=0){
//			MessageBox.post("至少选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
//			return null;
//		}
//		if(targets==null||targets.length>1){
//			MessageBox.post("只能选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
//			return null;
//		}
//		if(targets!=null&&targets.length==1){
//			
//			if(targets[0] instanceof TCComponentItemRevision){
//				tccomponentrev = (TCComponentItemRevision) targets[0];
//			}else{
//				MessageBox.post("只能选择一个组件版本进行操作！","操作提示",MessageBox.INFORMATION);
//				return null;
//			}
//			
//			String revType = tccomponentrev.getType();
//			//if(revType!=null&&revType.equalsIgnoreCase("Y7OrderItemRevision")){
//			if(revType!=null&&revType.equalsIgnoreCase("ItemRevision")){
//				TCComponentBOMLine configProduct = null;
//				TCComponentItemRevision configProductRev = null;
//				TCComponentSavedVariantRule svr = null;
//				try {
//					TCComponent[] configBOMs = tccomponentrev.getRelatedComponents("IMAN_specification");
//					System.out.println(">>>>>> "+configBOMs.length);
//					//配置BOM
////					if(configBOMs!=null&&configBOMs.length==1){
////						if(configBOMs[0] instanceof TCComponentItemRevision){
////							configProductRev = (TCComponentItemRevision) configBOMs[0];
////						}
////					}
//					for (TCComponent tcComponent : configBOMs) {
//						if(tcComponent instanceof TCComponentItemRevision){
//							configProductRev = (TCComponentItemRevision)tcComponent;
//						}
//					}
//					if(configProductRev==null){
//						MessageBox.post("配置BOM数据不符合规范！","操作异常",MessageBox.ERROR);
//						return null;
//					}
//					//SVR IMAN_specification
//					TCComponent[] SVRs = tccomponentrev.getRelatedComponents("IMAN_specification");
//					System.out.println(">>>>>> "+SVRs.length);
////					if(SVRs!=null&&SVRs.length==1){
////						if(configBOMs[0] instanceof TCComponentSavedVariantRule){
////							svr = (TCComponentSavedVariantRule) SVRs[0];
////						}
////					}
//					for (TCComponent tcComponent : SVRs) {
//						String wuyu = tcComponent.getType();
//						if(tcComponent instanceof TCComponentSavedVariantRule){
//							svr = (TCComponentSavedVariantRule)tcComponent;
//						}
//					}
//					if(svr==null){
//						MessageBox.post("SVR数据不符合规范！","操作异常",MessageBox.ERROR);
//						return null;
//					}
//					
//					String svrText = svr.getProperty("cfg0VariantRuleText");
//					svr_List.clear();
//					parsingSVR(svrText);
//					
//					TCComponent[] tests =  configProductRev.getRelatedComponents();
//					
//					TCComponentSavedVariantRule test_svr = null;
//					for (TCComponent tcComponent : tests) {
//						if(tcComponent instanceof TCComponentSavedVariantRule){
//							test_svr = (TCComponentSavedVariantRule)tcComponent;
//							System.out.println("test_svr :"+test_svr);
//						}
//					}
//					
//					
//					//清算BOM
//					TCComponentBOMWindowType  tcctype = (TCComponentBOMWindowType) session.getTypeComponent("BOMWindow");
//					TCComponentBOMWindow bomWindow = tcctype.create(null);
//					configProduct = bomWindow.setWindowTopLine(configProductRev.getItem(),configProductRev,null,null);
//					
//					TCComponent component = (TCComponent) AIFUtility.getCurrentApplication().getTargetComponent();
//					TCSession tcSession = (TCSession)AIFUtility.getCurrentApplication().getSession();
//					//TCComponentManager manager = tcSession.getComponentManager();
//					//TCComponent svrs=manager.getTCComponent("ACfxRH4CLwqyPB");
//					List<TCComponentVariantRule > list =bomWindow.askVariantRules();
//					
//					System.out.println("----------wuyu1111331221");
//					TCComponentItemType tempType = (TCComponentItemType) session.getTypeComponent("Cfg0ProductItem");
//					TCComponent[] tempcomps = tempType.findItems("000062");
//					System.out.println("tempcomps = "+tempcomps);
//					System.out.println("tempcomps[0] = "+tempcomps[0]);
//					if(tempcomps[0] instanceof TCComponentConfigurationContext){
//						System.out.println("wyuyu2");
//						//bomWindow.setConfigurationContext((TCComponentConfigurationContext) tempcomps[0]);
//					}
//					
////				    VariantManagementService localVariantManagementService = VariantManagementService.getService(tcSession);
////				    VariantManagement.VariantConfigurationCriteria localVariantConfigurationCriteria = new VariantManagement.VariantConfigurationCriteria();
////				    localVariantConfigurationCriteria.savedVariantRules = new TCComponent[1];
////				    localVariantConfigurationCriteria.savedVariantRules[0] = svr;
////				    ServiceData localServiceData = localVariantManagementService.applyVariantConfiguration(bomWindow, localVariantConfigurationCriteria);
////				      
////				    bomWindow.showUnconfiguredOccs(false);
////				    bomWindow.hideVariants();;
////				 
////				    configProduct.refresh();
////					
////					custom_Item.clear();
////					ProcessBOM(configProduct);
//					
//					MessageBox.post("客户化零件清单成功！","操作提示",MessageBox.INFORMATION);
//					
//				} catch (Exception e) {
//					MessageBox.post("客户化零件清单失败！","操作异常",MessageBox.ERROR);
//					e.printStackTrace();
//				}
//
//			}else{
//				MessageBox.post("请选择供货代号版本进行操作！","操作提示",MessageBox.INFORMATION);
//				return null;
//			}
//			
//		}
//		
//		return null;
//	}
	
	
	//处理产品EBOM
/*	public void ProcessBOM(final TCComponentBOMLine product){
		
		Shell shell = AIFDesktop.getActiveDesktop().getShell();
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					
					try {
						liquidationBOM(product, monitor);
						
						for (TCComponentBOMLine custom : custom_Item) {
							String itemname = custom.getItem().getProperty("object_name");
							System.out.println(">>>>:"+custom);
							showProgreessMessage(monitor,"正在客户化零件..." + custom);
							createCustomItem(tccomponentrev, itemname);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					monitor.done();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		try {
			
			new ProgressMonitorDialog(shell).run(true, true, runnable);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parsingSVR(String text){
		if(text==null) return;
		String handler_s = text.replace("(", "").replace(")", "").replace(" ", "");
		String[] splits = handler_s.split("AND");
		for (String split : splits) {
			svr_List.add(split);
		}
	}
	
	public String formatParam(String text){
		if(text == null) return "";
		return text.replace("(", "").replace(")", "").replace(" ", "");
	}

	
	public void liquidationBOM(TCComponentBOMLine product,IProgressMonitor monitor){
		try {
			showProgreessMessage(monitor,"正在分析..." + product);
			if(product==null) return;
			AIFComponentContext[] children = product.getChildren();
			for (AIFComponentContext child : children) {
				TCComponentBOMLine childBomline = (TCComponentBOMLine) child.getComponent();
				liquidationBOM(childBomline,monitor);
			}
			
			System.out.println(">>>>>> "+product);
			String compType = product.getItemRevision().getType();
			
			if(!compType.equalsIgnoreCase("ItemRevision")) return;
			
			TCComponent compForm = product.getItemRevision().getRelatedComponent("IMAN_master_form_rev");
			
			if(compForm==null){
				System.err.println("该"+product+"组件表单异常！请联系管理员！");
				return;
			}
			
			String isAbstract = compForm.getProperty("y7isAbstractItem");
			
			if(isAbstract!=null&&isAbstract.equalsIgnoreCase("true")){
				custom_Item.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//创建客户化组件
	public void createCustomItem(TCComponentItemRevision rev, String itemname){
		try {
			TCComponentItemType itemType = (TCComponentItemType) session.getTypeComponent("Item");
			TCComponentItem newItem =itemType.create("", "", "Item", itemname, "", null);
			rev.add("IMAN_Simulation", newItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void showProgreessMessage(IProgressMonitor monitor,String messge) {
	  	
		if(progress==500){
			progress=0;
		    monitor.beginTask("处理中", 500);
		}
		progress++;
		monitor.worked(1);
		monitor.subTask(messge);
	}*/
	
	
/*	protected void setBOMVariantRule(TCComponentBOMLine topBOPLine , TCComponentSavedVariantRule savedVarRule) throws Exception{
		
		TCComponentBOMWindow bomWindow = (TCComponentBOMWindow) topBOPLine.window();
		bomWindow.setVariantConfigurationMode(TCComponentBOMWindow.VariantConfigurationMode.SingleRuleMultiValue);
		List<BOMVariantRuleModel> vrules = BOMVariantSOAHelper.getVariantRules(bomWindow);
		BOMVariantSOAHelper.setVariantRuleOptionValues(bomWindow , vrules.get(0));
		BOMVariantSOAHelper.applyVariantRule(bomWindow , vrules.get(0));
		Map<String, String> savedVarRule_Props = savedVarRule.getProperties();
		
		List<TCComponentVariantRule> windowVarRules = bomWindow.askVariantRules();
		
		if(windowVarRules.size()<0){
			return ;
		}
		
		TCComponentVariantRule rule = windowVarRules.get(0);
		OptionAndRev[] rev = rule.askOptions();
		System.out.println("rev.length  = "+rev.length);
		int k = 0;
		for (OptionAndRev optionAndRev : rev) {
			k++;
			System.out.println(k+" ; "+optionAndRev);
		}
		String name = "";
		String[] contents_rules = savedVarRule_Props.get("cfg0VariantRuleText").replace(" ", "").replace("[Teamcenter]", "").split("AND");
		for (int i = 0; i < rev.length; i++) {
			TCComponentVariantOption option = rev[i].option;
			TCComponentVariantOptionType.OptionData optionData = option.askOptionData();
			name = optionData.name;
			for (int j = 0; j < contents_rules.length; j++) {
				if (contents_rules[j].contains(name)) {
					String optionValue = contents_rules[j].split("=")[1].trim();
					System.out.println("optionValue :"+optionValue);
					// rule.setOptionValue(name, optionValue);
					SetVariantVaule(rule, name, optionValue);
//					DebugPrint.outPrintln("name 1= "+ name);
//					DebugPrint.outPrintln("optionValue 1= "+ optionValue);
					break;
				}
			}
		}
		bomWindow.hideVariants();
		bomWindow.refresh();
		topBOPLine.refresh();
	}*/
	
	
/*	protected void SetVariantVaule(TCComponentVariantRule variantRule,
			String optionName, String optionValue) {
		try {
			OptionAndRev[] optionAndRevs = variantRule.askOptions();
			for (int i = 0; i < optionAndRevs.length; i++) {
				TCComponentVariantOption variantOption = optionAndRevs[i].option;
				String tempName = variantOption.askName();
				if (tempName.equals(optionName)) {
					variantRule.setOptionValue(variantOption, optionValue);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.post(e);
		}
	}*/
	
	
	//-----------------------
    



}
