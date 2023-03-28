package com.example.coursework3proper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {

    private Colors color;
    private Sizes size;
    private int fabricContent;
    private int typeAmount;
    private String comment;

    public Socks(Colors color, Sizes size, int fabricContent) {
        this.color = color;
        this.size = size;
        this.fabricContent = fabricContent;
        this.typeAmount = 0;
        this.comment = "";
    }

    public Socks(Colors color, Sizes size, int fabricContent, int typeAmount) {
        this.color = color;
        this.size = size;
        this.fabricContent = fabricContent;
        this.typeAmount = typeAmount;
        this.comment = "";
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public int getFabricContent() {
        return fabricContent;
    }

    public void setFabricContent(int fabricContent) {
        this.fabricContent = fabricContent;
    }

    public int getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(int typeAmount) {
        this.typeAmount = typeAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return fabricContent == socks.fabricContent && typeAmount == socks.typeAmount && color == socks.color && size == socks.size;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, fabricContent, typeAmount);
    }
}
