package ru.google.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class googleTest {

    // Проинициализируем хром драйвер, чтоб не прописывать его локально в каждом методе,
    // если в дальнейшем потребуется выполнить более одного теста
    public ChromeDriver driver;

    // Воспользуемся анотациями Before и After
    // в before пропишем наши пропертис, а в after выход по окончанию теста
    @Before
    public void setUp() {
        // будем тестировать в Chrome, для это подключим chromedriver указав локальный путь до экзешника
        System.setProperty("webdriver.chrome.driver", "/Users/User/Desktop/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void searchCatPictures() throws InterruptedException {
        // откроем страницу поиска google
        driver.get("http://google.ru");

        //далее нам нужно нужно ввести наш запрос, для этого нужно "достучаться" до элемента страницы, то есть до самой строки поиска.
        //в DevTools`е смотрим class или name нашего input`а, идём по наименьшему сопротивлению и ищем name="q"
        //когда получили доступ к input`у, методом sendKeys вводим наш запрос "картинки с кошками"
        driver.findElementByName("q").sendKeys("картинки с кошками");

        //снова получаем доступ к нашему input`У, и методом sendKeys устанавливаем нажатие кнопки Enter для запуска поиска
        driver.findElementByName("q").sendKeys(Keys.ENTER);
        // Проверим себя на успешность прохождения тест
        System.out.println("SEARCH RESULT: OK");

        //Проверка ожидаемого title в поисковой выдаче. Заголовок должен быть вида "Картинки по запросу кошками"
        driver.findElementsByTagName("h3").equals("Картинки по запросу кошками");
        System.out.println("CHECK TITLE: OK");

        driver.findElementByCssSelector(".q.qs").click();

        // Чтоб открыть первую картинку, получим доступ к div`у по его уникальному атрибуту по xpath
        // в нашем случае это data-ri="0"

        driver.findElement(By.xpath("//div[@data-ri=\"0\"]")).click();
        System.out.println("CHOOSE FIRST CAT PICTURE: OK");
        // с помощью Thread выставвим таймер на 5с, чтоб визуально убедиться в том, что на картинке всё таки кошка
        Thread.sleep(5000);
    }

    @After
    public void close() {
        // завершим тест закрытием браузера
        driver.quit();
    }
}
