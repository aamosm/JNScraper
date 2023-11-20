# JNScraper




## Usage

I made JNScraper to brute log into multiple accounts with the help of a present pattern and amend the results to an output.txt file.

It uses Selenium to scrape the required data from the refreshed page.




### Download ChromeDriver Version 104:

- Open a terminal or command prompt.
- Run the following command to download ChromeDriver version 104:
curl -O https://chromedriver.storage.googleapis.com/104.0.5258.89/chromedriver_win32.zip


### Download Chrome Version 104 Installer:

- Download the Chrome version 104 installer from https://www.slimjet.com/chrome/google-chrome-old-version.php.
- Uninstall any previous version of Chrome
- After downloading, install Chrome 104 on your machine.



### Stop Chrome from Updating (Windows):

- Open the Run dialog by pressing Win + R.
- Type regedit and press Enter to open the Registry Editor.
- Navigate to the following key:
HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Google\Update
- If the Update key doesn't exist, right-click on Google and choose New -> Key. Name it Update.
- Right-click on the Update key, choose New -> DWORD (32-bit) Value, and name it UpdateDefault.
- Double-click on UpdateDefault and set its value to 0.



### Change Pathways According to Your Device:

- Open the JNScraper.java file in a text editor.
- Modify the paths in the System.setProperty lines for webdriver.chrome.bin and webdriver.chrome.driver to match the locations on your device.
- Update the path in the writeToFile method for the output file.




## You will need the following 

- Selenium JAR: This includes the core Selenium libraries. Given in https://www.selenium.dev/downloads/
- WebDriver JAR: This includes the WebDriver implementation. Given in https://github.com/aamosm/JNScraper/tree/main#download-chromedriver-version-104
- or the dependencies depending on your project.




## Remove restrictions on Chrome update

- If you want to allow Chrome to update again after previously preventing it, you can follow these steps:

- Open the Run dialog by pressing Win + R.
- Type regedit and press Enter to open the Registry Editor.
- Navigate to the following key: HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Google\Update.
- If you had previously created the UpdateDefault DWORD value with a value of 0, you can either delete this DWORD value or set its value to 1.

### To delete the DWORD value:

- Right-click on UpdateDefault.
- Select "Delete."
- To set the value to 1:

- Double-click on UpdateDefault.
- Change the "Value data" to 1.
- Click "OK."
