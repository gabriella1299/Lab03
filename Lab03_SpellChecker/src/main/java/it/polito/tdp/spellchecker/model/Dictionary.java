package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Dictionary {

	private Set<String> parole;
	//private List<String> parole;
	
	
	public Dictionary() {
		super();
		//this.parole = new LinkedList<String>();
		this.parole = new HashSet<String>();
	}

	public void loadDictionary(String language) {
		try {
			FileReader fr=new FileReader("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				parole.add(word.toLowerCase());
			}
			br.close();
		}catch(IOException o) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> rw=new LinkedList<RichWord>();
		RichWord r=null;
		
		for(String i:inputTextList) {
			if(parole.contains(i)) {
				r=new RichWord(i,true);
				rw.add(r);
			}
			else {
				r=new RichWord(i,false);
				rw.add(r);
			}
		}
		return rw;
	}

	public void unloadDictionary() {
		parole.clear();
	}
	
	/*public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> rw=new LinkedList<RichWord>();
		RichWord r=null;
		boolean trovata=false;
		
		for(String i:inputTextList) {
			for(String p:parole) {
				if(p.equals(i)) {
					r=new RichWord(i,true);
					rw.add(r);
					trovata=true;
					break;
				}
			}
			if(trovata==false) {
				r=new RichWord(i,false);
				rw.add(r);
			}
		}
		return rw;
	}
	*/
	
}
