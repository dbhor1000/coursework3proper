package com.example.coursework3proper.controllers;

import com.example.coursework3proper.model.Colors;
import com.example.coursework3proper.model.Sizes;
import com.example.coursework3proper.model.Socks;
import com.example.coursework3proper.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
@Tag(name = "Носки", description = "Контроллер для запросов к сервису носков")
public class SocksController {

    private SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "удалось добавить приход;"
            ),
            @ApiResponse(
                    responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат;"
            ),
            @ApiResponse(
                    responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны."
            )
    }
    )
    @PostMapping("/addSocks")
    @Operation(
            summary = "Добавление носков",
            description = "Добавление носков в базу данных"
    )
    public ResponseEntity<Socks> addSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        Socks addedSocks = socksService.addSocks(color, size, fabricContent, typeAmount);
        return ResponseEntity.ok(addedSocks);

    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа;"
            ),
            @ApiResponse(
                    responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат;"
            ),
            @ApiResponse(
                    responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны."
            )
    }
    )
    @GetMapping("/getAmountOfSocks")
    @Operation(
            summary = "Получение количества носков",
            description = "Получение количества носков, соответствующих указанным параметрам"
    )
    public ResponseEntity<Integer> getAmountOfSocks(Colors color, Sizes size, int cottonMin, int cottonMax) {

        int amountOfSocks = socksService.amountOfSocks(color, size, cottonMin, cottonMax);

        if (ObjectUtils.isEmpty(amountOfSocks)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(amountOfSocks);
    }

    //@GetMapping("/2")
    //@Operation(
    //        summary = "Получение количества носков(2)",
    //        description = "Получение количества носков, соответствующих указанным параметрам"
    //)
    //public ResponseEntity<Integer> getAmountOfSocks2(Colors color, Sizes size, int fabricContent) {
    //
    //    int amountOfSocks = socksService.amountOfSocks2(color, size, fabricContent);
    //
    //    if (ObjectUtils.isEmpty(amountOfSocks)) {
    //        return ResponseEntity.notFound().build();
    //    }
    //
    //    return ResponseEntity.ok(amountOfSocks);
    //}

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "удалось произвести отпуск носков со склада;"
            ),
            @ApiResponse(
                    responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат;"
            ),
            @ApiResponse(
                    responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны."
            )
    }
    )
    @PutMapping("/sellSocks")
    @Operation(
            summary = "Продажа носков",
            description = "Продажа носков со склада"
    )
    public ResponseEntity<Boolean> sellSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        Boolean soldSocks = socksService.sellSocks(color, size, fabricContent, typeAmount);
        return ResponseEntity.ok(soldSocks);

    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "запрос выполнен, товар списан со склада;"
            ),
            @ApiResponse(
                    responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат;"
            ),
            @ApiResponse(
                    responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны."
            )
    }
    )
    @DeleteMapping("/deleteSocks")
    @Operation(
            summary = "Выбросить носки",
            description = "Выбросить бракованные носки со склада"
    )
    public ResponseEntity<Boolean> deleteSocks(Colors color, Sizes size, int fabricContent, int typeAmount) {

        Boolean deletedSocks = socksService.deleteSocks(color, size, fabricContent, typeAmount);
        return ResponseEntity.ok(deletedSocks);

    }
}
