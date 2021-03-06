package bmsp.writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;

import bmsp.BMSPGenerator;
import bmsp.commands.Command;
import bmsp.commands.JumpBackCommand;
import bmsp.commands.PauseCommand;
import bmsp.commands.ShootCommand;
import bmsp.commands.StartCommand;
import bmsp.commands.StopCommand;

public class BMSPXMLFileWriter implements BMSPWriter {

	private String filePath; 
	
	public BMSPXMLFileWriter(String filePath) {
		this.setFilePath(filePath);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void write(BMSPGenerator generator) {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("bmsp");
			doc.appendChild(rootElement);
			
			ArrayList<Command> bmss = (ArrayList<Command>) generator.getBMSS();
			
			for(int i = 0; i < bmss.size(); i++) { 
				Command currentCommand = bmss.get(i);
				
				if(currentCommand instanceof JumpBackCommand) {
					addJumpback(doc, rootElement, (JumpBackCommand) bmss.get(i));
				} else if(currentCommand instanceof ShootCommand) {
					addShot(doc, rootElement, (ShootCommand)bmss.get(i));
				} else if(currentCommand instanceof StartCommand) {
					addStart(doc, rootElement, (StartCommand)bmss.get(i));
				} else if(currentCommand instanceof StopCommand) {
					addStop(doc, rootElement, (StopCommand)bmss.get(i));
				} else if(currentCommand instanceof PauseCommand) {
					addPause(doc, rootElement, (PauseCommand)bmss.get(i));
				}
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(filePath);
			t.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private void addStop(Document doc, Element rootElement, StopCommand stopcmd) {
		Element stop = doc.createElement("STOP");
		rootElement.appendChild(stop);		
	}

	private void addStart(Document doc, Element rootElement, StartCommand startcmd) {
		Element start = doc.createElement("START");
		rootElement.appendChild(start);
	}

	private void addShot(Document doc, Element rootElement, ShootCommand shootcmd) {
		Element shot = doc.createElement("Shot");
		rootElement.appendChild(shot);
		
		Element spin = doc.createElement("spin");
		spin.appendChild(doc.createTextNode(Float.toString(shootcmd.getSpin())));
		shot.appendChild(spin);
		
		Element x = doc.createElement("x");
		x.appendChild(doc.createTextNode(Float.toString(shootcmd.getX())));
		shot.appendChild(x);
		
		Element y = doc.createElement("y");
		y.appendChild(doc.createTextNode(Float.toString(shootcmd.getY())));
		shot.appendChild(y);
		
		Element heigth = doc.createElement("height");
		heigth.appendChild(doc.createTextNode(Float.toString(shootcmd.getHeight())));
		shot.appendChild(heigth);
		
		Element speed = doc.createElement("speed");
		speed.appendChild(doc.createTextNode(Float.toString(shootcmd.getSpeed())));
		shot.appendChild(speed);
		
		Element time = doc.createElement("time");
		time.appendChild(doc.createTextNode(Integer.toString(shootcmd.getTime())));
		shot.appendChild(time);
	}

	private void addJumpback(Document doc, Element rootElement, JumpBackCommand jumpBack) {
		Element jumpBackElement = doc.createElement("JumpBack");
		rootElement.appendChild(jumpBackElement);
		
		Element jumpIndex = doc.createElement("jumpIndex");
		jumpIndex.appendChild(doc.createTextNode(Integer.toString(jumpBack.getJumpIndex())));
		jumpBackElement.appendChild(jumpIndex);
		
		Element repetitions = doc.createElement("repetitions");
		repetitions.appendChild(doc.createTextNode(Integer.toString(jumpBack.getRepetitions())));
		jumpBackElement.appendChild(repetitions);
	}
	
	private void addPause(Document doc, Element rootElement, PauseCommand pausecmd) {
		Element pause = doc.createElement("Pause");
		rootElement.appendChild(pause);
		
		Element time = doc.createElement("time");
		time.appendChild(doc.createTextNode(Integer.toString(pausecmd.getTime())));
		pause.appendChild(time);
	}
}
