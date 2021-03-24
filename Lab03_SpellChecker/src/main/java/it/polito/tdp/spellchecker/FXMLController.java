/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
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
    	
    	TxtParoleSbagliate.clear();
    	
    	
    	double start=(System.nanoTime())/1000000;
    	
    	if(MenuBtn.getText().equals("Language")) {
    		TxtParoleSbagliate.setText("Scegliere una lingua!");
    		return;
    	}
    	
    	String[] input=TxtFrase.getText().toLowerCase().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_()\\[\\]\"]", "").split(" ");
    	
    	List<String> in=new LinkedList<String>();
    	int count=0;
    	
    	for(int i=0;i<input.length;i++) {
    		in.add(input[i]);
    	}
    	
    	List<RichWord> lista=model.spellCheckText(in);
    	for(RichWord r:lista) {
    		if(r.isCorretta()==false) {
    			TxtParoleSbagliate.appendText(r.getParola()+"\n");
    			count++;
    		}
    	}
    	LblErrori.setText("The text contains "+count+" errors");
    	
    	double stop=(System.nanoTime())/1000000;
    	
    	LblTempo.setText("Spell check completed in "+(stop-start)+" seconds");
    }

    @FXML
    void doClear(ActionEvent event) {
    	TxtFrase.clear();
    	TxtParoleSbagliate.clear();
    	MenuBtn.setText("Language");
    }

    @FXML
    void doEnglish(ActionEvent event) {
    	MenuBtn.setText("English");
    	model.unloadDictionary();
    	model.loadDictionary("English");
    	TxtParoleSbagliate.clear();
    }

    @FXML
    void doItalian(ActionEvent event) {
    	MenuBtn.setText("Italiano");
    	model.unloadDictionary();
    	model.loadDictionary("Italian");
    	TxtParoleSbagliate.clear();
    }	
    
    public void setModel(Dictionary model) {
    	this.model=model;
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



