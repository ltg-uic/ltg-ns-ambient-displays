package ltg.ns;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
import ltg.ns.update.Updater;
import processing.core.PGraphics;
import wordcram.*;

public class WordleFull extends Screen{
	protected WordCram _wordles;
	protected Wordle _wordle;


	public WordleFull(AmbientVizMain p){
		super(p);
		_wordle = new Wordle(p);
		_active = false;
		_loading = false;
		_wordle.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
		//sendInitRequest();
	}
	
	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("wordle_full_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}
	
	public void update(String s){
		_wordle.update(s);
	}

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_wordle.display(_p.width/2, _p.height/2);
		}
	}

}
