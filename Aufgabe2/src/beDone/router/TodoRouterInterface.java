package beDone.router;

import beDone.input.TodoInputInterface;
import beDone.output.TodoOutputInterface;
import beDone.storage.PersistentStorageInterface;

public interface TodoRouterInterface {

	/**
	 * Startet die Benutzerführung und reagiert auf die Eingaben des Benutzers
	 * => Bietet alle Elemente an, die zur Interaktion benötigt werden
	 */
	public void start();
	
	public void setOutput(TodoOutputInterface output);
	public void setInput(TodoInputInterface input);
	public void setStorage(PersistentStorageInterface storage);
}
