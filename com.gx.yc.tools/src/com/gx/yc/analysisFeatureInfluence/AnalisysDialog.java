package com.gx.yc.analysisFeatureInfluence;


import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.util.Registry;

public class AnalisysDialog {
    //首先定义一个表格(TableViewer类)对象
    private TableViewer tableViewer;
    
    ArrayList<InfluenceBean> bean_list = null;
    
/*    public static void main(String[] args) {
    	target_list = new  ArrayList<TCComponentBOMLine>();
        AnalisysDialog window = new AnalisysDialog(target_list);
        window.Init();
    }*/
    
    public AnalisysDialog(ArrayList<InfluenceBean> bean_list){
    	this.bean_list = bean_list;
    }
    
    public void Init(){
        //1.display负责管理一实现循环和控制UI线程和其他线程之间的通信
        //Display display = new Display();
        Shell shell = new Shell();
        //shell.setSize(600, 400);
        //设置shell的布局为FillLayout
        shell.setLayout(new FillLayout());
        shell.setText("特征更改影响分析");
        //调用自定义的方法创建表格
        createTableViewer(shell);
        //4.设定内容器
        tableViewer.setContentProvider(new TableViewerContentProvider());
        //5.设定标签器
        tableViewer.setLabelProvider(new TableViewerLabelProvider());
        //6.用setInput输入数据(把PeopleFactory产生的List集合传进来)
        tableViewer.setInput(EPartFactory.getPeoples());
        tableViewer.addFilter(new MyFilter());
        
        addListener();
        MyActionGroup actionGroup = new MyActionGroup(tableViewer);
        actionGroup.fillContextMenu(new MenuManager());        
        
        final Table table = tableViewer.getTable();
        table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent keyevent) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent keyevent) {
			 	if(keyevent.stateMask == SWT.CTRL && keyevent.keyCode == 'c') {
			    	  TableItem[] items = table.getSelection();
			    	  String ob = "";
			    	  for (TableItem tableItem : items) {
						EPartBean bean = (EPartBean)tableItem.getData();
						ob = ob+bean.getColumn_1()+";"+bean.getColumn_2()+";"+bean.getColumn_3()+"|";
			    	  }
			    	  Object[] data = {ob.substring(0, ob.length()-1)};
			    	  
			    	  Clipboard clipboard = new Clipboard(Display.getCurrent());
			    	  Transfer[] transfers = new Transfer[]{TextTransfer.getInstance()};
			    	  clipboard.setContents(data, transfers);
		        }
			}
		});
        
        shell.setImage( Registry.getRegistry(
                AbstractAIFDialog.class ).getImage(
                "aifDesktop.ICON" ) );
        shell.setSize(950, 700);
		
		//设置窗口居中
		int screenWidth = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		int frameWidth  =  950;
		int frameHeight =  700;
		shell.setLocation((screenWidth -frameWidth)/2 , (screenHeight - frameHeight)/2);
		shell.open();
    }
    
    
    /**
     * 创建一个表格
     */
    public void createTableViewer(Composite prarent){
        tableViewer = new TableViewer(prarent, SWT.MULTI| SWT.H_SCROLL 
                | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL );
        
        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);//设置标头
        table.setLinesVisible(true);//显示表格线
        TableLayout tLayout = new TableLayout();//专用于表格的布局
        table.setLayout(tLayout);
        
        tLayout.addColumnData(new ColumnWeightData(20));//这个是设置ID列的列宽为10像素
        new TableColumn(table, SWT.NONE).setText("序号");

        tLayout.addColumnData(new ColumnWeightData(50));//这个是设置ID列的列宽为40像素
        new TableColumn(table, SWT.NONE).setText("零组件号");
        
        tLayout.addColumnData(new ColumnWeightData(70));//这个是设置ID列的列宽为70像素
        new TableColumn(table, SWT.NONE).setText("影响配置SVR");
    
        tLayout.addColumnData(new ColumnWeightData(70));//这个是设置ID列的列宽为10像素
        new TableColumn(table, SWT.NONE).setText("影响供货代号");
        
    }
    
    /**
     * 新增加的监听器
     */
    public void addListener(){
        //TableViewer的双击事件的监听
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {//IDoubleClickListener是一个接口
            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                EPartBean ePartBean = (EPartBean) selection.getFirstElement();
                //得到记录的实体对象(要类型转换)
                //一个弹出提示框
                MessageDialog.openInformation(null, "提示",ePartBean.getColumn_1());
            }
        });

        /*
         * TableViewer的选择事件（单击）监听
         */
        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
            }
        });   
        
    }
}