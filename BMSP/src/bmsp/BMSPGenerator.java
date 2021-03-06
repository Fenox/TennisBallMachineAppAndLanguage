package bmsp;

import java.util.ArrayList;
import java.util.List;

import bmsp.commands.*;

/**
 * @author Julian
 *
 */
public class BMSPGenerator {

	ArrayList<Command> bmss = new ArrayList<>();
	
	public List<Command> getBMSS() {
		return bmss;
	}

	/**
	 * @param command Command to be added to the ball-machine-shooting-sequence
	 * @return whether the bmss is a valid sequence.
	 */
	public boolean addCommand(Command command) {
		bmss.add(command);
		
		boolean valid = checkValidity();
		if(!valid) {
			bmss.remove(command);
		}
		
		return valid;
	}
	
	private boolean checkValidity() {
		
		for(int i = 0; i < bmss.size(); i++) {
			if(bmss.get(i) instanceof JumpBackCommand) {
				JumpBackCommand command = (JumpBackCommand)bmss.get(i);
				
				if(command.getJumpIndex() >= i) {
					return false;
				}
			}
		}
		
		return true;
	}
}
