package com.gx.yc.test;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.util.Vector;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import com.teamcenter.rac.aif.AIFClipboard;
import com.teamcenter.rac.aif.AIFPortal;
import com.teamcenter.rac.aif.AIFTransferable;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentSavedVariantRule;
import com.teamcenter.rac.kernel.TCComponentVariantRule;
import com.teamcenter.rac.kernel.TCComponentVariantRule.OptionAndRev;
import com.teamcenter.rac.kernel.TCComponentVariantRule.OptionValueData;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.variants.ExpressionEditModeEnum;
import com.teamcenter.rac.util.MessageBox;

public class TestHandler  extends AbstractHandler implements ClipboardOwner{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String str = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId();
		System.out.println(">>>"+str);
		
		AbstractAIFApplication app=AIFUtility.getCurrentApplication();
		InterfaceAIFComponent[] targets=app.getTargetComponents();
		
		if(targets==null||targets.length<=0){
			MessageBox.post("至少选择一个组件进行操作！","操作提示",MessageBox.INFORMATION);
		}
		
		TCComponentSavedVariantRule svr = (TCComponentSavedVariantRule) targets[0];
		
		//test
		try {
			String wuyu = svr.getProperty("cfg0VariantRuleText");
			System.out.println(">>> "+wuyu);
			TCComponent tc = svr.getRelatedComponent("cfg0VariantRuleText");
			System.out.println(tc);
			String text_test = svr.getTextRepresentation();
			System.out.println(">>> "+text_test);
			String des_test = svr.getDescription();
			System.out.println(">>> "+des_test);
			String name_test = svr.getName();
			System.out.println(">>> "+name_test);
			ExpressionEditModeEnum e_test = svr.getExpressionType();
			System.out.println(">>> = "+e_test.EditCondition);
			System.out.println(">>> = "+e_test.EditConstant);
			System.out.println(">>> = "+e_test.EditVariantRuleSet);
			System.out.println("");
			System.out.println("");
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

//		if(targets!=null&&targets.length>=1){
//			
//			AIFClipboard clipboard = AIFPortal.getClipboard();
//		    Vector localVector = new Vector(targets.length);
//		    for (InterfaceAIFComponent interfaceAIFComponent : targets) {
//		        if ((interfaceAIFComponent != null) && (!localVector.contains(interfaceAIFComponent)))
//		            localVector.addElement(interfaceAIFComponent);
//			}
//			AIFTransferable localAIFTransferable = new AIFTransferable(localVector);
//			clipboard.setContents(localAIFTransferable, this);
//		}
		
		
		return null;
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		
	}
	
	public static void main(String[] args) {
		String wuyu = "(AA00 = AA02 AND AB00 = AB02 AND AC00 = AC02 AND ZZ00 = ZZ01 AND ZZ00 = ZZ02 AND RD00 = 12 AND DD00 = DD01 AND HB00 = HB03 AND YZ00 = 20 AND YR00 = YR01 AND AE00 = AE02 AND AD00 = AD02 AND AF00 = AF02 AND EE00 = EE02 AND AG00 = AG02 AND Models = Cfg_T20180829_001 AND HS00 = 15)";
	    wuyu = wuyu.replace("(", "");
	    wuyu = wuyu.replace(" ","");
	    wuyu = wuyu.replace(")","");
		String[] splits = wuyu.split("AND");
		for (String string : splits) {
			System.out.println(string);
		}
	
	}

}
