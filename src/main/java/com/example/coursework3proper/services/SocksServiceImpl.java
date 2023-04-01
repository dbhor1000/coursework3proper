package com.example.coursework3proper.services;


import com.example.coursework3proper.model.Colors;
import com.example.coursework3proper.model.Sizes;
import com.example.coursework3proper.model.Socks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SocksServiceImpl implements SocksService {

    final private FilesService filesService;

    public SocksServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }
    public static Map<Socks, Integer> socksMapAlt = new HashMap<>();

    private static int socksId = 0;

    @Override
    public Socks addSocks(Colors color, Sizes size, int fabricContent, int amount) {

        Socks addedSocks = new Socks(color, size, fabricContent);
        if (!socksMapAlt.containsKey(addedSocks) && socksMapAlt.get(addedSocks) == null) {
            socksMapAlt.put(addedSocks, amount);
        } else {
            int currentAmount = socksMapAlt.get(addedSocks);
            socksMapAlt.replace(addedSocks, currentAmount + amount);
        }
        saveSocksToFile();
        return addedSocks;

    }

    @Override
    public boolean sellSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        if (socksMapAlt.get(new Socks(color, size, fabricContent)) >= typeAmount){
            socksMapAlt.replace(new Socks(color, size, fabricContent), socksMapAlt.get(new Socks(color, size, fabricContent)) - typeAmount);
            saveSocksToFile();
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean deleteSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        if (socksMapAlt.get(new Socks(color, size, fabricContent)) >= typeAmount){
            socksMapAlt.replace(new Socks(color, size, fabricContent), socksMapAlt.get(new Socks(color, size, fabricContent)) - typeAmount);
            saveSocksToFile();
            return true;

        } else {
            return false;
        }
    }

    @Override
    public Integer amountOfSocks(Colors color, Sizes size, int cottonMin, int cottonMax){

        Map<Socks, Integer> collect = socksMapAlt.entrySet().stream()
                .filter(map -> map.getKey().getFabricContent() >= cottonMin & map.getKey().getFabricContent() <= cottonMax )
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        int totalAmount = 0;
        for (int value: collect.values()) {
            totalAmount += value;
        }
        return totalAmount;
    }

    private void saveSocksToFile() {

        try {
            String json = new ObjectMapper().writeValueAsString(socksMapAlt);
            filesService.saveSocksToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Метод чтения базы данных из JSON не работает.

    //@PostConstruct
    //private void init() {
    //    readSocksFromFile();
    //}
    //
    //private void readSocksFromFile() {
    //
    //    try {
    //        String json = filesService.readSocksFromFile();
    //
    //
    //        JSONParser parser = new JSONParser();
    //        Object obj = parser.parse(json);
    //        Map map = (Map)obj;
    //
    //        socksMapAlt = map;
    //
    //    } catch (ParseException e) {
    //        e.printStackTrace();
    //        throw new RuntimeException(e);
    //    }
    //}

}
