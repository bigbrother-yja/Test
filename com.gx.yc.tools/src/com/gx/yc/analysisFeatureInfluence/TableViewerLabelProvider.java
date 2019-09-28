package com.gx.yc.analysisFeatureInfluence;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * ���������Ҫ����Ϊ"��ǩ��"���������õ�.
 * "��ǩ��"��һ����ʵ�������ֶα����ֱ�ȡ��Ȼ��ϵ������TableViewer�ĸ�������.
 * ʵ�ֶ�Ӧ�Ľӿ�
 * @author yja
 */
public class TableViewerLabelProvider implements ITableLabelProvider{
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;//�����getColumnText()��ͬ����,����������Է���һ��nullֵ.
    }

    /**
     * �ɴ˷����������ݼ�¼�ڱ���ÿһ������ʾʲô����
     * @param element ʵ�������
     * @param columnIndex �кţ�0�ǵ�һ��
     * @return ����ֵһ��Ҫ����NULLֵ,�������
     */
    @Override
    public String getColumnText(Object element, int columnIndex) {
        EPartBean ePartBean = (EPartBean) element;
        if(columnIndex == 0){
            return ePartBean.getId().toString();
        }
        
        if(columnIndex == 1){
            return ePartBean.getColumn_1();
        }
        
        if(columnIndex == 2){
            return ePartBean.getColumn_2();
        }
        
        if(columnIndex == 3){
            return ePartBean.getColumn_3();
        }
        
        if(columnIndex == 4){
            return ePartBean.getColumn_4();
        }
        if(columnIndex == 5){
            return ePartBean.getColumn_5();
        }
        //���������ϵ�ʱ�򷵻�һ�����ַ���
        return "";
    }
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>����˵�����⼸�������ô�����,��ʵ�־Ϳ�����>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void addListener(ILabelProviderListener listener) {
        
    }
    
    @Override
    public void dispose() {
        
    }
    
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }
    
    @Override
    public void removeListener(ILabelProviderListener listener) {
        
    }
}