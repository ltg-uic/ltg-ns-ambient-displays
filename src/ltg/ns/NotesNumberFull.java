package ltg.ns;


import ltg.ns.objects.NoteCount;
import ltg.ns.objects.Screen;

public class NotesNumberFull extends Screen {

	protected NoteCount _noteCount;

	public NotesNumberFull(){
		super();
	}

	public NotesNumberFull(AmbientVizMain p) {
		super(p);
		_noteCount = new NoteCount(_p);
		_noteCount.setDimensions(_width, _height);
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_noteCount.display(_x, _y);
			if(checkTime(8000)){
				_noteCount.changeNoteCount((int)_p.random(50));
			}
		}
	}
}
