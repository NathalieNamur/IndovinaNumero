package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	/**ATTRIBUTO RIFERIMENTO MODEL:**/
	private Model model; 
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox HBoxTentativo;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private TextField txtTentativiRimasti;

    @FXML
    private TextField txtTentativo;

    
    
    @FXML
    void doNuovaPartita(ActionEvent event) {
    	
    	/**GESTIONE INTERFACCIA:**/
    	
    	HBoxTentativo.setDisable(false);
    	txtTentativiRimasti.setText(Integer.toString(model.getTMAX()));
    	txtTentativo.clear();
    	txtRisultato.clear();
    	
    	
    	/**GESTIONE NUOVA PARTITA (MODEL):**/
    	
    	model.nuovaPartita();

    }

    
    @FXML
    void doProva(ActionEvent event) {
    
    	/**1.ACQUISIZIONE E CONTROLLO DATI:**/
    	
    	String t = txtTentativo.getText();
    	int tentativo = Integer.parseInt(t); //*
    	
    	txtTentativiRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiEffettuati())); //*
    	
    	//*
    	//Integer.parseInt(..String..) : trasforma una stringa in un intero.
    	//Integer.toString(..int..) : trasforma un intero in una stringa.
    	
    	
    	/*
    	VOLENDO, E' POSSIBILE AGGIUNGERE UN CONTROLLO PER VERIFICARE CHE 
    	IL GIOCATORE ABBIA EFFETTIVAMENTE INSERITO UN NUMERO.
    	
    	DI TALE CONTROLLO SI DEVE OCCUPARE IL Controller PERCHE' COSTITUISCE
    	UN CONTROLLO DATI DELL'INTERFACCIA GRAFICA.
    	 
    	try {
    	tentativo = Integer.parseInt(t);
    	}
    	catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100");
    		return; 
    	}
    
    	**/
    	
    	
    	/**2.ESECUZIONE DELL'OPERAZIONE (MODEL).**/
    	
    	
    	/**3.VISUALIZZAZIONE/AGGIORNAMENTO DEL RISULTATO:**/
    	
    	int risultato;
    	
    	try{
    		risultato = model.provaTentativo(tentativo);
    	}
    	catch(InvalidParameterException ip) {
    		txtRisultato.setText(ip.getMessage());
    		return;
    	}
    	catch(IllegalStateException is) {
    		txtRisultato.setText(is.getMessage());
    		HBoxTentativo.setDisable(true);
    		return;
    	}
    	
    	if(risultato == 0) {
			txtRisultato.clear();
			txtRisultato.appendText("Hai indovinato!! \nIl numero segreto era: "+model.getNumeroSegreto());
			HBoxTentativo.setDisable(true);	
		}
    	else if(risultato == -1) {
    		txtRisultato.clear();
    		txtRisultato.setText("Tentativo troppo basso.");
    	}
    	else {
    		txtRisultato.clear();
    		txtRisultato.setText("Tentativo troppo alto.");
    	}
    	
    	//NB:
   		//Al posto dei blocchi if-else, 
    	//è possibile utilizzare dei blocchi separati if(){ ... return;}.
    		
    }

    
    /**METODO setModel() PER ASSOCIARE IL MODEL ALL'ATTRIBUTO DI RIFERIMENTO:**/
    public void setModel(Model m) {
    	this.model = m; 
    }
    
    
    
    @FXML
    void initialize() {
        assert HBoxTentativo != null : "fx:id=\"HBoxTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativiRimasti != null : "fx:id=\"txtTentativiRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
    }

}
