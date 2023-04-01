package com.example.coursework3proper.services;


import com.example.coursework3proper.model.Colors;
import com.example.coursework3proper.model.Sizes;
import com.example.coursework3proper.model.Socks;

public interface SocksService {

    Socks addSocks(Colors color, Sizes size, int fabricContent, int amount);
    boolean sellSocks(Colors color, Sizes size, int fabricContent, int typeAmount);
    boolean deleteSocks(Colors color, Sizes size, int fabricContent, int typeAmount);
    Integer amountOfSocks(Colors color, Sizes size, int cottonMin, int cottonMax);

}
