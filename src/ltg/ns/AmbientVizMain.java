package ltg.ns;

import java.util.ArrayList;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.commons.ltg_event_handler.SingleChatLTGEventHandler;
import ltg.commons.ltg_event_handler.SingleChatLTGEventListener;
import ltg.ns.objects.Screen;
import ltg.ns.update.Updater;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import de.looksgood.ani.*;

import javax.swing.*;


public class AmbientVizMain extends PApplet{

	public SingleChatLTGEventHandler eh;
	boolean xmpp = false;
	int numOfChannels = 20;
	protected String className;

	private String chatRoom = "nh-test@conference.ltg.evl.uic.edu";
	private String botUsername = "hg-beacon-test@ltg.evl.uic.edu";
	private String botPassword = "hgbeacon";

	public MainScreen mainScreen;
	public ChannelMenu menuOur, menuAll;
	public WordleFull wordleCollectiveOur;
	public WordleGrid wordleGridOur;
	public ImageFull imageFullOur;
	public ImageGrid imageGridOur;
	public NotesNumberGrid numNotesGridOur;
	public NotesNumberFull numNotesFullOur;
	public NotesFull notesFullOur;
	public NotesGrid notesGridOur;
	public ScoreFull scoreFullOur;
	public ScoreGrid scoreGridOur;

	public float proportionWidth = 0.32f;
	public float proportionHeight = 0.195f;

	public WordleFull wordleCollectiveAll;
	public WordleGrid wordleGridAll;
	public ImageFull imageFullAll;
	public ImageGrid imageGridAll;
	public NotesNumberGrid numNotesGridAll;
	public NotesNumberFull numNotesFullAll;
	public NotesFull notesFullAll;
	public NotesGrid notesGridAll;
	public ScoreFull scoreFullAll;
	public ScoreGrid scoreGridAll;

	Updater updater;

	public int bgColor = color(255, 255, 255);
	public int last5MinColor = color(0, 125, 224);

	public int gridSquares = 9;
	public PFont normalFont, boldFont;
	public int borderFullChannels;
	public int borderGridChannels;

	boolean channelOffset = false;
	int currentChannel = -1;
	ArrayList<Screen> screens = new ArrayList<Screen>(); 

	public void setup(){

		size(displayWidth, displayHeight);
		background(bgColor);
		smooth();
		Ani.init(this);
		normalFont = loadFont("HelveticaNeue-100.vlw");
		boldFont = loadFont("HelveticaNeue-Bold-100.vlw");
		updater = new Updater(this);

		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) { 
			e.printStackTrace();
		} 
		
//		while(className == null){
//			ImageIcon icon = new ImageIcon("../data/iconPane.png");
//			className = (String) JOptionPane.showInputDialog(null, "Please enter your class name", "", JOptionPane.INFORMATION_MESSAGE, icon, null, null );
//		}

		borderFullChannels = (int) (0.05f*width);
		borderGridChannels = (int) (0.05f*width/2);

		if(xmpp){
			eh = new SingleChatLTGEventHandler(botUsername, botPassword, chatRoom);
			eh.registerHandler("notes_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initNoteFull(e);
				}
			});	
			eh.registerHandler("notes_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initNoteGrid(e);
				}
			});	
			eh.registerHandler("images_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initImageFull(e);
				}
			});	
			eh.registerHandler("images_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initImageGrid(e);
				}
			});	

			eh.registerHandler("wordle_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initWordleFull(e);
				}
			});	

			eh.registerHandler("wordle_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initWordleGrid(e);
				}
			});	

			eh.runAsynchronously();
		}


		mainScreen = new MainScreen(this);

		notesFullOur = new NotesFull(this);
		numNotesFullOur = new NotesNumberFull(this);
		scoreFullOur = new ScoreFull(this);
		imageFullOur = new ImageFull(this);	
		wordleCollectiveOur = new WordleFull(this);
		notesGridOur = new NotesGrid(this, 3, 3);
		numNotesGridOur = new NotesNumberGrid(this, 3, 3);
		scoreGridOur = new ScoreGrid(this, 3, 3);
		imageGridOur = new ImageGrid(this, 3, 3);	
		wordleGridOur = new WordleGrid(this, 3, 3);

		notesFullAll = new NotesFull(this);
		numNotesFullAll = new NotesNumberFull(this);
		scoreFullAll = new ScoreFull(this);
		imageFullAll = new ImageFull(this);	
		wordleCollectiveAll = new WordleFull(this);
		notesGridAll = new NotesGrid(this, 3, 3);
		numNotesGridAll = new NotesNumberGrid(this, 3, 3);
		scoreGridAll = new ScoreGrid(this, 3, 3);
		imageGridAll = new ImageGrid(this, 3, 3);	
		wordleGridAll = new WordleGrid(this, 3, 3);

		screens.add(notesFullOur);
		screens.add(numNotesFullOur);
		screens.add(scoreFullOur);
		screens.add(imageFullOur);
		screens.add(wordleCollectiveOur);
		screens.add(notesGridOur);
		screens.add(numNotesGridOur);
		screens.add(scoreGridOur);
		screens.add(imageGridOur);
		screens.add(wordleGridOur);

		screens.add(notesFullAll);
		screens.add(numNotesFullAll);
		screens.add(scoreFullAll);
		screens.add(imageFullAll);
		screens.add(wordleCollectiveAll);
		screens.add(notesGridAll);
		screens.add(numNotesGridAll);
		screens.add(scoreGridAll);
		screens.add(imageGridAll);
		screens.add(wordleGridAll);

		///
		goToMenu();
	}

	public void draw(){
		mainScreen.display();
		for(Screen s : screens){
			s.display();
		}
	}

	public void mouseClicked(){
		if(mainScreen.isActive()){
			if(mouseY > height/2) channelOffset = true;
			mainScreen.mouseClicked(mouseX, mouseY);
		}
		else{
			goToMenu();
		}
	}

	public void channelSelected(int channel){
		if(channelOffset){
			currentChannel = channel+10;
			channelOffset = false;
		}
		else currentChannel = channel;
		goToScreen(currentChannel);
	}

	public void goToMenu(){
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		mainScreen.setActive(true);
	}

	public void goToScreen(int channel){
		mainScreen.setActive(false);
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		if(channel < screens.size()){
			screens.get(channel).setActive(true);
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "ltg.ns.AmbientVizMain" });
	}

}
