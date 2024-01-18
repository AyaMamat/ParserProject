package com.laba.solvd.parsers;


import com.laba.solvd.model.Person;

public interface Parser {
    Person parse(String path);
}
