package com.gx.yc.test;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.actions.ActionGroup;

//�̳�ActionGroup
public class MyActionGroup extends ActionGroup{
  private TableViewer tableViewer;
  
  /**
   * ����Ҽ��в˵�,����Ҫ
   * ���ɲ˵�Menu,��������Action����
   */
  public void fillContextMenu(IMenuManager mgr){//I��ͷ��һ���ǽӿڵ���˼.
      //��������Action���󵽲˵���������
      MenuManager menuManager = (MenuManager) mgr; //��Ϊ�������һ���ӿ�,��������ط�Ҫת��һ������.
      menuManager.add(new OpenAction());
      menuManager.add(new RefreshAction());

      /*
       * ��һ�δ�����ʲô��˼..........????????�е�㲻��.
       */
      //����Menu�����ر��table��,menu��table��������Ϊ�Է��Ĳ���.
      Table table = tableViewer.getTable();
      Menu menu = menuManager.createContextMenu(table);
      table.setMenu(menu);
      
  }
  
  /**
   * ��������TableViewer����Ĺ��캯����
   * ��Ϊ��Action��Ҫʹ�õ�TableViewer���� ����һ��Ҫ��TableViewer��������
   */
  public MyActionGroup(TableViewer tableViewer) {
      this.tableViewer = tableViewer;
  }
  
  /**
   * "��"��Action��
   */
  private class OpenAction extends Action{
      public OpenAction(){
          setText("���");
      }
      /**
       * �̳���Action�ķ���,��������д�ڴ˷�����.
       */
      public void run(){
          IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
          EPartBean obj = (EPartBean) (selection.getFirstElement());
          if (obj == null) {
              MessageDialog.openInformation(null, null, "ѡѡ���¼");
          } else {
              MessageDialog.openInformation(null, null, obj.getColumn_1());
          }            
      }
  }
  
  /**
   * ���Ƶ�Action��
   */
  private final class RefreshAction extends Action{
      public RefreshAction(){
          setText("����");
          
          
      }
      
      public void run(){
          //tableViewer.refresh();//���ñ���ˢ�·���.
    	  
    	  Table table = tableViewer.getTable();
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
}