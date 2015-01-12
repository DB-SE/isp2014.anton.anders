package beDone.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import beDone.core.TodoEntry;
import beDone.core.TodoList;
import beDone.core.addons.TodoEntryAddonInterface;
import beDone.helper.ArrayStringConverter;

public class PersistentTextStorage implements PersistentStorageInterface {

	protected String filename = "mylist.todo";
	protected TodoList todolist = null;
	
	public void setFilename(String f){
		this.filename = f;
	}
	
	private String encode(String s){
		return s.replaceAll(";", "#SEMICOLON#");
	}
	
	private String decode(String s){
		return s.replaceAll("#SEMICOLON#", ";");
	}
	
	/*
	 * Aufbau des Datensatzes: 
	 * 	ID ;title ; text ; status[;[TAGS_COMMA_SEPERATED]] 
	 */
	public boolean parseLine(String line, String[] ColNames){
		TodoEntry t = new TodoEntry();
		String[] raw = line.split(";");
		
		if(raw.length < 4)
			return false;
		
		t.setID(	raw[0]);
		t.setTitle( this.decode(raw[1]) );
		t.setText(  this.decode(raw[2]) );
		t.setStatus(raw[3]);
		for(int i = 4 ; i < raw.length; i++){
			for(TodoEntryAddonInterface a: t.getAddons()){
				if(a.getName().equals(ColNames[i].trim())){
					a.setValues( ArrayStringConverter.explode(this.decode(raw[i]), ","));
				}
			}
		}
		
		this.todolist.addTodo(t);
		
		return true;
	}
	
	public String buildLine(TodoEntry t){
		
		String line = new String();
		line += t.getID() + ";";
		line += this.encode(t.getTitle()) + ";";
		line += this.encode(t.getText())  + ";";
		line += t.getStatus();
		for(TodoEntryAddonInterface a : t.getAddons()){
			line +=";" + this.encode(ArrayStringConverter.implode(a.getValues(),","));
		}
		line +="\n";
		return line;
	}
	
	public String buildHeaderLine(TodoEntry t){
		
		String line = new String();
		line += "ID;";
		line += "Titel;";
		line += "Beschreibung;";
		line += "Status";
		for(TodoEntryAddonInterface a : t.getAddons()){
			line +=";" + a.getName();
		}
		line +="\n";
		return line;
	}
	
	private String[] parseHeader(String line){
		return line.split(";");
	}
	
	public boolean load() {
		try {
			InputStream is = new FileInputStream(this.filename);
			Scanner scanner = new Scanner(is);
			String[] header = {};
			if(scanner.hasNext()){
				header = this.parseHeader(scanner.nextLine());
			}
			
			while(scanner.hasNext()){
				this.parseLine(scanner.nextLine(), header);
			}
			scanner.close();
			is.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("FILE NOT FOUND");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean clearFile(String filename){
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename);
			writer.print("");
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean save() {
		this.clearFile(this.filename);
		
		OutputStream fos;
		try {
			fos = new FileOutputStream(this.filename);
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
			
			out.write(this.buildHeaderLine(new TodoEntry()));
			out.flush();
			
			for(TodoEntry t: this.todolist.getTodos()){
				out.write( this.buildLine(t) );
				out.flush();
			}
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}


	public void setTodoList(TodoList t) {
		this.todolist = t;
	}
	

}
