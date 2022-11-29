set projectLocation=C:\Users\sakha\eclipse-workspace1\SeleniumExamples
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*;
java org.testng.TestNG TestFrames.xml
pause