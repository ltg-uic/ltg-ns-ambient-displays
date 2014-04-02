package ltg.ns;

import java.util.ArrayList;

import processing.core.PVector;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.ScoreBoard;
import ltg.ns.objects.Screen;

public class ScoreGrid extends Screen {
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<ScoreBoard> _scoreBoards;

	public ScoreGrid() {
		super();
	}

	public ScoreGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initScoreBoards();
		sendInitRequest();
	}	

	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("images_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}

	private void initScoreBoards(){
		_scoreBoards = new ArrayList<ScoreBoard>();
		for(int i = 0; i < _numCols*_numRows; i++){
			ScoreBoard s = new ScoreBoard(_p, 10);
			s.setDimensions(_widthContent, _heightContent);
			_scoreBoards.add(s);
		}
	}

	public void display(){

		super.display();

		if(isActive() && !isLoading()){

			_p.background(255);

			for(int i=0; i<_scoreBoards.size(); i++){

				int currentRow = _p.floor(i / _numCols);
				int currentCol = i % _numCols;
				PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
				_scoreBoards.get(i).display(loc.x, loc.y);


			}
			
		}
	}

	public void setGridParameters(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/_numRows;
		_startX = _xSpace/2;
		_startY = _ySpace/2;
		_widthContent = (_p.width - _xSpace*(_numCols-1) - _startX*2)*200f;
		_heightContent = (_p.height - _ySpace*(_numRows-1) - _startY*2)*125f;
	}
}
