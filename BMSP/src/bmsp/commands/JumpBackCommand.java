package bmsp.commands;

public class JumpBackCommand implements Command {

	private int jumpIndex;
	private int repetitions;
	
	public JumpBackCommand(int jumpIndex, int repetitions) {
		this.jumpIndex = jumpIndex;
		this.repetitions = repetitions;
	}
	
	public JumpBackCommand() {}
	
	@Override
	public void nothing() {
	}
		
	public int getJumpIndex() {
		return jumpIndex;
	}

	public void setJumpIndex(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

}
