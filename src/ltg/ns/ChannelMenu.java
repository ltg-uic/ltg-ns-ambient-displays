package ltg.ns;


import java.util.ArrayList;
import java.util.Set;

import ltg.ns.objects.ChannelIcon;
import ltg.ns.objects.Screen;
import de.looksgood.ani.Ani;
import processing.core.PVector;

public class ChannelMenu extends Screen{
	protected int _currentIndex, _numCols, _numOfChannels;
	protected float _startX, _startY, _xSpace, _ySpace;
	protected ArrayList<ChannelIcon> _icons;
	
	public ChannelMenu(AmbientVizMain p, int numOfChannels, int numOfColumns) {
		super(p);
		_icons = new ArrayList<ChannelIcon>();
		for(int i = 0; i<numOfChannels; i++){
			_icons.add(new ChannelIcon(_p, 0, 0, _p.width/6, _p.width/6, i));
		}
		_numCols = numOfColumns;
		_active = true;
		setGridParametets();
	}
	
	@Override
	public void setActive(boolean active){
		_active = active;
	}

	public void display(){
		if(isActive()){
			_p.fill(_p.bgColor);
			_p.rectMode(_p.CORNER);
			_p.rect(0, 0, _p.displayWidth, _p.displayHeight);

			for(int i=0; i<_icons.size(); i++){
				int currentRow = _p.floor(i / _numCols);
				int currentCol = i % _numCols;
				
				_icons.get(i).loc(new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY));
				_icons.get(i).display();
			}
		}
	}
	
	
	@Override
	public void mouseClicked(float x, float y){
		float _x = x;
		float _y = y;
		int channel = -1;
		for(int i=0; i < _icons.size(); i++){
			int a = _icons.get(i).mouseClicked(_x, _y);
			if(a != -1){
				channel = a;
				break;
			}
		}
		if(channel != -1){
			_p.channelSelected(channel);
		}
	}
	
	private void setGridParametets(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/(_icons.size()/_numCols);
		_startX = _xSpace/2;
		_startY = _ySpace/2;
	}
}
