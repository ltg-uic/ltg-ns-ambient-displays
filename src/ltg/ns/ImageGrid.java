package ltg.ns;


import java.util.ArrayList;

import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Screen;
import processing.core.PVector;

public class ImageGrid extends Screen {
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<ImageSet> _imageSets;

	public ImageGrid() {
		super();
	}

	public ImageGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initImageSets();
	}	

	private void initImageSets(){
		_imageSets = new ArrayList<ImageSet>();
		for(int i = 0; i < _numCols*_numRows; i++){
			ImageSet s = new ImageSet(_p, 4);
			s.setDimensions(_widthContent, _heightContent);
			_imageSets.add(s);
		}
	}

	public void display(){
		
		super.display();
		
		if(isActive() && !isLoading()){
			
				_p.background(255);
				
				for(int i=0; i<_imageSets.size(); i++){
					
					int currentRow = _p.floor(i / _numCols);
					int currentCol = i % _numCols;
					PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
					_imageSets.get(i).display(loc.x, loc.y);
				
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
