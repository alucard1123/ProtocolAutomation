package Expression_Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 14-1-22.
 */
public class ReadConfig {
    public Map<String,String> MainConfigMap(){
        Map<String,String> map = new HashMap();
        File file = new File("config.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while((line=br.readLine())!=null){
                //WARRING:test array only for test
                String[] vec = line.split(":");
                map.put(vec[0],vec[1]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return map;
    }
}
