package tripAdvisor;

import org.testng.annotations.*;

import java.io.IOException;

public class Setup_DockerGrid {

    @BeforeSuite
    void startDockerGrid(){
        try {
            Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
            Thread.sleep(30000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterSuite
    void stopDockerGrid(){
        try {
            Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
            Thread.sleep(15000);
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
