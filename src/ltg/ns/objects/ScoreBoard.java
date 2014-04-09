package ltg.ns.objects;

import java.util.ArrayList;

import ltg.ns.AmbientVizMain;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import de.looksgood.ani.Ani;

public class ScoreBoard {
	ArrayList<ScoreLine> _scoreLines;
	int _width, _height, _size, _lineHeight, _lastChanged;
	String _class, _group, _school;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	PShape _border;
	float _scroll;

	public ScoreBoard(AmbientVizMain p, int size) {
		_p = p;
		_scroll = 0;
		_class = "ben";
		_group = "ics";
		_school = "BZAEDS";
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
		Ani.to(this, 1.5f, "_scroll",-_lineHeight, Ani.LINEAR, "onEnd:resetScroll");
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
		float textSize = 0.025f*_pg1.width;
		float xLine = _width/2;
//		float yLine = _lineHeight/2-_lineHeight/4 + _scroll;
		float yLine = _lineHeight + _scroll;

		
		
		_pg1.beginDraw();
		_pg1.background(255);
		_pg1.imageMode(_p.CENTER);
		for(int i = 0; i < _scoreLines.size(); i++){
			PGraphics _pg2 =  _scoreLines.get(i).display(xLine, yLine);
			_pg1.image(_pg2, xLine, yLine);
			yLine += _lineHeight;
			_pg2.dispose();
		}
		//_pg1.shape(_border, 0, 0, _pg1.width, _pg1.height);
		_pg1.rectMode(_p.CENTER);
		_pg1.noStroke();
		_pg1.fill(255);
		_pg1.rect(_pg1.width/2, _pg1.height*0.05f, _pg1.width, _pg1.height*0.1f);
		_pg1.stroke(0);
		_pg1.strokeWeight(2);
		_pg1.line(0, _pg1.height*0.1f, _pg1.width, _pg1.height*0.1f);
		
		_pg1.fill(_p.last5MinColor);
		_pg1.textAlign(_p.CENTER);
		_pg1.textFont(_p.boldFont);
		
		
		float textSizeTitles = 0.03f*_pg1.width;
		_pg1.textSize(textSizeTitles);
		_pg1.text("Last 5 Minutes", 0.43f*_pg1.width, _pg1.height*0.05f);
		_pg1.fill(0);
		
		_pg1.textSize(textSizeTitles);
		_pg1.text("Today", 0.64f*_pg1.width, _pg1.height*0.05f);
		
		_pg1.textSize(textSizeTitles);
		_pg1.text("Total", 0.84f*_pg1.width, _pg1.height*0.05f);

		_pg1.endDraw();
		
		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
		_pg1.dispose();
		
		
		
		//labels
		_p.rectMode(_p.CENTER);
		_p.fill(255);
		//		_p.stroke(0);
		//		_p.strokeWeight(0.002f*_p.height);
		_p.noStroke();
		float rectY = y+_pg1.height/2-0.04f*_pg1.height;
		float rectH = 0.1f*_pg1.height;

		_p.rect(x, rectY, _pg1.width, rectH);
		_p.fill(0);
		_p.textFont(_p.boldFont);
		_p.textSize(textSize);
		//_p.textSize(32);
		_p.text("School:", x-0.87f*_pg1.width/2, rectY+0.2f*rectH);
		_p.text("Class:", x-0.25f*_pg1.width/2, rectY+0.2f*rectH);
		_p.text("Group:", x+0.35f*_pg1.width/2, rectY+0.2f*rectH);

		_p.fill(_p.last5MinColor);
		_p.textFont(_p.normalFont);
		_p.textSize(textSize);
		_p.text(_school, x-0.65f*_pg1.width/2, rectY+0.2f*rectH);
		_p.text(_class, x-0.05f*_pg1.width/2, rectY+0.2f*rectH);
		_p.text(_group, x+0.6f*_pg1.width/2, rectY+0.2f*rectH);
		
		if(checkTime(2500)){
			scrollUP();
		}
		
		//_p.rect(0, 0, _, d);
	}

	protected boolean checkTime(int time){
		int now = _p.millis();	
		if(now - _lastChanged > time){
			_lastChanged = now;
			return true;
		}
		return false;
	}
}
