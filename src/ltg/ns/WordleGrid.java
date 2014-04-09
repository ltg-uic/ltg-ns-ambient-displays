package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
import processing.core.PVector;


public class WordleGrid extends Screen {
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<Wordle> _wordles;

	public WordleGrid() {
		super();
	}

	public WordleGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initWordles();
	}	
	
	public WordleGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns) {
		super(p);
		_className = className;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initWordles();
	}	
	
	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("wordle_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}
	
	public void sendUpdateRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("wordle_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}

	
	
	public void initWordles(){
		_wordles = new ArrayList<Wordle>();
		for(int i = 0; i < _numCols*_numRows; i++){
			Wordle w = new Wordle(_p);
			w.setDimensions(_widthContent, _heightContent);
			_wordles.add(w);
		}
	}

	public void display(){
		
		super.display();
		
		if(isActive() && !isLoading()){				
				for(int i=0; i<_wordles.size(); i++){
					int currentRow = _p.floor(i / _numCols);
					int currentCol = i % _numCols;
					PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
					_wordles.get(i).display(loc.x, loc.y);;
				}
				if(checkTime(_p.updateInterval)){
					sendUpdateRequest();
				}
		}
	}
	
	public void update(String s, int i) {
		_wordles.get(i).update(s);
	}


	public void setGridParameters(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/_numRows;
		
		_startX = _xSpace/2;
		_startY = _ySpace/2;
		
		_widthContent = (_p.proportionWidth*_p.width);
		_heightContent = (_p.proportionHeight*_p.width);
	}
}
