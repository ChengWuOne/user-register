package com.ibm.clientvantage.util;

import java.util.UUID;

/*Create a random unique string
 * */
public class UuidUtils {
	public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
