package com.example.opt_os;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OPTController implements Initializable {

    @FXML
    private TextField daytrang;
    @FXML
    private TextField khungtrang;
    @FXML
    private TextArea textarea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    daytrang.setText(null);
    khungtrang.setText(null);
    }
    @FXML
    public void onOKButtonClick() {
        if (khungtrang.getText()==null || daytrang.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi  Dữ Liệu   ");
            alert.setHeaderText("không được bỏ trống bất cứ trường nào " );
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }
            return;
        }
        if(Pattern.matches("\\D",khungtrang.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi  Dữ Liệu   ");
            alert.setHeaderText("số khung trang phải là một số nguyên  " );
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }
            return;
        }
        int frames = Integer.parseInt(khungtrang.getText());
        if(Pattern.matches(".*[a-zA-Z].*",daytrang.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Lỗi  Dữ Liệu   ");
            alert.setHeaderText("dãy trang không bao gồm chữ cái" );
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && (result.get() == ButtonType.OK)) {
                return;
            }
            return;
        }
        String[] day = daytrang.getText().split("\\D");
        int reference[] = new int[day.length];
        for (int i=0; i<day.length; i++){
            reference[i]= Integer.parseInt(day[i]);
        }
        int ref_len= day.length;
        OPT opt = new OPT();
        String result =opt.run(frames,ref_len,reference);
        textarea.setText(result);

    }
    @FXML
    public void onCancelButtonClick() {
        textarea.setText("");
        daytrang.setText(null);
        khungtrang.setText(null);
        return;
    }
}