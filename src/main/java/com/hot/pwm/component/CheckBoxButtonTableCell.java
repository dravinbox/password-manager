package com.hot.pwm.component;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;

public class CheckBoxButtonTableCell <S, T> extends TableCell<S, T>{
	private final CheckBox chebox;  
  
    public CheckBoxButtonTableCell() {  
        this.chebox = new CheckBox();  
        //添加元素  
        setGraphic(chebox);  
    }  
  
    @Override  
    protected void updateItem(T item, boolean empty) {  
//        System.out.println("empty："+empty);  
        super.updateItem(item, empty);  
        if (empty) {  
            //如果此列为空默认不添加元素  
            setText(null);  
            setGraphic(null);  
        } else {  
            //初始化为不选中  
            chebox.setSelected(false);  
            setGraphic(chebox);  
             
        }  
    }  

}
