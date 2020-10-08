package com.github.rshindo.kuromoji;

import java.util.Objects;

public class Input {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Input{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Input input = (Input) o;
        return Objects.equals(text, input.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
