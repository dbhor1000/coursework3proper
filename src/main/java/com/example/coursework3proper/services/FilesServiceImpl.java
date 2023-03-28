package com.example.coursework3proper.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("src/main/resources")
    private String dataFilesPath;
    @Value("socks.json")
    private String socksFileName;

    @Override
    public boolean saveSocksToFile(String json) {

        try {
            Files.writeString(Path.of(dataFilesPath, socksFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Лишний метод???
    @Override
    public String readSocksFromFile() {

        try {
            return Files.readString(Path.of(dataFilesPath, socksFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}