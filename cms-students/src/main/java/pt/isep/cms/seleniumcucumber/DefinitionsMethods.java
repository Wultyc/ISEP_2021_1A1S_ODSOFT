package pt.isep.cms.seleniumcucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DefinitionsMethods {



    public boolean find(List<WebElement> list, String text) {
        for (WebElement l : list) {
            //System.out.println(l.getText());
            if (l.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void delete(List<WebElement> list, String name) {
        WebElement checkBox = this.findCheckBox(list, name);
        checkBox.click();

    }

    private WebElement findCheckBox(List<WebElement> list, String text) {
        for (WebElement element : list) {
            List<WebElement> td = element.findElements(By.tagName("td"));
            for (int i = 0; i < td.size(); i++) {
                WebElement el = td.get(i);
                if (el.getText().contains(text)) {
                    WebElement cb = element.findElements(By.tagName("td")).get(i - 1)
                            .findElement(By.tagName("span"))
                            .findElement(By.tagName("input"));
                    return cb;
                }
            }

        }
        return null;
    }

    public boolean edit(List<WebElement> list, String name) {
        for (WebElement element : list) {

            List<WebElement> td = element.findElements(By.tagName("td"));
            for (WebElement checkBox : td) {
                if (checkBox.getText().contains(name)) {
                    checkBox.click();
                    return true;
                }

            }
        }
        return false;
    }

    public void printList(List<WebElement> list){
        int count = 0;
        for (WebElement element : list) {
            count++;
            System.out.println("\nElemento nº" + count + " ----> " + element.getText());
        }
    }
}
