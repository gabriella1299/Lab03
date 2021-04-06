package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Dictionary {

	//private Set<String> parole;
	private List<String> parole;
	private String language;
	
	
	public Dictionary() {
		super();
		this.parole = new LinkedList<String>();
		//this.parole = new HashSet<String>();
	}

	

	public List<String> getParole() {
		return parole;
	}



	public void setParole(List<String> parole) {
		this.parole = parole;
	}



	public boolean loadDictionary(String language) {
		parole = new ArrayList<String>();
		this.language = language;
		
		try {
			FileReader fr=new FileReader("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				parole.add(word.toLowerCase());//parole aggiunte tutte in minuscolo
			}
			Collections.sort(parole);
			br.close();
			System.out.println("Dizionario " + language + " loaded. Found " + parole.size() + " words.");
			return true;
		}catch(IOException o) {
			System.out.println("Errore nella lettura del file");
			return false;
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		//List<RichWord> parole= new ArrayList<RichWord>();
		List<RichWord> rw=new LinkedList<RichWord>();
		RichWord r=null;
		
		for(String i:inputTextList) {
			if(parole.contains(i.toLowerCase())) {
				r=new RichWord(i,true); //potevo anche non mettere 'corretta' nel costruttore
			}
			else {
				r=new RichWord(i,false);
			}
			rw.add(r);
		}
		return rw;
	}
	
	public void unloadDictionary() {
		parole.clear();
	}



	public List<RichWord> spellCheckTextLinear(List<String> in) {
		List<RichWord> par = new ArrayList<RichWord>();
		//List<RichWord> parole= new LinkedList<RichWord>();

		for (String str : in) {

			RichWord richWord;

			boolean found = false;
			for (String word : parole) {
				if (word.equalsIgnoreCase(str)) {
					found = true;
					break;
				}
			}

			if (found) {
				richWord=new RichWord(str, true);	
			} else {
				richWord=new RichWord(str, false);
			}

			par.add(richWord);
		}

		return par;
	}



	public List<RichWord> spellCheckTextDichotomic(List<String> in) {
		List<RichWord> par = new ArrayList<RichWord>();
		//List<RichWord> parole= new LinkedList<RichWord>();

		for (String str : in) {

			RichWord richWord;
			if (binarySearch(str.toLowerCase()))
				richWord=new RichWord(str, true);	
			else
				richWord=new RichWord(str, false);	
			par.add(richWord);
		}

		return par;
	}
	
	private boolean binarySearch(String stemp) {
		int inizio = 0;
		int fine = parole.size();

		while (inizio != fine) {
			int medio = inizio + (fine - inizio) / 2;
			if (stemp.compareToIgnoreCase(parole.get(medio)) == 0) {
				return true;
			} else if (stemp.compareToIgnoreCase(parole.get(medio)) > 0) {
				inizio = medio + 1;
			} else {
				fine = medio;
			}
		}

		return false;
	}
	
	/*public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
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
	
	public List<RichWord> spellCheckTextDicotomic(List<String> inputTextList){
		List<RichWord> rw=new LinkedList<RichWord>();
		RichWord r=null;
		boolean trovata=false;
		
		for(String i:inputTextList) {
			((Object) parole).getParole(parole.size()/2)
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
	}*/
}
