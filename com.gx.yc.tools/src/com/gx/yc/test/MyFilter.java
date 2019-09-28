package com.gx.yc.test;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
 
public class MyFilter extends ViewerFilter {
    public boolean select(Viewer viewer, Object parentElement, Object element) {
    	
    	Table table  = ((TableViewer) viewer).getTable();
    	Color color1 = Display.getDefault().getSystemColor(SWT.COLOR_RED);
    	Color color2 = new Color(Display.getDefault(), 152 ,245, 255);
        TableItem[] items = table.getItems();
        for (TableItem tableItem : items) {
        	if(tableItem.getText(3).startsWith("1071")&&tableItem.getText(3).endsWith("1071")){
        		tableItem.setBackground(color2);
        	}else
        	
           	if(!tableItem.getText(2).equalsIgnoreCase(tableItem.getText(3))){
        		tableItem.setBackground(color1);
        	}
		}
        table.redraw();
        return true;
    }
}