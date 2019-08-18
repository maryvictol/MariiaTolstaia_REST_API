import org.testng.annotations.DataProvider;

import static service.enums.ErrorsCodes.ERROR_UNKNOWN_WORD;

public class DataProviders {

    @DataProvider(name = "checkMultipleResponse")
    public Object[][] checkMultipleResponse() {
        return new Object[][]{
                {"blau sky", "blau"},
                {"neew waard", "neew", "waard"},
                {"столеца россии мaсква", "столеца", "мaсква"},
                {"moren lakely something leke this", "moren", "lakely", "leke"},
        };
    }

    @DataProvider(name = "checkMultipleResponseForMultilineText")
    public Object[][] checkMultipleResponseForMultilineText() {
        return new Object[][]{
                {new String[] {"blau sky","sunn shine"}, "blau","sunn shine"},
                {new String[] {"neew","waaard"}, "neew", "waaard"},
                {new String[] {"столеца россии","мaсква"}, "столеца", "мaсква"},
                {new String[] {"moren likely","something leke this"}, "moren", "leke"},
        };
    }

    @DataProvider(name = "checkIgnoreDigits")
    public Object[][] checkIgnoreDigits() {
        return new Object[][]{
                {"reque1sting system", "reque1sting", 0, ERROR_UNKNOWN_WORD.getCode()},
                {"system reque1st", "reque1st", 7, ERROR_UNKNOWN_WORD.getCode()},
                {"123requesting system", "123requesting", 0, ERROR_UNKNOWN_WORD.getCode()},
                {"requesting system123", "system123", 11, ERROR_UNKNOWN_WORD.getCode()},
        };
    }
}
