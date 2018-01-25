package com.hot.pwm.component;

import com.hot.pwm.bean.Password;
import com.hot.pwm.bean.PasswordSim;
import com.hot.pwm.component.TableCellFactory.IHandleFocus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.media.VideoTrack;
import net.sf.jsqlparser.schema.Table;

public class TableCellFactory<S,T> {
	public interface IHandleFocus{
		public void handleFocus();
		public void handleBlur();
	}
	public IHandleFocus iHandleFocus;
	 TextField textField;
	 public TableCellFactory(TextField textField) {

		 this.textField= textField;

	}
	public  TableCell getTableCell(){
			
		 return   new TableCell<S, T>(){
				@Override
				public  void updateItem(T item, boolean empty) {
					super.updateItem(item, empty);
					if(empty){
						setGraphic(null);
					}else{
						if(getItem()==null){
							textField.setText("");
						}else{
							textField.setText(getItem().toString());
						}
						setGraphic(textField);
					}
					
				}
			};
			
	}
	public void setFocusAction(IHandleFocus iHandleFocus2){
		 this.iHandleFocus= iHandleFocus2;
		 this.textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(oldValue&&!newValue){
						//输入框失去焦点
						//编辑完成
						iHandleFocus.handleBlur();
					}else{
						//输入框聚焦
						//编辑中
						iHandleFocus.handleFocus();

					}
					
				}
			});
		
	}

}
