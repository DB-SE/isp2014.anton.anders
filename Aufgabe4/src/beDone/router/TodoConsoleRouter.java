package beDone.router;

import java.util.Scanner;

import beDone.helper.ConsoleIO;
import beDone.input.TodoInputInterface;
import beDone.output.TodoOutputInterface;
import beDone.storage.PersistentStorageInterface;

public class TodoConsoleRouter implements TodoRouterInterface {

	private enum Zustand {MENU, INPUT, OUTPUT, LOAD, SAVE, EXIT};
	
	private TodoOutputInterface 		out;
	private TodoInputInterface 			in;
	private PersistentStorageInterface 	storage;
	
	private Zustand zustand = Zustand.MENU;
	
	private boolean running = false;
	
	public void start() {
		
		boolean exit = false;
		/*
		 * Schleife, bis nutzer EXIT wählt
		 */
		while(this.zustand != Zustand.EXIT || !exit ){

				switch (this.zustand) {
				case MENU:
					this.printMenu();
					this.zustand = this.getMenuSelect();
					break;
				
				case INPUT:
					if(this.in != null)
						this.in.callDialog();
					this.zustand = Zustand.MENU;
					break;
				
				case OUTPUT:
					if(this.out != null)
						this.out.callOutput();
					this.zustand = Zustand.MENU;
					break;
	
				case SAVE:
					if(this.storage != null)
						this.storage.save();
					this.zustand = Zustand.MENU;
					break;
					
				case LOAD:
					if(this.storage != null)
						this.storage.load();
					this.zustand = Zustand.MENU;
				case EXIT:
					System.out.println("Bye!");
					exit =true;
					break;
				default:
					break;
				}
			
		}
		
	}

	public void setOutput(TodoOutputInterface output) {
		this.out = output;
	}

	public void setInput(TodoInputInterface input) {
		this.in = input;
		
	}

	public void setStorage(PersistentStorageInterface storage) {
		this.storage = storage;
	}

	
	private void printMenu(){
		
			
		System.out.println("Auswahl der Aktion:");
		if(this.in != null) 		System.out.println("\t Neuen Todo-Eintrag hinzufügen: n");
		if(this.out != null)		System.out.println("\t Todos ansehen:                 v");
		if(this.storage != null)	System.out.println("\t Todoliste-Laden:               l");
		if(this.storage != null)	System.out.println("\t Aktuelle Todoliste-Speichern:  s");
		System.out.println("\t Programm schließen             q");
		
		System.out.println("");
		System.out.print("Bitte geben Sie den Kürzel für die Aktion ein:");
	}
	
	private Zustand getMenuSelect(){
		
		String z = ConsoleIO.getInstance().readLine();

		if(z.length()<1)
			return this.getMenuSelect();
		
		switch(z.charAt(0)){
			case 'n': return Zustand.INPUT;
			case 'v': return Zustand.OUTPUT;
			case 'l': return Zustand.LOAD;
			case 's': return Zustand.SAVE;
			case 'q': return Zustand.EXIT;
			case '\r': return this.getMenuSelect();
			case '\n': return this.getMenuSelect();
			default: return Zustand.MENU;
		}
		
	}

	public void stop() {
		
		
	}

	public boolean isRunning() {
		return this.running;
	}

	public String getName() {
		return "TodoConsoleRouter";
	}
}
