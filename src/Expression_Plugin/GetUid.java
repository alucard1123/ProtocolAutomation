package Expression_Plugin;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by Edward on 14-7-21.
 */
public class GetUid {
    public static String getDBValue(int cursor,String tgt){
        String UID = null;
        ReadConfig m = new ReadConfig();
        Map<String,String> argMap = m.MainConfigMap();
        String DBIP = argMap.get("DBIP");
        String DBU = argMap.get("DBU");
        String DBP = argMap.get("DBP");
        String DBSID = argMap.get("DBSID");
        String instance = DBIP+":1521:"+DBSID;
        ConnDB conn = new ConnDB();
        try {
            conn.connDB(instance,DBU,DBP);
            ResultSet rs = conn.runSql("select * from t_info_subaccount");
            rs.absolute(cursor);
            if(tgt.equals("UID")){
                UID = rs.getString(2);
            }else if(tgt.equals("BANK")){
                UID = rs.getString(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  UID;
    }
}
