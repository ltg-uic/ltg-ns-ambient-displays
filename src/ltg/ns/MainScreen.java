package ltg.ns;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import ltg.ns.objects.Screen;

public class MainScreen extends Screen {

	PShape _titleSVG;
	ChannelMenu _menuOur;
	ChannelMenu _menuAll;
	ArrayList<String> _iconsOur, _iconsAll;
	
	MainScreen(AmbientVizMain p){
		super(p);
		_iconsOur = new ArrayList<String>();
		_iconsOur.add("Menu_Selector_notes_s.svg");
		_iconsOur.add("Menu_Selector_no_notes_s.svg");
//		_iconsOur.add("Menu_Selector_tags_s.svg");
//		_iconsOur.add("Menu_Selector_pictures_s.svg");
		_iconsOur.add("Menu_Selector_wordle_s.svg");
		_iconsOur.add("Menu_Selector_notes_p.svg");
		_iconsOur.add("Menu_Selector_no_notes_p.svg");
//		_iconsOur.add("Menu_Selector_tags_p.svg");
//		_iconsOur.add("Menu_Selector_pictures_p.svg");
		_iconsOur.add("Menu_Selector_wordle_p.svg");
		
		_iconsAll = new ArrayList<String>();
		_iconsAll.add("Menu_Selector_notes_s_all.svg");
		_iconsAll.add("Menu_Selector_no_notes_s_all.svg");
//		_iconsAll.add("Menu_Selector_tags_s_all.svg");
//		_iconsAll.add("Menu_Selector_pictures_s_all.svg");
		_iconsAll.add("Menu_Selector_wordle_s_all.svg");
		_iconsAll.add("Menu_Selector_notes_p_all.svg");
		_iconsAll.add("Menu_Selector_no_notes_p_all.svg");
//		_iconsAll.add("Menu_Selector_tags_p_all.svg");
//		_iconsAll.add("Menu_Selector_pictures_p_all.svg");
		_iconsAll.add("Menu_Selector_wordle_p_all.svg");
//		_iconsAll.add("Menu_Selector_notes_s.svg");
//		_iconsAll.add("Menu_Selector_no_notes_s.svg");
//		_iconsAll.add("Menu_Selector_tags_s.svg");
//		_iconsAll.add("Menu_Selector_pictures_s.svg");
//		_iconsAll.add("Menu_Selector_wordle_s.svg");
//		_iconsAll.add("Menu_Selector_notes_p.svg");
//		_iconsAll.add("Menu_Selector_no_notes_p.svg");
//		_iconsAll.add("Menu_Selector_tags_p.svg");
//		_iconsAll.add("Menu_Selector_pictures_p.svg");
//		_iconsAll.add("Menu_Selector_wordle_p.svg");

		
		_titleSVG = _p.loadShape("Menu_Selector_header.svg");
		
		_menuOur = new ChannelMenu(_p, 3, (int)(0.0f*_p.width), (int)(_p.height*0.01f), _iconsOur, 0, "selector_our_class.svg");
		_menuOur.setDimensions((int)(_p.width*1f), (int)(_p.height/2.3));
		
		_menuAll = new ChannelMenu(_p, 3, (int)(0.0f*_p.width), (int)( _p.height/2.2), _iconsAll, 6, "selector_all_classes.svg");
		_menuAll.setDimensions((int)(_p.width*1f), (int)(_p.height/2.3));
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
			_p.background(240);
			_p.shapeMode(_p.CORNER);
			_p.shape(_titleSVG, 0, 0, _p.displayWidth, 0.15f*_p.displayHeight);
	
			_menuOur.display();
			_menuAll.display();	
		}
	}	
}
