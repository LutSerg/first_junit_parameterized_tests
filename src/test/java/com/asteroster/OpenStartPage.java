package com.asteroster;

import com.codeborne.selenide.Selenide;

public class OpenStartPage {
    public OpenStartPage openStartPage () {
        Selenide.open("https://www.championat.com/");
        return this;
    }
}
