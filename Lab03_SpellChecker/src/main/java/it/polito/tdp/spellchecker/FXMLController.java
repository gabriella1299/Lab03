/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton MenuBtn;

    @FXML
    private TextArea TxtFrase;

    @FXML
    private Button BtnCheck;

    @FXML
    private TextArea TxtParoleSbagliate;

    @FXML
    private Label LblErrori;

    @FXML
    private Button TxtClear;

    @FXML
    private Label LblTempo;

    @FXML
    void doCheck(ActionEvent event) {

    }

    @FXML
    void doClear(ActionEvent event) {

    }

    @FXML
    void doEnglish(ActionEvent event) {

    }

    @FXML
    void doItalian(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert MenuBtn != null : "fx:id=\"MenuBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtFrase != null : "fx:id=\"TxtFrase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert BtnCheck != null : "fx:id=\"BtnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtParoleSbagliate != null : "fx:id=\"TxtParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert LblErrori != null : "fx:id=\"LblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtClear != null : "fx:id=\"TxtClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert LblTempo != null : "fx:id=\"LblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}



