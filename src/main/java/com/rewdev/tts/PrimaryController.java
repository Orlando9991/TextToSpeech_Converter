package com.rewdev.tts;

import com.rewdev.tts.core.TextToSpeech;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;


public class PrimaryController {

    @FXML
    private Button convertButton;

    @FXML
    private Button convertAndSaveButton;

    @FXML
    private TextArea inputText;
    
    
    @FXML
    private void convertToSpeech(){
        String text = inputText.getText();

        if(!text.isEmpty()){
            TextToSpeech textToSpeech = TextToSpeech.getInstance();
            textToSpeech.convertToText(text);
        }
    }

    @FXML
    private void convertToSpeechAndSave(){
        String text = inputText.getText();
        String uriPath = getFileExportPath();

        if(!text.isEmpty()){
            TextToSpeech textToSpeech = TextToSpeech.getInstance();
            textToSpeech.convertAndSaveAudio(text, uriPath);
        }
    }

    public String getFileExportPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save tts audio");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.wav"));

        //Show fileDialog
        File outputFile = fileChooser.showSaveDialog(Window.getWindows().get(0));

        return outputFile.getAbsolutePath();
    }

    @FXML
    private void onInputTextArea(){
        if(inputText.getText().isEmpty()){
            convertButton.setDisable(true);
            convertAndSaveButton.setDisable(true);
        }else{
            convertButton.setDisable(false);
            convertAndSaveButton.setDisable(false);
        }
    }
  
}
