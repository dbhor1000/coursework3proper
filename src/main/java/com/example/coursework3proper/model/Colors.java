package com.example.coursework3proper.model;

public enum Colors {

    BLACK("Чёрный"),
    RED("Красный"),
    ORANGE("Оранжевый"),
    YELLOW("Жёлтый"),
    GREEN("Зелёный"),
    BLUE("Синий"),
    PURPLE("Фиолетовый"),
    PINK("Розовый"),
    WHITE("Белый"),
    GREY("Серый"),
    PATTERNED("Узорчатый");

    String category;

    Colors(String category) {
        this.category = category;
    }
}
