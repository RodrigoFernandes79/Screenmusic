package br.com.example.challenge.screenMusic.service;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiAPIService {

    //https://console.cloud.google.com/vertex-ai/generative/multimodal/create/text?project=flowing-diode-427721-p4
    @Value("${api.project.id}")
    String projectId;
    @Value("${api.location}")
    String location;
    String modelName = "gemini-1.5-pro-001";
    String textPrompt = "Please provide a brief resume" +
            "with a maximum of 200 characters about the artist: ";

    public String textInputGeminiAI(String artistName) throws IOException {
        // Initialize client that will be used to send requests. This client only needs
        // to be created once, and can be reused for multiple requests.
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);

            String textWithArtist = textPrompt + artistName;

            GenerateContentResponse response = model.generateContent(textWithArtist);
            String output = ResponseHandler.getText(response);
            return output;
        }
    }

    public Integer insertYearOfAlbumInGeminiAI(String artistName, String albumName) throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);

            String textYearRelease = "responds by simply showing the year of release of" +
                    "the " + artistName + " album " + albumName +
                    " .Show only the year and nothing else. If you do not have access to real-time information, show only 0";

            GenerateContentResponse response = model.generateContent(textYearRelease);
            String output = ResponseHandler.getText(response);
            Integer outputString = Integer.parseInt(output.trim());
            return outputString;
        }
    }

    public Integer insertMinutesOfMusicInGeminiAI(String musicName, String artistName) throws IOException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);

            String textYearRelease = "How many minutes has the song " + musicName + " by " + artistName + " ? " +
                    "Show only the whole number of minutes and nothing else. If you don`t know, only type 0 and nothing else";

            GenerateContentResponse response = model.generateContent(textYearRelease);
            String output = ResponseHandler.getText(response);
            Integer outputString = Integer.parseInt(output.trim());
            return outputString;
        }
    }
}