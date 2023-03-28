package com.example.coursework3proper.services;

import com.example.coursework3proper.model.Colors;
import com.example.coursework3proper.model.Sizes;
import com.example.coursework3proper.model.Socks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SocksServiceImpl implements SocksService{

    final private FilesService filesService;

    public SocksServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }
    private static Map<Integer, Socks> socksMap = new TreeMap<>();
    //private static Map<Socks, Integer> socksMapAlt = new HashMap<>();
    private static int socksId = 0;

    @Override
    public Socks addSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        //Socks socks = new Socks(color, size, fabricContent);
        Socks addedSocks = new Socks(color, size, fabricContent, typeAmount, "Приход товара на склад.");
        socksMap.putIfAbsent(socksId++, addedSocks);
        //if (socksMapAlt.get(socks) == null) {
        //    socksMapAlt.put(socks, typeAmount);
        //} else {
        //    int oldAmount = socksMapAlt.get(socks);
        //    socksMapAlt.put(socks, (typeAmount + oldAmount));
        //}
        saveSocksToFile();
        return addedSocks;

    }

    @Override
    public boolean sellSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {
        int totalSocksLeft = 0;

        for(int i = 0; i < socksMap.size(); i++) {
            if(socksMap.get(i).getColor() == color && socksMap.get(i).getSize() == size && socksMap.get(i).getFabricContent() == fabricContent) {

                totalSocksLeft = totalSocksLeft + socksMap.get(i).getTypeAmount();
            }
        }

        if (typeAmount <= totalSocksLeft) {
            Socks soldSocks = new Socks(color, size, fabricContent, typeAmount, "Отпуск носков со склада.");
            socksMap.putIfAbsent(socksId++, soldSocks);
            saveSocksToFile();

            return true;

        }
        return false;
    }

    @Override
    public boolean deleteSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {
        int totalSocksLeft = 0;

        for(int i = 0; i < socksMap.size(); i++) {
            if(socksMap.get(i).getColor() == color && socksMap.get(i).getSize() == size && socksMap.get(i).getFabricContent() == fabricContent) {

                totalSocksLeft = totalSocksLeft + socksMap.get(i).getTypeAmount();
            }
        }

        if (typeAmount <= totalSocksLeft) {
            Socks wasteSocks = new Socks(color, size, fabricContent, typeAmount, "Удаление бракованных носков.");
            socksMap.putIfAbsent(socksId++, wasteSocks);
            saveSocksToFile();

            return true;

        }
        return false;
    }

    @Override
    public Integer amountOfSocks(Colors color, Sizes size, int cottonMin, int cottonMax){

        readSocksFromFile();
        int amountOfSocks = 0;

        for(int i = 0; i < socksMap.size(); i++){

            if(socksMap.get(i).getColor() == color && socksMap.get(i).getSize() == size && socksMap.get(i).getFabricContent() >= cottonMin && socksMap.get(i).getFabricContent() <= cottonMax){
                amountOfSocks = amountOfSocks + socksMap.get(i).getTypeAmount();
            }
        }

        return amountOfSocks;
    }

    //@Override
    //public Integer amountOfSocks2(Colors color, Sizes size, int fabricContent){
    //
    //    Socks socks = new Socks(color, size, fabricContent);
    //    int amountOfSocks = socksMapAlt.get(socks);
    //
    //    return amountOfSocks;
    //}

    private void saveSocksToFile() {

        try {
            String json = new ObjectMapper().writeValueAsString(socksMap);
            filesService.saveSocksToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Лишние методы???

    @PostConstruct
    private void init() {
        readSocksFromFile();
        socksId = socksMap.size();
    }

    private void readSocksFromFile() {

        try {
            String json = filesService.readSocksFromFile();
            socksMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Socks>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
