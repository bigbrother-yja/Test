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
    //���ȶ���һ�����(TableViewer��)����
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
        //1.display�������һʵ��ѭ���Ϳ���UI�̺߳������߳�֮���ͨ��
        //Display display = new Display();
        Shell shell = new Shell();
        //shell.setSize(600, 400);
        //����shell�Ĳ���ΪFillLayout
        shell.setLayout(new FillLayout());
        shell.setText("��������Ӱ�����");
        //�����Զ���ķ����������
        createTableViewer(shell);
        //4.�趨������
        tableViewer.setContentProvider(new TableViewerContentProvider());
        //5.�趨��ǩ��
        tableViewer.setLabelProvider(new TableViewerLabelProvider());
        //6.��setInput��������(��PeopleFactory������List���ϴ�����)
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
		
		//���ô��ھ���
		int screenWidth = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		int frameWidth  =  950;
		int frameHeight =  700;
		shell.setLocation((screenWidth -frameWidth)/2 , (screenHeight - frameHeight)/2);
		shell.open();
    }
    
    
    /**
     * ����һ�����
     */
    public void createTableViewer(Composite prarent){
        tableViewer = new TableViewer(prarent, SWT.MULTI| SWT.H_SCROLL 
                | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL );
        
        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);//���ñ�ͷ
        table.setLinesVisible(true);//��ʾ�����
        TableLayout tLayout = new TableLayout();//ר���ڱ��Ĳ���
        table.setLayout(tLayout);
        
        tLayout.addColumnData(new ColumnWeightData(20));//���������ID�е��п�Ϊ10����
        new TableColumn(table, SWT.NONE).setText("���");

        tLayout.addColumnData(new ColumnWeightData(50));//���������ID�е��п�Ϊ40����
        new TableColumn(table, SWT.NONE).setText("�������");
        
        tLayout.addColumnData(new ColumnWeightData(70));//���������ID�е��п�Ϊ70����
        new TableColumn(table, SWT.NONE).setText("Ӱ������SVR");
    
        tLayout.addColumnData(new ColumnWeightData(70));//���������ID�е��п�Ϊ10����
        new TableColumn(table, SWT.NONE).setText("Ӱ�칩������");
        
    }
    
    /**
     * �����ӵļ�����
     */
    public void addListener(){
        //TableViewer��˫���¼��ļ���
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {//IDoubleClickListener��һ���ӿ�
            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                EPartBean ePartBean = (EPartBean) selection.getFirstElement();
                //�õ���¼��ʵ�����(Ҫ����ת��)
                //һ��������ʾ��
                MessageDialog.openInformation(null, "��ʾ",ePartBean.getColumn_1());
            }
        });

        /*
         * TableViewer��ѡ���¼�������������
         */
        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
            }
        });   
        
    }
}