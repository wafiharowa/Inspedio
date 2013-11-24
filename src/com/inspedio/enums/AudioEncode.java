package com.inspedio.enums;

public class AudioEncode extends InsEnum{

	public static final AudioEncode WAV = new AudioEncode("audio/x-wav", 0);
	public static final AudioEncode MIDI = new AudioEncode("audio/midi", 1);
	public static final AudioEncode MP3 = new AudioEncode("audio/mpeg", 2);
	
	protected AudioEncode(String Name, int Value) {
		super(Name, Value);
	}

}
