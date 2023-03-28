package com.example.coursework3proper.services;

public interface FilesService {
    boolean saveSocksToFile(String json);

    String readSocksFromFile();
}
