package ltg.ns.objects;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PShape;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class NoteCount {

	int _width, _height, _number;
	String _school, _class, _group, _nextCount, _nextSchool, _nextClass, _nextGroup, _title;
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
		_width = 200;
		_height = 100;

		_background = _p.loadShape("note_1.svg");
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
	}

	public void changeNoteCount(){;
	Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		_number = Integer.parseInt(_nextCount);
		_school = _nextSchool;
		_class = _nextClass;
		_group = _nextGroup;
		Ani.to(this, 2f, "_gTint", 255);
	}

	public void display(float x, float y) {

		if(_number > 0){
			_pg1 = _p.createGraphics(_width, _height);
			_pg2 = _p.createGraphics(_width, _height);

			_pg2.beginDraw();
			_pg2.shapeMode(_p.CENTER);
			_pg2.shape(_background, _pg2.width/2, _pg2.height/2, 0.8f*_pg2.width, 0.8f*_pg2.height);
			_pg2.fill(255,0, 0);
			_pg2.stroke(255);
			_pg2.strokeWeight(5);
			_pg2.ellipseMode(_p.CENTER);
			_pg2.ellipse(0.85f*_pg2.width, 0.15f*_pg2.width, 0.25f*_pg2.width, 0.25f*_pg2.width);
			_pg2.endDraw();

			_pg1 = _p.createGraphics(_width, _height);
			_pg1.beginDraw();
			_pg1.fill(255);
			_pg1.textAlign(PConstants.CENTER);
			_pg1.textFont(_p.boldFont);
			_pg1.textSize(0.2f*_pg1.height);

			_pg1.text(_number, 0.85f*_pg2.width, 0.15f*_pg2.width+0.4f*_pg1.textSize);

			float textSize = 0.045f*_pg1.width;
			_pg1.fill(0);
			_pg1.textFont(_p.normalFont);
			_pg1.textSize(textSize);
			_pg1.textAlign(PConstants.LEFT);
			_pg1.text(_school, (int)(0.13f*_pg1.width), (int)(0.87f*_pg1.height));
			_pg1.text(_class, (int)(0.35f*_pg1.width), (int)(0.87f*_pg1.height));
			if(_group != null){
				_pg1.text(_group, (int)(0.55f*_pg1.width), (int)(0.87f*_pg1.height));
			}
			_pg1.endDraw();

			_p.tint(255, 255);
			_p.imageMode(PConstants.CENTER);
			_p.image(_pg2, x, y);

			_p.tint(255, _gTint);
			_p.image(_pg1, x, y);

			_pg1.dispose();
			_pg2.dispose();
		}


	}

	public void updateCount(String eSchool, String eClass, String eGroup, String eCount) {
		_nextSchool = eSchool;
		_nextClass = eClass;
		_nextGroup = eGroup;
		_nextCount = eCount;
		if(_number != Integer.parseInt(_nextCount)) changeNoteCount();
	}

	public void updateCount(String eSchool, String eClass, String eCount) {
		_nextSchool = eSchool;
		_nextClass = eClass;
		_nextCount = eCount;
		if(_number != Integer.parseInt(_nextCount)) changeNoteCount();

	}

}
