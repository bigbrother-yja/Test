package com.gx.yc.analysisBOMInfluence;

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
    	Color color = Display.getDefault().getSystemColor(SWT.COLOR_RED);
        TableItem[] items = table.getItems();
        for (TableItem tableItem : items) {
        	if(tableItem.getText(4).startsWith("нч")&&tableItem.getText(4).endsWith("нч")){
        		tableItem.setBackground(color);
        	}
		}
        table.redraw();
        return true;
    }
}