package com.hexacode.themes;

import com.formdev.flatlaf.FlatDarkLaf;

public class Hexacode extends FlatDarkLaf {
    public static boolean setup() {
        return setup(new Hexacode());
    }

    @Override
    public String getName() {
        return "Hexacode";
    }
}
