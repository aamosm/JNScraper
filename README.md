# JNScraper




## Usage

I made JNScraper to brute log into multiple accounts with the help of a present pattern and amend the results to an output.txt file.

It uses Selenium to scrape the required data from the refreshed page.




### Download ChromeDriver Version 104:

- Open a terminal or command prompt.
- Run the following command to download ChromeDriver version 104:
curl -O https://chromedriver.storage.googleapis.com/104.0.5258.89/chromedriver_win32.zip


### Download Chrome Version 104 Installer:

- Download the Chrome version 104 installer from the official Chrome archive.
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
