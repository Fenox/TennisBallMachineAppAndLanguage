package bmsp.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import bmsp.commands.Command;
import bmsp.commands.JumpBackCommand;
import bmsp.commands.PauseCommand;
import bmsp.commands.ShootCommand;
import bmsp.commands.StartCommand;
import bmsp.commands.StopCommand;

public class BMSPXMLReader implements BMSPReader {

	String file;
	
	public BMSPXMLReader(String file) {
		this.file = file;
	}
	
	public List<Command> read() {
		
		ArrayList<Command> result = new ArrayList<>();
		
		try {
			File xmlFile = new File(file);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(xmlFile);
			
			NodeList commands = doc.getFirstChild().getChildNodes();
			
			for(int i = 0; i < commands.getLength(); i++) {
				Node currentNode = commands.item(i);
				Element currentElement = (Element) currentNode;				
				
				String tagName = currentElement.getTagName();
				switch(tagName) {
					case "START": result.add(new StartCommand());
						break;
					case "STOP": result.add(new StopCommand());
						break;
					case "Pause": result.add(parsePauseCommand(currentElement));
						break;
					case "Shot": result.add(parseShootCommand(currentElement));
						break;
					case "JumpBack": result.add(parseJumpBackCommand(currentElement));
						break;
					default:
						break;
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return result;
	}

	private Command parseJumpBackCommand(Element currentElement) {
		JumpBackCommand cmd = new JumpBackCommand();
		
		String jumpIndex = currentElement.getElementsByTagName("jumpIndex").item(0).getTextContent();
		cmd.setJumpIndex(Integer.parseInt(jumpIndex));
		
		String repetitions = currentElement.getElementsByTagName("repetitions").item(0).getTextContent();
		cmd.setRepetitions(Integer.parseInt(repetitions));
		
		return cmd;
	}

	private Command parseShootCommand(Element currentElement) {
		ShootCommand cmd = new ShootCommand();
		
		String spin = currentElement.getElementsByTagName("spin").item(0).getTextContent();
		cmd.setSpin(Float.parseFloat(spin));
		String x = currentElement.getElementsByTagName("x").item(0).getTextContent();
		cmd.setX(Float.parseFloat(x));
		String y = currentElement.getElementsByTagName("y").item(0).getTextContent();
		cmd.setY(Float.parseFloat(y));
		String height = currentElement.getElementsByTagName("height").item(0).getTextContent();
		cmd.setHeight(Float.parseFloat(height));
		String speed = currentElement.getElementsByTagName("speed").item(0).getTextContent();
		cmd.setSpeed(Float.parseFloat(speed));
		String time = currentElement.getElementsByTagName("time").item(0).getTextContent();
		cmd.setTime(Integer.parseInt(time));
		
		return cmd;
	}

	private Command parsePauseCommand(Element currentElement) {
		PauseCommand cmd = new PauseCommand();

		String time = currentElement.getElementsByTagName("time").item(0).getTextContent();
		cmd.setTime(Integer.parseInt(time));
		
		return cmd;
	}
}
