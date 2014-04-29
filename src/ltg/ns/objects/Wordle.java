package ltg.ns.objects;

import processing.core.PGraphics;
import processing.core.PImage;
import wordcram.Colorers;
import wordcram.Word;
import wordcram.WordCram;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class Wordle {

	WordCram _wordle, _nextWordle;
	int _width, _height;
	String _title, _school, _class, _group, _nextSchool, _nextClass, _nextGroup;
	String _wordleString;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	PImage _wordleImg;
	boolean _drawn;

	public Wordle(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_school = "";
		_class = "";
		_group = "";
		_width = 50;
		_height = 50;
		_wordleString = "";
		_wordle = new WordCram(_p).fromTextString(_wordleString)
				.withColorer(Colorers.twoHuesRandomSatsOnWhite(_p))
				.sizedByWeight(7, 100);
		_drawn = false;
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
	}

	public void changeWordle(String s){
		_nextWordle = new WordCram(_p).fromTextString(s).withColorer(Colorers.twoHuesRandomSatsOnWhite(_p))
				.sizedByWeight(7, 100);
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		_wordle = _nextWordle;
		_school = _nextSchool;
		_class = _nextClass;
		_group = _nextGroup;
		Ani.to(this, 1.5f, "_gTint", 255);
	}

	public void display(float x, float y) {
		if(!_drawn){
			_pg1 = _p.createGraphics(_width, _height);
			_wordle.toCanvas(_pg1);
			_pg1.beginDraw();
			if(_drawn != true){
				_wordle.drawAll();
				_drawn = true;
			}
			_pg1.fill(0);
			_pg1.textFont(_p.normalFont);
			_pg1.textSize(0.06f*_height);
			_pg1.text(_school, (int)(0.1f*_pg1.width), (int)(0.97f*_pg1.height));
			_pg1.text(_class, (int)(0.35f*_pg1.width), (int)(0.97f*_pg1.height));
			if(_group != null){
				_pg1.text(_group, (int)(0.65f*_pg1.width), (int)(0.97f*_pg1.height));
			}
			_pg1.endDraw();
			_p.tint(255, _gTint);
			_p.imageMode(_p.CENTER);
			_p.image(_pg1, x, y);
			_pg1.dispose();
		}
		
		if(_drawn){
			_wordleImg = _pg1.get();
			_p.image(_pg1, x, y);
		}
		

	}

	public void update(String eSchool, String eClass, String eGroup, String eWordle){
		_wordleString = eWordle;
		_nextSchool = eSchool;
		_nextClass = eClass;
		_nextGroup = eGroup;
		_wordle = null;
		_wordle = new WordCram(_p).fromTextString(_wordleString)
				.withColorer(Colorers.twoHuesRandomSatsOnWhite(_p))
				.sizedByWeight(7, 100);
		changeWordle(eWordle);
	}

	public void reload() {
		_wordle = null;
		_wordle = new WordCram(_p).fromTextString(_wordleString)
				.withColorer(Colorers.twoHuesRandomSatsOnWhite(_p))
				.sizedByWeight(7, 100);
		_drawn = false;
	}


}