package ru.clevertec.gordievich.web;

public enum Attributes {

    ID,
    VALUE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
