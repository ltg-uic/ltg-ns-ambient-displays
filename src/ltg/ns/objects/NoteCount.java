package ltg.ns.objects;

import processing.core.PGraphics;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class NoteCount {

	int _width, _height, _number, _nextCount;
	String _count, _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	

	public NoteCount(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_number = 0;
		_count = _p.str(_number);
		_width = 200;
		_height = 100;
		_pg1 = _p.createGraphics(_width, _height);
		_pg1.textFont(_p.notesNumFont);
		
		
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
	}

	public void changeNoteCount(int count){
		_nextCount = count;
		Ani.to(this, 2.5f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}
	
	public void resetTint(){
		_number = _nextCount;
		_count = _p.str(_number);
		Ani.to(this, 2.5f, "_gTint", 255);
	}
	
	public void display(float x, float y) {
		_pg1.beginDraw();
		_pg1.background(_p.bgColor);
		_pg1.fill(0);
	
		_pg1.textSize(0.5f*_height);
		_pg1.textAlign(_p.CENTER);
		_pg1.text(_count, (int)(0.5f*_width), (int)(0.5f*_height+0.2f*_pg1.textSize));
		_pg1.endDraw();
		
		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
		
		_p.fill(100);
		_p.rectMode(_p.CENTER);
		_p.rect(x, (int)(y+_height/2-0.1*_height), _width, (int)(0.15*_width), 2);
		
		_p.fill(200);
		_p.textAlign(_p.LEFT);
		_p.textSize(0.1f*_height);
		_p.text(_title, x-(int)(0.4f*_width), (int)(y+_height/2-0.06*_height));
	}

}
