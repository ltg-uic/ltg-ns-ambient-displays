package ltg.ns.objects;

import processing.core.PGraphics;
import processing.core.PShape;
import ltg.ns.AmbientVizMain;
import de.looksgood.ani.*;

public class Note {
	int _width, _height;
	String _note, _nextNote, _nextSchool, _nextClass, _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	PShape _background;
	
	

	public Note(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "Latest note";
		_background = _p.loadShape("ge_note.svg");
		_note = "Lorem ipsum dolor sit amet,"
				+ " consectetur adipisicing elit, sed do eiusmod "
				+ "tempor incididunt ut labore et dolore magna aliqua. ";
		_width = 200;
		_height = 100;		
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
	}

	public void changeNote(){
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}
	
	public void resetTint(){
		_note = _nextNote;
		Ani.to(this, 2f, "_gTint", 255);
	}
	
	public void display(float x, float y) {
		_pg1 = _p.createGraphics(_width, _height);

		_pg1.beginDraw();
		_pg1.background(_p.bgColor);
		_pg1.shape(_background,0, 0, _pg1.width, _pg1.height);
		
		//title
		_pg1.fill(255);
		_pg1.textFont(_p.boldFont);
		_pg1.textSize(0.12f*_height);
		_pg1.text(_title, (int)(0.1f*_pg1.height), (int)(0.025f*_pg1.height), (int)(_pg1.width-0.2f*_pg1.height), (int)(_pg1.height-0.2f*_pg1.height));
		
		
		//body
		_pg1.fill(0);
		_pg1.textFont(_p.normalFont);
		_pg1.textSize(0.1f*_height);
		_pg1.text(_note, (int)(0.1f*_pg1.height), (int)(0.2f*_pg1.height), (int)(_pg1.width-0.2f*_pg1.height), (int)(_pg1.height-0.4f*_pg1.height));
		_pg1.endDraw();
		
		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
		_pg1.dispose();
	}

	public void updateNote(String eSchool, String eClass, String eNoteBody) {
		_nextNote = eNoteBody;
		_nextClass = eClass;
		_nextSchool = eSchool;
		changeNote();
	}
}
