package ltg.ns.select;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;

import ltg.ns.AmbientVizMain;
import processing.core.PGraphics;
import controlP5.ControlEvent;
import controlP5.ControlFont;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.ControllerInterface;
import controlP5.Toggle;


public class SelectionScreen {

	int _width, _height;
	AmbientVizMain _p;
	PGraphics _pg1;
	ControlP5 _cp5Classes, _cp5Displays, _cp5Button;
	boolean _7ms, _7bl, _7dm, _active;
	DisplayListener displaysListener;
	ClassListener classListener;
	ButtonListener buttonListener;

	public SelectionScreen(AmbientVizMain p) {
		_p = p;
		
		_active = true;
		_cp5Classes = new ControlP5(_p);
		_cp5Displays = new ControlP5(_p);
		_cp5Button = new ControlP5(_p);

		_width = _p.width;
		_height = _p.height;

		displaysListener = new DisplayListener();
		classListener = new ClassListener();
		buttonListener = new ButtonListener();

		_cp5Classes.setFont(_p.boldFont);
		_cp5Displays.setFont(_p.boldFont);
		_cp5Button.setFont(_p.boldFont);

		_cp5Classes.addToggle("Class7MS").addListener(classListener).setCaptionLabel("7MS").setValue(true).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.1f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("Class7BL").addListener(classListener).setCaptionLabel("7BL").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.17f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("Class7DM").addListener(classListener).setCaptionLabel("7DM").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.24f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("ben").addListener(classListener).setCaptionLabel("ben").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.31f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("amanda").addListener(classListener).setCaptionLabel("amanda").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.38f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		
		_cp5Displays.addToggle("DisplayA").setCaptionLabel("A").addListener(displaysListener).setValue(true).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.6f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayB").setCaptionLabel("B").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.7f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayC").setCaptionLabel("C").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.8f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayD").setCaptionLabel("D").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.9f*_width, 0.3f*_height).getCaptionLabel().setSize((int)(0.02f*_width));

		_cp5Button.addButton("Done").addListener(buttonListener).setSize((int)(0.1f*_width), (int)(0.05f*_width)).setPosition(0.45f*_width, 0.7f*_height).getCaptionLabel().setSize((int)(0.02f*_width)).align(ControlP5.CENTER, ControlP5.CENTER);
	}


	private class DisplayListener implements ControlListener {
		public void controlEvent(ControlEvent theEvent) {
			java.util.List<Toggle> list = _cp5Displays.getAll(Toggle.class);
			String name = theEvent.getController().getName().toString();
			if(theEvent.getValue()==1.0){
				_p.displayID = theEvent.getController().getCaptionLabel().getText();
				for(Toggle c: list){
					if(!c.getName().toString().equals(name)){
						c.setValue(false);
					}
				}
			}
		}
	}
	private class ClassListener implements ControlListener {
		public void controlEvent(ControlEvent theEvent) {
			java.util.List<Toggle> list = _cp5Classes.getAll(Toggle.class);
			String name = theEvent.getController().getName().toString();
			if(theEvent.getValue()==1.0){
				_p.className = theEvent.getController().getCaptionLabel().getText();
				for(Toggle c: list){
					if(!c.getName().toString().equals(name)){
						c.setValue(false);
					}
				}
			}
		}
	}

	private class ButtonListener implements ControlListener {
		public void controlEvent(ControlEvent theEvent) {
			_p.createObjects();
			setActive(false);
			_cp5Button.dispose();
			_cp5Classes.dispose();
			_cp5Displays.dispose();
			_p.classDisplaySelected = true;
			_p.sendGeneralInitMessage();
			System.out.println(_p.className + " : " + _p.displayID);
		}
	}


	public boolean isActive() {
		return _active;
	}
	
	public void setActive(boolean active) {
		_active = active;
	}

	public void display(float x, float y) {
		if(isActive()){
			_pg1 = _p.createGraphics(_width, _height);
			_pg1.beginDraw();
			_pg1.background(150);
			_pg1.fill(255);
			_pg1.textFont(_p.boldFont);
			_pg1.textSize(0.04f*_p.height);
			_pg1.textAlign(_p.CENTER);
			_pg1.text("SELECT CLASS", 0.28f*_p.width, 0.2f*_p.height);
			_pg1.textSize(0.04f*_p.height);
			_pg1.text("SELECT DISPLAY", 0.73f*_p.width, 0.2f*_p.height);
			_pg1.endDraw();
			_p.imageMode(_p.CENTER);
			_p.image(_pg1, x, y);
		}
	}

}
