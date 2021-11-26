import java.io.File;

import javazoom.jl.player.*;
import java.io.FileInputStream;


public class Music {
    static String bip = "music.mp3";
    static String link = (new File("").getAbsolutePath()).concat("\\assets\\"+ bip).replace("\\","/");
    FileInputStream fis;
    Player playMP3;
    public Music() throws Exception {
            fis = new FileInputStream(link);
            playMP3 = new Player(fis);
        }
}
