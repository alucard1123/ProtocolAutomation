package Expression_Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Edward on 14-7-4.
 */
public class TransExpression {
    private int cluser = 0;
    public String Trans(String input){
        String currentInput = input;
        //clear brace
        currentInput=currentInput.replace("{","");
        currentInput=currentInput.replace("}","");

        String[] cuttedString = currentInput.split(":");
        String cuttedKey = cuttedString[0];
        String cuttedValue = cuttedString[1];
        if(cuttedKey.equals("bank")){
            //to bank module
            String returned = null;
            int length = cuttedValue.length();
            if(length>3){
                for(int i=0;i<length;i++){
                    returned +=(int)(10*Math.random());
                }
                return returned;
            }
            else{
                if(cuttedValue.split("@").length==2){
                    int DBCursor = Integer.parseInt(cuttedValue.split("@")[1]);
                    String bankFromDB = GetUid.getDBValue(DBCursor,"BANK");
                    return bankFromDB;
                }
                System.out.println("in random module");
            }
        }else if(cuttedKey.equals("uid")){
            String cuttedUid =cuttedValue;
            int uidBit;
            try{
                uidBit= Integer.parseInt(cuttedUid);
            }
            catch (NumberFormatException ex ){
                if(cuttedUid.split("@").length==2){
                    try{
                        int DBCursor = Integer.parseInt(cuttedUid.split("@")[1]);

                        String uidFromDB = GetUid.getDBValue(DBCursor, "UID");
                        return uidFromDB;
                    }catch (NumberFormatException exII){
                        return cuttedValue;
                    }
                }
                return cuttedValue;
            }
            if (cuttedUid.length()>2){
                //add other bits.
                int addedBit = 15-cuttedUid.length();
                if(addedBit>0){
                    for(int i=0;i<addedBit;i++){
                        cuttedUid +=(int)(10*Math.random());
                    }
                    return cuttedUid;
                }
            }else if(uidBit<15){
                String returned = "";
                for(int i=0;i<uidBit;i++){
                    returned +=(int)(10*Math.random());
                }
                return returned;
            }else{
                return cuttedValue;
            }

        }
        else {
            System.out.println(cuttedKey);
        }
        return null;
    }
    private void checkAndAsk(){
        String verify = null;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            verify = stdin.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(verify.equals("n")){
            System.out.println("Exiting program...");
            System.exit(0);
        }
    }
    public void addCluser(){
        this.cluser++;
    }
    public int getCluser(){
        return this.cluser;
    }
}
