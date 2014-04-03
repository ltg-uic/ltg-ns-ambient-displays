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
	float _scroll;

	public ScoreBoard(AmbientVizMain p, int size) {
		_p = p;
		_scroll = 0;
		_gTint = 255;
		_width = (int)(_p.width);
		_height = 600;
		_lineHeight = _height/6;
		_size = size;
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
		_lineHeight = (int)_height/6;
		//_pg1.setSize(_width, _height);

		for(ScoreLine s:_scoreLines){
			s.setDimensions(_width, _lineHeight);
		}
	}

	public void scrollUP(){
		Ani.to(this, 2f, "_scroll",-_lineHeight, Ani.LINEAR, "onEnd:resetScroll");
	}

	public void resetScroll(){
		ScoreLine tmpLine = _scoreLines.get(0);
		_scoreLines.remove(0);
		_scoreLines.add(tmpLine);
		_scroll = 0;
		Ani.to(this, 1f, "_scroll", 0, Ani.LINEAR);
	}

	public void display(float x, float y) {		
	
		_pg1 = _p.createGraphics(_width, _height);

		float xLine = _width/2;
		float yLine = _lineHeight/2-_lineHeight/4+_scroll;
		
		_pg1.beginDraw();
		_pg1.background(255);
		_pg1.imageMode(_p.CENTER);
		for(int i = 0; i < _scoreLines.size(); i++){
			PGraphics _pg2 =  _scoreLines.get(i).display(xLine, yLine);
			_pg1.image(_pg2, xLine, yLine);
			yLine += _lineHeight;
		}
		//_pg1.shape(_border, 0, 0, _pg1.width, _pg1.height);
		_pg1.rectMode(_p.CENTER);
		_pg1.noStroke();
		_pg1.fill(255);
		_pg1.rect(_pg1.width/2, _pg1.height*0.05f, _pg1.width, _pg1.height*0.1f);
		_pg1.stroke(0);
		_pg1.line(0, _pg1.height*0.1f, _pg1.width, _pg1.height*0.1f);
		
		_pg1.fill(_p.last5MinColor);
		_pg1.textAlign(_p.CENTER);
		_pg1.textFont(_p.boldFont);
		_pg1.textSize(0.03f*_pg1.width);
		_pg1.text("Last 5 Minutes", 0.43f*_pg1.width, _pg1.height*0.05f);
		_pg1.fill(0);
		
		_pg1.textSize(0.03f*_pg1.width);
		_pg1.text("Today", 0.64f*_pg1.width, _pg1.height*0.05f);
		
		_pg1.textSize(0.03f*_pg1.width);
		_pg1.text("Total", 0.84f*_pg1.width, _pg1.height*0.05f);

		_pg1.endDraw();
		
		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
		_pg1.dispose();
		
		
		
		//_p.rect(0, 0, _, d);
		
		
	}
}
