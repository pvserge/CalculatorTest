//******************************************************************************
//
//
/*
Copyright (c) 2016 Appium Committers. All rights reserved.
 
Licensed to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
//
//******************************************************************************

import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
//import io.appium.java_client.windows.WindowsDriver;

public class CalculatorTest {

    private static RemoteWebDriver CalculatorSession = null;
    private static WebElement CalculatorResult = null;
    private static String TestApp = null;
    private static String KeyGroup = null;
    private static String DisplayGroup = null;
    private static WebElement ButtonClear = null;
    private static WebElement ButtonOne = null;
    private static WebElement ButtonSeven = null;
    private static WebElement ButtonEight = null;
    private static WebElement ButtonNine = null;
    private static WebElement ButtonPlus = null;
    private static WebElement ButtonEquals = null;
    private static WebElement ButtonMultiply = null;
    private static WebElement ButtonDivide = null;
    private static WebElement ButtonMinus = null;


    @BeforeClass
    public static void setup() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            String osName = System.getProperty("os.name");
            System.out.println(osName);
            if(osName.contains("Windows"))
            {
                capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
                capabilities.setCapability("platformName", "Windows");
                capabilities.setCapability("deviceName", "WindowsPC");
                CalculatorSession = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


                TestApp = "/Window[@Name='Calculator']/Window[0]";
                KeyGroup = "/Window[1]";
                DisplayGroup = "/AXGroup[0]";
                
                ButtonClear = CalculatorSession.findElementByName("Clear");          
                ButtonOne = CalculatorSession.findElementByName("One");
                ButtonSeven = CalculatorSession.findElementByName("Seven");
                ButtonEight = CalculatorSession.findElementByName("Eight");
                ButtonNine = CalculatorSession.findElementByName("Nine");
                ButtonPlus = CalculatorSession.findElementByName("Plus");
                ButtonEquals = CalculatorSession.findElementByName("Equals");
                ButtonMultiply = CalculatorSession.findElementByName("Multiply by");
                ButtonDivide = CalculatorSession.findElementByName("Divide by");
                ButtonMinus = CalculatorSession.findElementByName("Minus");
                CalculatorResult = CalculatorSession.findElementByName("Display is 0");
            }
            
            if (osName.contains("Mac"))
            {
                capabilities.setCapability("app", "Calculator");
                capabilities.setCapability("platformName", "Mac");
                capabilities.setCapability("deviceName", "Mac");
                CalculatorSession = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                
                TestApp = "/AXApplication[@AXTitle='Calculator']/AXWindow[0]";
                KeyGroup = "/AXGroup[1]";
                DisplayGroup = "/AXGroup[0]";
                
                ButtonClear = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='clear']");
                ButtonOne = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='one']");
                ButtonSeven = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='seven']");
                ButtonEight = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='eight']");
                ButtonNine = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='nine']");
                ButtonPlus = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='add']");
                ButtonEquals = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='equals']");
                ButtonMultiply = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='multiply']");
                ButtonDivide = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='divide']");
                ButtonMinus = CalculatorSession.findElementByXPath(TestApp + KeyGroup + "/AXButton[@AXDescription='subtract']");
                CalculatorResult = CalculatorSession.findElementByXPath(TestApp + DisplayGroup + "/AXStaticText[@AXDescription='main display']");
            }
          
            ButtonClear.click();
            ButtonSeven.click();

            Assert.assertNotNull(CalculatorResult);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
        }
    }

    @Before
    public void Clear()
    {
        ButtonClear.click();
        Assert.assertTrue(CalculatorResult.getText().contains("0"));
    }

    @AfterClass
    public static void TearDown()
    {
        CalculatorResult = null;
        if (CalculatorSession != null) {
            CalculatorSession.quit();
        }
        CalculatorSession = null;
    }

    @Test
    public void Addition()
    {
        ButtonOne.click();
        ButtonPlus.click();
        ButtonSeven.click();
        ButtonEquals.click();
        Assert.assertTrue(CalculatorResult.getText().contains("8"));
    }

    @Test
    public void Combination()
    {
        ButtonSeven.click();
        ButtonMultiply.click();
        ButtonNine.click();
        ButtonPlus.click();
        ButtonOne.click();
        ButtonEquals.click();
        ButtonDivide.click();
        ButtonEight.click();
        ButtonEquals.click();
        Assert.assertTrue(CalculatorResult.getText().contains("8"));
    }

    @Test
    public void Division()
    {
        ButtonEight.click();
        ButtonEight.click();
        ButtonDivide.click();
        ButtonOne.click();
        ButtonOne.click();
        ButtonEquals.click();
        Assert.assertTrue(CalculatorResult.getText().contains("8"));
    }

    @Test
    public void Multiplication()
    {
        ButtonNine.click();
        ButtonMultiply.click();
        ButtonNine.click();
        ButtonEquals.click();
        Assert.assertTrue(CalculatorResult.getText().contains("81"));        
    }

    @Test
    public void Subtraction()
    {
        ButtonNine.click();
        ButtonMinus.click();
        ButtonOne.click();
        ButtonEquals.click();
        Assert.assertTrue(CalculatorResult.getText().contains("8"));
    }
}