package ltg.ns;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
import ltg.ns.update.Updater;
import processing.core.PGraphics;
import wordcram.*;

public class WordleFull extends Screen{
	protected WordCram _wordles;
	protected Wordle _wordle;


	public WordleFull(AmbientVizMain p, String className, String bannerURL) {
		super(p);
		_className = className;
		_name = "wordle_full_class_"+className;
		setShapeBanner(bannerURL);
		_wordle = new Wordle(p);
		_wordle.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels-0.05f*_p.height);
	}

	public void setActive(boolean active){	
		super.setActive(active);
		if(active){
			_wordle.reload();
			System.out.println("setting active wordle");
		}
	}

	public void display(){	
		super.display();
		if(isActive() && !isLoading()){
			_wordle.display(_p.width/2, _p.height/2);
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
			}
		}
	}

	public void update(String eSchool, String eClass, String eGroup, String eWordle){
		_wordle.update(eSchool, eClass, eGroup, eWordle);
	}

	public void update(String eSchool, String eClass, String eWordle) {
		_wordle.update(eSchool, eClass, null, eWordle);
	}

}
