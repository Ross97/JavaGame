package dev.ross.rossgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Launcher {
	public static void main(String[] args) throws MalformedURLException{
		
		//In game music
		URL url = new URL("https://raw.githubusercontent.com/Ross97/processingAssignment1/master/Main/data/urbanJungle.mp3"); //"http://www.edu4java.com/sound/back.wav"
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
		
		
		Game game = new Game("Finder!", 750, 750);
		game.start();
	}
}



