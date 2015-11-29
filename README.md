# cloudsoa-springbootservice
REST api with JWT auth. Made with Groovy and Spring Boot.

This is a part of a system of different clients and backend apis, made to investigate and prove interoperability in Microsoft Azure and their security token services.

This application exposes a REST endpoint, which requires JWT tokens from Microsoft Azure Active Directory.

# Build the aplication
Clone the repository and run "gradle bootRepackage"

# Configuration in Azure AD
In the classic Azure portal, navigate to Active Directory -> standard catalog -> Application

1. Press Add
2. Choose "...an application my organization is developing"
3. Choose a name and select "Web application/web api"
4. Fill in a dummy sign on url, and come up with a "app id uri", for example http://yourtenant.onmicrosoft.com/appname. This will be used by all clients.

TODO: The client id should be registered and validated by the application.

#  Configure Azure Application
In the classic Azure portal, click New and select Compute -> Web app -> Custom create

Come up with an url and select "no database".

When the app is created, navigate to the Configure tab. 

* Change "Java version" from Off to 1.7 (or later) and select web container: "Tomcat".

Navigate to the "Dashboard" tab

* select "Reset deployment credentials" and choose a username and password. 
* Press "Stop" in the bottom of the screen to remove possible file locks before deployment.

Connect with a FTP client to the "FTP host name" displayed on the "Dashboard" tab. 

* Authenticate with the username and password you chose.
* Navigate to the wwwroot/webapps remote directory.
* Delete the ROOT directory.
* Upload the war-file from your local build. It is located in the build/libs local directory. Rename it to "ROOT.war" on the remote server.

Press "Start" in the Azure portal to start Tomcat again.

Navigate to the app url. You should see an error about authentication credentials missing.
