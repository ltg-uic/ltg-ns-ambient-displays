package ltg.ns;

import java.util.ArrayList;

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

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_scoreBoard.display(_p.width/2, _p.height/2);
			if(checkTime(3000)){
				_scoreBoard.scrollUP();
			}
		}
	}

}
