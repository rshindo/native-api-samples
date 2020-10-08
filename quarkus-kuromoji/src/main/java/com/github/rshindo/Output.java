package com.github.rshindo;

import java.util.List;
import java.util.Objects;

public class Output {

    private List<String> tokens;

    public Output(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Output{" +
                "tokens=" + tokens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Output output = (Output) o;
        return Objects.equals(tokens, output.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }
}
