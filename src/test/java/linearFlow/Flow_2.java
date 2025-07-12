// 2. LIST OF LANGUAGE -> AND TOTAL NO OF EACH COURSES EACH

package linearFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flow_2 {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.coursera.org/search?query=Web%20Development");
		driver.manage().window().maximize();
		
		//For More Languages
		WebElement allLang=driver.findElement(By.cssSelector("button[aria-label='Show more Language options'] span[class='cds-button-label']"));
		allLang.click();
		
		//For ALl languages in list
		List <WebElement> lang=driver.findElements(By.xpath("//*[@class=\"css-q5d1os\"]/div"));
//		System.out.println(lang.size());
		
		//Creating HashMap for formatting and storing
		Map<String, Integer> languageMap = new HashMap<>();
		Pattern pattern = Pattern.compile("^(.*)\\((\\d+)(?:,(\\d+))?\\)$");


		for (WebElement ele: lang) {
			String tex= ele.getText();
			Matcher matcher = pattern.matcher(tex.trim());//got 3 group
			
			if (matcher.find()) {
				String name = matcher.group(1).trim();  //group 1
				String number = matcher.group(3) != null ? matcher.group(2)+matcher.group(3) : matcher.group(2);  //group 2 & 3 for numbers
				languageMap.put(name, Integer.parseInt(number));	
			}
		}
		
		//Printing eac
		for(String language: languageMap.keySet()) {
			System.out.println(language + " - " + languageMap.get(language));
		}

	}

}
