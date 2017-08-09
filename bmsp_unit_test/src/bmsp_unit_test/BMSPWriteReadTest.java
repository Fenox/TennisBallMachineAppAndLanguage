package bmsp_unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bmsp.BMSPGenerator;
import bmsp.commands.Command;
import bmsp.commands.JumpBackCommand;
import bmsp.commands.ShootCommand;
import bmsp.commands.StartCommand;
import bmsp.commands.StopCommand;
import bmsp.reader.BMSPXMLReader;
import bmsp.writer.BMSPXMLFileWriter;

public class BMSPWriteReadTest {

	@Test
	public void testWriteAndRead() {
		
		BMSPGenerator gen = generateCommands();
	
		String filePath = "xmlFile.xml";
		
		BMSPXMLFileWriter writer = new BMSPXMLFileWriter(filePath);
		writer.write(gen);
		
		BMSPXMLReader reader = new BMSPXMLReader(filePath);
		ArrayList<Command> read = (ArrayList<Command>) reader.read();
		
		assert(read.get(0) instanceof StartCommand);
		assert(read.get(1) instanceof ShootCommand);
		assert(read.get(2) instanceof JumpBackCommand);
		assert(read.get(3) instanceof ShootCommand);
		assert(read.get(4) instanceof StopCommand);
		
		ShootCommand scmd1 = (ShootCommand) read.get(1);
		JumpBackCommand jcmd1 = (JumpBackCommand) read.get(2);
		ShootCommand scmd2 = (ShootCommand) read.get(3);
		
		assertEquals(scmd1.getSpin(), 1000, 0.01);
		assertEquals(scmd1.getX(), 5, Float.MIN_NORMAL);
		assertEquals(scmd1.getY(), 10, Float.MIN_NORMAL);
		assertEquals(scmd1.getHeight(), 12, Float.MIN_NORMAL);
		assertEquals(scmd1.getSpeed(), 100, Float.MIN_NORMAL);
		assertEquals(scmd1.getTime(), 3000);
		
		assertEquals(jcmd1.getJumpIndex(), 1);
		assertEquals(jcmd1.getRepetitions(), 5);
		
		assertEquals(scmd2.getSpin(), 2000, Float.MIN_NORMAL);
		assertEquals(scmd2.getX(), 7, Float.MIN_NORMAL);
		assertEquals(scmd2.getY(), 3.5f, Float.MIN_NORMAL);
		assertEquals(scmd2.getHeight(), 20.1f, Float.MIN_NORMAL);
		assertEquals(scmd2.getSpeed(), 150.5f, Float.MIN_NORMAL);
		assertEquals(scmd2.getTime(), 1000);
	}
	
	private BMSPGenerator generateCommands() {
		BMSPGenerator gen = new BMSPGenerator();
		gen.addCommand(new StartCommand());
		
		ShootCommand cmd1 = new ShootCommand(1000,5,10,12,100,3000);
		gen.addCommand(cmd1);
		
		JumpBackCommand cmd2 = new JumpBackCommand(1, 5);
		gen.addCommand(cmd2);
		
		ShootCommand cmd3 = new ShootCommand(2000,7,3.5f,20.1f,150.5f,1000);
		gen.addCommand(cmd3);
		
		gen.addCommand(new StopCommand());
		
		return gen;
	}
}
