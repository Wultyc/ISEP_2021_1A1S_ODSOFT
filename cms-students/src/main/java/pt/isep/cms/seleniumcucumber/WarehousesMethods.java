package pt.isep.cms.seleniumcucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WarehousesMethods {



    public boolean findWarehouse(List<WebElement> list, String text) {
        for (WebElement l : list) {
            //System.out.println(l.getText());
            if (l.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void deleteWarehouse(List<WebElement> list, String name) {
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

}
