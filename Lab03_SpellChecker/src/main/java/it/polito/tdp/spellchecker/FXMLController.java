/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
	
	// Flag to select dichotomic search
	private final static boolean dichotomicSearch = true;
	private final static boolean linearSearch = false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//    @FXML
//    private MenuButton MenuBtn;
    @FXML
    private ComboBox<String> boxLingua;

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
    void doActivation(ActionEvent event) {
    	if (boxLingua.getValue() !=null) {

    		TxtFrase.setDisable(false);
    		TxtParoleSbagliate.setDisable(false);
    		BtnCheck.setDisable(false);
    		TxtClear.setDisable(false);
    		TxtFrase.clear();
    		TxtParoleSbagliate.clear();

    	}else {

    		TxtFrase.setDisable(true);
    		TxtParoleSbagliate.setDisable(true);
    		BtnCheck.setDisable(true);
    		TxtClear.setDisable(true);
    		TxtFrase.setText("Seleziona una lingua!");

    	}

    }

    @FXML
    void doCheck(ActionEvent event) {
    	
    	TxtParoleSbagliate.clear();
    	List<String> in=new LinkedList<String>();
    	
    	if (boxLingua.getValue() == null) {
    		TxtFrase.setText("Seleziona una lingua!");
			return;
		}
    	if (!model.loadDictionary(boxLingua.getValue())) {
    		TxtFrase.setText("Errore nel caricamento del dizionario!");
			return;
		}

    	
    	//String[] input=TxtFrase.getText().toLowerCase().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_()\\[\\]\"]", "").split(" ");
    	String input=TxtFrase.getText();
    	if (input.isEmpty()) {
    		TxtFrase.setText("Inserire un testo da correggere!");
			return;
		}
    	input = input.replaceAll("\n", " ");
    	input = input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
		StringTokenizer st = new StringTokenizer(input, " ");
		while (st.hasMoreTokens()) {
			in.add(st.nextToken());
		}
    	
    	
    	long start=System.nanoTime();
    	
    	List<RichWord> lista;
    	if (dichotomicSearch) {
    		lista = model.spellCheckTextDichotomic(in);
		} else if (linearSearch) {
			lista = model.spellCheckTextLinear(in);
		} else {
			lista = model.spellCheckText(in);
		}
    	long stop=System.nanoTime();
    	
    	int errori=0;
    	StringBuilder richText = new StringBuilder();
    	
    	for(RichWord r:lista) {
    		if(r.isCorretta()==false) {
    			richText.append(r.getParola()+"\n");
    			errori++;
    		}
    	}
    	TxtParoleSbagliate.setText(richText.toString());
    	LblErrori.setText("The text contains "+errori+" errors");
    	LblTempo.setText("Spell check completed in "+(stop-start)/ 1E9 +" seconds");
    }

    @FXML
    void doClear(ActionEvent event) {
    	TxtFrase.clear();
    	TxtParoleSbagliate.clear();
    	LblErrori.setText("Number of Errors:");
    	LblTempo.setText("Spell Check Status:");
    }

  /*  @FXML
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
  */  
    public void setModel(Dictionary model) {
    	TxtFrase.setDisable(true);
    	TxtFrase.setText("Selezionare una lingua");

    	TxtParoleSbagliate.setDisable(true);
    	boxLingua.getItems().addAll("English","Italian");

    	BtnCheck.setDisable(true);
    	TxtClear.setDisable(true);

    	this.model=model;
    }
    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtFrase != null : "fx:id=\"TxtFrase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert BtnCheck != null : "fx:id=\"BtnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtParoleSbagliate != null : "fx:id=\"TxtParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert LblErrori != null : "fx:id=\"LblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtClear != null : "fx:id=\"TxtClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert LblTempo != null : "fx:id=\"LblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}



