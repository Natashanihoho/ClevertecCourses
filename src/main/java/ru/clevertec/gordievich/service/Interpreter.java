package ru.clevertec.gordievich.service;

import ru.clevertec.gordievich.exceptions.InvalidDataFormat;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;

public interface Interpreter {

    String interpret(String[] args) throws UnknownIdException, NotEnoughProductsException, InvalidDataFormat;
}
