package ltg.ns.objects;

import java.util.ArrayList;

import ltg.ns.AmbientVizMain;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import de.looksgood.ani.Ani;

public class ScoreBoard {
	ArrayList<ScoreLine> _scoreLines;
	int _width, _height, _size, _lineHeight;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	PShape _border;

	public ScoreBoard(AmbientVizMain p, int size) {
		_p = p;
		_gTint = 255;
		_width = (int)(_p.width);
		_height = 600;
		_lineHeight = _height/5;
		_size = size;
		_pg1 = _p.createGraphics(_width, _height);
		_scoreLines = new ArrayList<ScoreLine>();
		_border = _p.loadShape("white_frame.svg");
		initScoreLines();
	}

	private void initScoreLines(){
		for(int i=0; i<_size; i++){
			ScoreLine slLine = new ScoreLine(_p);
			slLine.setDimensions(_width, _lineHeight);
			_scoreLines.add(slLine);
		}
	}
	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
	}

	public void scrollUP(){
		//Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		//Ani.to(this, 2f, "_gTint", 255);
	}

	public void display(float x, float y) {		
		
//		_pg1.beginDraw();
//		_pg1.background(_p.color(200));
//		_pg1.beginDraw();
//		_pg1.background(_board._p.color(200));
//		_pg1.rectMode(_board._p.CENTER);
//		_pg1.noStroke();
//		_pg1.fill(255);
//		_pg1.rect(_pg1.width/2, _pg1.height/2, _pg1.width-0.01f*_pg1.width, _pg1.height-0.02f*_pg1.height, 4);
//		_pg1.fill(0);
//		_pg1.textSize(0.5f*_height);
//		_pg1.textAlign(_board._p.LEFT);
//		_pg1.textFont(_board._p.boldFont);
//
//		_pg1.text(_tag+":", 0.02f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
//		_pg1.fill(_board._p.last5MinColor);
//
//		_pg1.text(_count5Min, 0.4f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
//		_pg1.fill(0);
//		_pg1.fill(0);
//		_pg1.text(_countToday, 0.6f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
//		_pg1.text(_countAll, 0.8f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
//		_pg1.endDraw();
//		
//		
//		
//		
//		
//		
//		_pg1.endDraw();
//		
//		_p.tint(255, _gTint);
//		_p.imageMode(_p.CENTER);
//		_p.image(_pg1, x, y);
//		
//		float xLine = _width/2;
//		float yLine = _lineHeight/2-_lineHeight/4;
//		
//		for(int i = 0; i < _scoreLines.size(); i++){
//			_scoreLines.get(i).display(xLine, yLine);
//			yLine += _lineHeight;
//		}
//		
//		_p.shape(_border, x, y, _p.width, _p.height);
		
		
	}
}
