package com.example.coursework3proper.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Socks {

    private Colors color;
    private Sizes size;
    private int fabricContent;

    public Socks(Colors color, Sizes size, int fabricContent) {
        this.color = color;
        this.size = size;
        this.fabricContent = fabricContent;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return getFabricContent() == socks.getFabricContent() && getColor() == socks.getColor() && getSize() == socks.getSize();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getSize(), getFabricContent());
    }
}
