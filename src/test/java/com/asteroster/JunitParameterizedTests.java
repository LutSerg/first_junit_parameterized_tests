package com.asteroster;

import com.asteroster.siteitems.MenuItem;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.By;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class JunitParameterizedTests {
    OpenStartPage openMainPage = new OpenStartPage();
    SelenideElement selenideElementForMenu =  $(".header-top__wrapper");

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

    @ParameterizedTest(name = "Поверка наличия в меню вид спорта {0}")
    void champJunitTests(String testData) {
        openMainPage.openStartPage();
        selenideElementForMenu.shouldHave(Condition.text(testData));
    }


    @CsvSource(value = {
            "ЦСКА, ПФК ЦСКА",
            "зен, ФК ЗЕНИТ"
    })

    @ParameterizedTest (name = "При поиске слова {0}, должен найтись текст {1}")
    void champInsideTest (String testData, String expectedResult) {
        openMainPage.openStartPage();
        $(".search-top__btn").click();
        $(".search-top__input").setValue(testData);
        $(".search-top__result").shouldHave(Condition.text(expectedResult));
    }




    @EnumSource(MenuItem.class)
    @ParameterizedTest(name = "Поиск в меню видов спорта")
    void champMenuItemTest(MenuItem testData) {
        //Предусловие
        openMainPage.openStartPage();
        selenideElementForMenu.shouldHave(Condition.text(testData.sportTypeName));
    }

    static Stream<Arguments> methodSourceChampTest() {
        return Stream.of(
                Arguments.of("АВТО", "Формула-1" ),
                Arguments.of("БАСКЕТБОЛ", "НБА")
        );
    }

    @MethodSource("methodSourceChampTest")
    @ParameterizedTest
    void methodSourceChampTest(String sportType, String sportSubType) {
        openMainPage.openStartPage();
        $$(".header-menu-item").find(Condition.text(sportType))
                        .shouldBe(Condition.visible)
                        .hover();
        sleep(3000);
        $$(".header-menu-item__drop-columns").find(Condition.text(sportSubType));

    }
}
