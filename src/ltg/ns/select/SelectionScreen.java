package ltg.ns.select;

import java.util.ArrayList;

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
	ControlP5 _cp5Classes, _cp5Displays;
	boolean _7ms, _7bl, _7dm, _active;
	DisplayListener displaysListener;
	ClassListener classListener;

	@SuppressWarnings("deprecation")
	public SelectionScreen(AmbientVizMain p) {
		_p = p;
		_cp5Classes = new ControlP5(_p);
		_cp5Displays = new ControlP5(_p);

		_width = _p.width;
		_height = _p.height;
		
		displaysListener = new DisplayListener();
		classListener = new ClassListener();
		
		_cp5Classes.setFont(_p.boldFont);
		_cp5Displays.setFont(_p.boldFont);


		_cp5Classes.addToggle("Class7MS").addListener(classListener).setCaptionLabel("7MS").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.15f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("Class7BL").addListener(classListener).setCaptionLabel("7BL").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.25f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Classes.addToggle("Class7DM").addListener(classListener).setCaptionLabel("7DM").setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.35f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));

		_cp5Displays.addToggle("DisplayA").setCaptionLabel("A").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.55f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayB").setCaptionLabel("B").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.65f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayC").setCaptionLabel("C").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.75f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
		_cp5Displays.addToggle("DisplayD").setCaptionLabel("D").addListener(displaysListener).setSize((int)(0.05f*_width), (int)(0.05f*_width)).setPosition(0.85f*_width, 0.2f*_height).getCaptionLabel().setSize((int)(0.02f*_width));
	}


	private class DisplayListener implements ControlListener {
		public void controlEvent(ControlEvent theEvent) {

			ArrayList<ControllerInterface> controllers = (ArrayList) _cp5Displays.getAll();

			for(ControllerInterface c : controllers){
				if(!c.getName().equals(theEvent.getController().getName()))
					System.out.println(c.getName());
			}
		}
	}
	private class ClassListener implements ControlListener {
		public void controlEvent(ControlEvent theEvent) {

			ArrayList<ControllerInterface> controllers = (ArrayList) _cp5Classes.getAll();

			for(ControllerInterface c : controllers){
				if(!c.getName().equals(theEvent.getController().getName()))
					System.out.println(c.getName());
			}
		}
	}

	public void display(float x, float y) {
		_pg1 = _p.createGraphics(_width, _height);
		_pg1.beginDraw();
		_pg1.background(100);
		_pg1.endDraw();

		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
	}

}
