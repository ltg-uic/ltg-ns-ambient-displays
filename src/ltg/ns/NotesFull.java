package ltg.ns;


import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;

public class NotesFull extends Screen{

	protected Note _note;

	public NotesFull(){
		super();
	}

	public NotesFull(AmbientVizMain p) {
		super(p);
		_note = new Note(_p);
		_note.setDimensions(_width, _height);
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_note.display(_x, _y);
			if(checkTime(3000)){
				_note.changeNote();
			}
		}
	}
}
