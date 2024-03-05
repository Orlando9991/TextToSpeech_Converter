/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rewdev.tts.core;
/**
 *
 * @author orlan
 */
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import javax.sound.sampled.AudioFileFormat;

public class TextToSpeech {

    private static TextToSpeech textToSpeech;
    private TextToSpeech(){}

    public static TextToSpeech getInstance(){
        if(textToSpeech == null){
            textToSpeech = new TextToSpeech();
        }
        return textToSpeech;
    }
    
    public void convertToText(String text){
        Voice voice = getVoice();
        speak(voice, text);
    }

    public void convertAndSaveAudio(String text, String Uri){
        Voice voice = getVoice();
        SingleFileAudioPlayer audioPlayer = getAudioFile(Uri);
        voice.setAudioPlayer(audioPlayer);
        speak(voice, text);
        audioPlayer.close();
    }

    private void speak(Voice voice, String text){
        if(!text.isEmpty() && voice != null){
            voice.allocate();
            voice.speak(text);
            voice.deallocate();
        }
    }

    private Voice getVoice(){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice[] voices = VoiceManager.getInstance().getVoices();
        if(voices.length>0){
            return VoiceManager.getInstance().getVoice(voices[0].getName());
        }
        throw new RuntimeException("Not found");
    }

    private SingleFileAudioPlayer getAudioFile(String outputUri){
        SingleFileAudioPlayer outputAudio = new SingleFileAudioPlayer(outputUri, AudioFileFormat.Type.WAVE);
        return outputAudio;
    }
    
}
