package ltg.ns;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import ltg.ns.objects.Screen;

public class MainScreen extends Screen {

	PShape _titleSVG;
	ChannelMenu _menuOur;
	ChannelMenu _menuAll;
	
	MainScreen(AmbientVizMain p){
		super(p);
		
		_titleSVG = _p.loadShape("Menu_Selector_header.svg");
		
		_menuOur = new ChannelMenu(_p, 10, 5, (int)(0.075f*_p.width), (int)(_p.height*0.04f));
		_menuOur.setDimensions((int)(_p.width*0.9f), (int)(_p.height/2.2));
		
		_menuAll = new ChannelMenu(_p, 10, 5, (int)(0.075f*_p.width), (int)( _p.height/2.1));
		_menuAll.setDimensions((int)(_p.width*0.9f), (int)(_p.height/2.2));
	}
	
	@Override
	public void setActive(boolean active){
		_active = active;
	}
	
	@Override
	public void mouseClicked(float x, float y){
		float _x = x;
		float _y = y;
		_menuOur.mouseClicked(_x, _y);
		_menuAll.mouseClicked(_x, _y);
	}
	
	public void display(){
		if(isActive()){
			_p.fill(_p.bgColor);
			_p.rectMode(_p.CORNER);
			_p.rect(0, 0, _p.displayWidth, _p.displayHeight);
			_p.shapeMode(_p.CORNER);
			_p.shape(_titleSVG, 0, 0, _p.displayWidth, 0.15f*_p.displayHeight);
			_menuOur.display();
			_p.stroke(200);
			_p.strokeWeight(2);
			_p.line(0, _p.height/2+0.07f*_p.height, _p.width, _p.height/2+0.07f*_p.height);
			_menuAll.display();	
		}
	}	
}
