package ltg.ns.objects;

import processing.core.PGraphics;
import processing.core.PShape;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class NoteCount {

	int _width, _height, _number, _nextCount;
	String _count, _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	PGraphics _pg2;
	PShape _background;
	

	public NoteCount(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_number = 0;
		_count = _p.str(_number);
		_width = 200;
		_height = 100;
		
		_background = _p.loadShape("ge_yellow_note.svg");
		_background = _p.loadShape("ge_blue_note.svg");

		_pg1 = _p.createGraphics(_width, _height);
		_pg2 = _p.createGraphics(_width, _height);

	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
		_pg2.setSize(_width, _height);

	}

	public void changeNoteCount(int count){
		_nextCount = count;
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}
	
	public void resetTint(){
		_number = _nextCount;
		_count = _p.str(_number);
		Ani.to(this, 2f, "_gTint", 255);
	}
	
	public void display(float x, float y) {

		_pg2.beginDraw();
		_pg2.shapeMode(_p.CENTER);
		_pg2.shape(_background, _pg2.width/2, _pg2.height/2, 0.8f*_pg2.width, 0.8f*_pg2.height);
		_pg2.fill(255,0, 0);
		_pg2.stroke(255);
		_pg2.strokeWeight(5);
		_pg2.ellipseMode(_p.CENTER);
		_pg2.ellipse(0.9f*_pg2.width, 0.1f*_pg2.width, 0.2f*_pg2.width, 0.2f*_pg2.width);
		_pg2.endDraw();
		
		_pg1 = _p.createGraphics(_width, _height);
		_pg1.beginDraw();
		_pg1.fill(255);
		_pg1.textAlign(_p.CENTER);
		_pg1.textFont(_p.boldFont);
		_pg1.textSize(0.2f*_pg1.height);

		_pg1.text(_count, 0.9f*_pg2.width, 0.1f*_pg2.width+0.35f*_pg1.textSize);
		_pg1.endDraw();
		
		_p.tint(255, 255);
		_p.imageMode(_p.CENTER);
		_p.image(_pg2, x, y);
		
		_p.tint(255, _gTint);
		_p.image(_pg1, x, y);
	}

}
