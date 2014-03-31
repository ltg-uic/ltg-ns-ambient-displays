package ltg.ns;


import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
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
		_wordle.setDimensions(_p.width, _p.height);
	}

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_wordle.display(_p.width/2, _p.height/2);
			if(checkTime(5000)){
				_wordle.changeWordle();
			}
		}
	}

}
