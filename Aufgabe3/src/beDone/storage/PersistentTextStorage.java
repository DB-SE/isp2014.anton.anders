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
	 * 	ID ;[[TAGS_COMMA_SEPERATED];] title ; text ; status
	 */
	public boolean parseLine(String line){
		TodoEntry t = new TodoEntry();
		String[] raw = line.split(";");
		
		if(raw.length < 4)
			return false;
		
		t.setID(	raw[0]);
		t.setTitle( this.decode(raw[1]) );
		t.setText(  this.decode(raw[2]) );
		t.setStatus(raw[3]);
		
		this.todolist.addTodo(t);
		
		return true;
	}
	
	public String buildLine(TodoEntry t){
		
		String line = new String();
		line += t.getID() + ";";
		line += this.encode(t.getTitle()) + ";";
		line += this.decode(t.getText())  + ";";
		line += t.getStatus();
		
		return line;
	}
	
	public boolean load() {
		try {
			InputStream is = new FileInputStream(this.filename);
			Scanner scanner = new Scanner(is);
			while(scanner.hasNext()){
				this.parseLine(scanner.nextLine());
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("FILE NOT FOUND");
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
			out.write("");
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
