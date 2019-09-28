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

//继承ActionGroup
public class MyActionGroup extends ActionGroup{
  private TableViewer tableViewer;
  
  /**
   * 鼠标右键有菜单,首先要
   * 生成菜单Menu,并将两个Action传入
   */
  public void fillContextMenu(IMenuManager mgr){//I开头的一般是接口的意思.
      //加入两个Action对象到菜单管理器中
      MenuManager menuManager = (MenuManager) mgr; //因为传入的是一个接口,所以这个地方要转换一下类名.
      menuManager.add(new OpenAction());
      menuManager.add(new RefreshAction());

      /*
       * 这一段代码是什么意思..........????????有点搞不懂.
       */
      //生成Menu并挂载表格table上,menu和table两个对象互为对方的参数.
      Table table = tableViewer.getTable();
      Menu menu = menuManager.createContextMenu(table);
      table.setMenu(menu);
      
  }
  
  /**
   * 用来接受TableViewer对象的构造函数。
   * 因为在Action会要使用到TableViewer对象 所以一定要把TableViewer传进来。
   */
  public MyActionGroup(TableViewer tableViewer) {
      this.tableViewer = tableViewer;
  }
  
  /**
   * "打开"的Action类
   */
  private class OpenAction extends Action{
      public OpenAction(){
          setText("另存");
      }
      /**
       * 继承自Action的方法,动作代码写在此方法中.
       */
      public void run(){
          IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
          EPartBean obj = (EPartBean) (selection.getFirstElement());
          if (obj == null) {
              MessageDialog.openInformation(null, null, "选选择记录");
          } else {
              MessageDialog.openInformation(null, null, obj.getColumn_1());
          }            
      }
  }
  
  /**
   * 复制的Action类
   */
  private final class RefreshAction extends Action{
      public RefreshAction(){
          setText("复制");
          
          
      }
      
      public void run(){
          //tableViewer.refresh();//调用表格的刷新方法.
    	  
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