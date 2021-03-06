package bmsp.commands;

public class PauseCommand implements Command {

	/**
	 * Pause time in milliseconds
	 */
	private int time;
	
	@Override
	public void nothing() {
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public PauseCommand(int time) {
		this.time = time;
	}
	
	public PauseCommand() {}
}
