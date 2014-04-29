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

	public WordleGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns, String bannerURL) {
		super(p);
		setShapeBanner(bannerURL);
		_className = className;
		_name = "wordle_grid_class_"+className;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initWordles();
	}	
	
	public void setActive(boolean active){	
		super.setActive(active);
		if(active){
			for(int i=0; i<_wordles.size(); i++){
				_wordles.get(i).reload();
			}
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
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
			}
		}
	}
	
	public void update(String eSchool, String eClass, String eGroup, String eWordle, int i) {
		_wordles.get(i).update(eSchool, eClass, eGroup, eWordle);
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
