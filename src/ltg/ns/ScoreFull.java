package ltg.ns;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ScoreBoard;
import ltg.ns.objects.ScoreLine;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;

public class ScoreFull extends Screen {
	
	
	ScoreBoard _scoreBoard;
	
	public ScoreFull(AmbientVizMain p){
		super(p);
		_scoreBoard = new ScoreBoard(_p, 10);
		_scoreBoard.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
		_active = false;
		_loading = false;
	}
	
	public ScoreFull(AmbientVizMain p, String className){
		super(p);
		_className = className;
		_scoreBoard = new ScoreBoard(_p, 10);
		_scoreBoard.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
		_active = false;
		_loading = false;
	}

	
	public void sendUpdateRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("wordle_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_scoreBoard.display(_p.width/2, _p.height/2);
//			if(checkTime(_p.updateInterval)){
//				sendUpdateRequest();
//			}
			
			
		}
	}

}
