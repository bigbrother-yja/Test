package com.gx.yc.test;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;

public class EPartFactory {

	public static List<EPartBean> getResults(ArrayList<InfluenceBean> bean_list) {
		List<EPartBean> list = new ArrayList<EPartBean>();
		list.clear();
		try {
			for(int i=0;i<bean_list.size();i++){
				
				InfluenceBean influenceBean = bean_list.get(i);
				TCComponentBOMLine bomline = influenceBean.getBomline();
				ArrayList<TCComponent> array_comps = influenceBean.getSupplierCode_list();

	            for (TCComponent component : array_comps) {
		            EPartBean bean = new EPartBean();
		            bean.setId(new Long(i+1));
		            
		            bean.setColumn_1(bomline.getItem().getProperty("object_name"));
	            	
		            String item_id = component.getProperty("item_id");
		            String revision = component.getProperty("revision_id");
		            bean.setColumn_2(item_id+"/"+revision);
		            
		            if(component instanceof TCComponentItemRevision){
		            	TCComponentItemRevision rev = (TCComponentItemRevision) component;
			            String svr = "";
			            TCComponent[] svr_components = rev.getRelatedComponents("svr");
			            for (TCComponent tcComponent : svr_components) {
							if(tcComponent instanceof TCComponentItem){
								TCComponentItem item = (TCComponentItem) tcComponent;
								//�жϴ���
								svr = svr + item.getProperty("object_name")+"��";
							}
						}
			            if(svr==null||"".equals(svr)||svr.equalsIgnoreCase("null")){
			            	bean.setColumn_3("");
			            }else{
			            	bean.setColumn_3(svr.substring(0,svr.length()-1));
			            }
			            
			            list.add(bean);
		            }
				}
	            

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
    public static List<EPartBean> getPeoples() {
        //����һ��list��������People��Ķ���
        List<EPartBean> list = new ArrayList<EPartBean>();
            // ��1��ʵ�������
            EPartBean ePartBean1 = new EPartBean();
            ePartBean1.setId(new Long(1));
            ePartBean1.setColumn_1("֧��");
            ePartBean1.setColumn_2("00000-107100");
            ePartBean1.setColumn_3("1071");
            
            list.add(ePartBean1);
            
            // ��2��ʵ�������
            EPartBean ePartBean2 = new EPartBean();
            ePartBean2.setId(new Long(2));
            ePartBean2.setColumn_1("����");
            ePartBean2.setColumn_2("00000-130800");
            ePartBean2.setColumn_3("00000-130800");
            list.add(ePartBean2);
            
            // ��3��ʵ�������
            EPartBean ePartBean3 = new EPartBean();
            ePartBean3.setId(new Long(3));
            ePartBean3.setColumn_1("�ҷ���");
            ePartBean3.setColumn_2("00000-132222");
            ePartBean3.setColumn_3("00000-130800");
            list.add(ePartBean3);
            
            // ��3��ʵ�������
            EPartBean ePartBean4 = new EPartBean();
            ePartBean4.setId(new Long(4));
            ePartBean4.setColumn_1("�ҷ���");
            ePartBean4.setColumn_2("00000-13111");
            ePartBean4.setColumn_3("00000-130800");
            list.add(ePartBean4);
            
            // ��3��ʵ�������
            EPartBean ePartBean5 = new EPartBean();
            ePartBean5.setId(new Long(5));
            ePartBean5.setColumn_1("�ҷ���");
            ePartBean5.setColumn_2("00000-130800");
            ePartBean5.setColumn_3("00000-130800");
            list.add(ePartBean5);
            
            return list;
    }
}