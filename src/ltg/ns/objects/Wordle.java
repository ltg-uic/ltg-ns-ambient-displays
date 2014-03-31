package ltg.ns.objects;

import processing.core.PGraphics;
import wordcram.Colorers;
import wordcram.WordCram;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class Wordle {

	WordCram _wordle;
	int _width, _height;
	String _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	boolean _loadingWordle;


	public Wordle(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_width = 50;
		_height = 50;
		_pg1 = _p.createGraphics(_width, _height);

		_wordle = new WordCram(_p).fromWebPage("http://wordcram.org")
				.withColorer(Colorers.twoHuesRandomSatsOnWhite(_p)).toCanvas(_pg1)
				.sizedByWeight(7, 100);
		_loadingWordle = false;
		
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
		_pg1.beginDraw();
		_pg1.background(_p.bgColor);
		_pg1.endDraw();
		_loadingWordle = true;
	}

	public void changeWordle(){
		_loadingWordle = true;
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		Ani.to(this, 2f, "_gTint", 255);
		_wordle = new WordCram(_p).fromWebPage("http://wordcram.org")
				.withColorer(Colorers.twoHuesRandomSatsOnWhite(_p)).toCanvas(_pg1)
				.sizedByWeight(7, 100);
		_loadingWordle = false;
	}

	public void display(float x, float y) {		
		_pg1.beginDraw();
		if(_loadingWordle){
			_pg1.background(_p.bgColor);
		}
		if(_wordle.hasMore()&&!_loadingWordle){
			_wordle.drawNext();
		}
		_pg1.endDraw();

		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);

	}

}
