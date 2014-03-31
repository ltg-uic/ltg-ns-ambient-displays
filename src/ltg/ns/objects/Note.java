package ltg.ns.objects;

import processing.core.PGraphics;
import ltg.ns.AmbientVizMain;
import de.looksgood.ani.*;

public class Note {
	int _width, _height;
	String _note, _nextNote, _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	
	

	public Note(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "Note";
		_note = "Lorem ipsum dolor sit amet,"
				+ " consectetur adipisicing elit, sed do eiusmod "
				+ "tempor incididunt ut labore et dolore magna aliqua. "
				+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco"
				+ " laboris nisi ut aliquip ex ea commodo consequat. Duis aute"
				+ " irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur";
		_width = 200;
		_height = 100;
		_pg1 = _p.createGraphics(_width, _height);
		_pg1.textFont(_p.notesFont);
		
		
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
	}

	public void changeNote(){
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}
	
	public void resetTint(){
		_note = _nextNote;
		Ani.to(this, 2f, "_gTint", 255);
	}
	
	public void display(float x, float y) {
		_pg1.beginDraw();
		_pg1.background(_p.bgColor);
		_pg1.fill(0);
		
		_pg1.textSize(0.15f*_height);
		_pg1.text(_title, (int)(0.1f*_height), (int)(0.15f*_height), (int)(_width-0.2f*_height), (int)(_height-0.2f*_height));
		
		_pg1.textSize(0.05f*_height);
		_pg1.text(_note, (int)(0.1f*_height), (int)(0.4f*_height), (int)(_width-0.2f*_height), (int)(_height-0.4f*_height));
		_pg1.endDraw();
		
		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
	}

	public void updateNote(String note) {
		_nextNote = note;
		changeNote();
	}
}
