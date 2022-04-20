package com.asteroster;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class JunitParameterizedTests {

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browserSize = "1920x1080";
    }
    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {
            "ФУТБОЛ",
            "Хоккей"
    })
    @Disabled
    @ParameterizedTest(name = "Поверка наличия в меню вид спорта {0}")
    void champJunitTests(String testData) {
        Selenide.open("https://www.championat.com/");
        $(".header-top__wrapper").shouldHave(Condition.text(testData));
    }


    @CsvSource(value = {
            "ЦСКА, ПФК ЦСКА",
            "зен, ФК ЗЕНИТ"
    })

    @ParameterizedTest (name = "При поиске слова {0}, должен найтись текст {1}")
    void champInsideTest (String testData, String expectedResult) {
        Selenide.open("https://www.championat.com/");
        $(".search-top__btn").click();
        $(".search-top__input").setValue(testData);
        $(".search-top__result").shouldHave(Condition.text(expectedResult));
    }
}
