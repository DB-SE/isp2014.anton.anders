package beDone.helper;

import java.util.Scanner;

public class ConsoleIO {

	
	protected static ConsoleIO instance = null;
	protected Scanner scanner = null;
	
	public static ConsoleIO getInstance(){
		if(ConsoleIO.instance == null){
			ConsoleIO.instance = new ConsoleIO();
		}
		return ConsoleIO.instance;
	}
	
	public ConsoleIO(){
		this.scanner = new Scanner(System.in);
	}
	
	public void writeLine(String s){
		System.out.println(s);
	}
	
	public void writeNewPage(){
		System.out.println("\n\n\n\n ###########################################");
	}
	
	public void writeTable(String[] s){
		for(String st : s){
			System.out.print(st+"\t");
		}
		System.out.print("\n");
	}
	
	public String readLine(){
		return this.scanner.nextLine();
	}
	
	public int readInt(){
		return this.scanner.nextInt();
	}
	
}
