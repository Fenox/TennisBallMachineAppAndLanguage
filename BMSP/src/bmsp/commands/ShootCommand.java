package bmsp.commands;

/**
 * @author Julian
 *
 */
public class ShootCommand implements Command {

	/**
	 * Spin in rounds per Minute.
	 */
	private float spin;
	
	/**
	 * target X Position of the ball touching the ground on the other 
	 * site of the field as distance from the outer left line
	 */
	private float x;

	/**
	 * target Y Position of the ball touching the ground on the other 
	 * site of the field as distance from the net.
	 */
	private float y;
	
	/**
	 * Maximum height of the ball in meters.
	 */
	private float height;
	
	/**
	 * Maximum speed of the ball in km/h.
	 */
	private float speed;

	/**
	 * Time from the start of the command until 
	 * it shoots and ends in milliseconds.
	 */
	private int time;
	
	public ShootCommand(float spin, float x, float y, float height, float speed, int time) {
		this.spin = spin;
		this.x = x;
		this.y = y; 
		this.height = height;
		this.speed = speed;
		this.time = time;
	}
	
	public ShootCommand() {}
	
	public float getSpin() {
		return spin;
	}

	public void setSpin(float spin) {
		this.spin = spin;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float heigth) {
		this.height = heigth;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public void nothing() {
	}
}
