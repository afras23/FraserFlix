# Setting Up the REST API With JS Client on Eclipse:

## Pre-requisities:
1. Java Development Kit (JDK) 8 or higher
2. Eclipse IDE for Java EE Developers
3. Apache Tomcat 9 or higher
4. Node.js and npm

## Configure Tomcat in Eclipse
1. Go to Window > Preferences.
2. Expand Server > Runtime Environments.
3. Click Add, select Apache Tomcat v9.0 and click Next.
4. Browse to the location where you have installed Tomcat and click Finish.
5. In the Servers view, right-click and select New > Server.
6. Select Apache Tomcat v9.0 Server and click Next.
7. Add your project to the server and click Finish.

## Steps:
1. Unzip the muhammadrizwansaleem23699889.zip file
2. navigate to FilmRestfulWithJSClient folder
3. open eclipse and configure tomcat on port 8080
4. Right Click import Dynamic web project and select the Folder film-rest-api from the unzip file
4. Once application imported right-click on the project and run as select Tomcat Server and click finish
5. Great! Web API is deployed : http://localhost:8080/film-rest-api/films
6. Next we need to start our React JS Client Application
7. navigate to film-web-app project
8. Open film-web-app folder in terminal or Visual Studio code
9. Run "npm install" command in terminal and wait for node_modules to be install completely
10. Now run "npm run dev" command
11. Hurray! Now App is running you can open browser and navigate to http://localhost:5173

Thanks for reading this, if you have any suggestion for me for writing readme let me know thanks.

Author: Muhammad Rizwan Saleem
MMU ID: 23699889