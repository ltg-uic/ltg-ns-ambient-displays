package ltg.ns;


import java.util.ArrayList;

import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
import processing.core.PVector;
import wordcram.Colorers;
import wordcram.WordCram;
import ltg.ns.objects.*;

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

	private void initWordles(){
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
				if(checkTime(10000)){
					_wordles.get((int)_p.random(0, _wordles.size())).changeWordle();
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
