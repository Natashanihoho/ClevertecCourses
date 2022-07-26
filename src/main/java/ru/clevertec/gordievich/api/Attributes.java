package ru.clevertec.gordievich.api;

public enum Attributes {

    ID,
    VALUE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
