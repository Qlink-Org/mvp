package com.qlink.common.utils.neo.test; 

import java.util.Map;

import org.junit.Test;

import com.qlink.common.utils.neo.client.NeoClient;


public class NeoTest {

    private NeoClient neoClient = new NeoClient();

    @Test
    public void testInsert(){
    	String scriptHash = "0x3c609804b58157abb85fb2fee8933672f65c0369";
    	String JsonRpcMethod = "invokefunction";
    	String contractMethod = "put";
    	String key = "ss_id_1234";
    	String val = "{ \"firstName\": \"Brett\", \"lastName\":\"McLaughlin\", \"email\": \"aaaa\" }";
    	Map<String, String> reusltMap = neoClient.insertVal(scriptHash, JsonRpcMethod, contractMethod, key, val);
    	if("1".equals(reusltMap.get("code"))){
    		System.out.println("成功======"+reusltMap.get("msg"));
    	} else {
    		System.out.println("失败======"+reusltMap.get("msg"));
    	}
    }

    @Test
    public void testQuery(){
    	String scriptHash = "0x8647667327cbde21741a11bd0a26b58b06789aeb";
    	String JsonRpcMethod = "invokefunction";
    	String contractMethod = "totalSupply";
    	/*String key = "ss_id_1234";*/
    	String key = "";
    	Map<String, String> result = neoClient.queryByKey(scriptHash, JsonRpcMethod, contractMethod, key);
    	System.out.println(result);
    }
    
    
    public static void main(String[] args) {

		// 十六进制转化为十进制，结果140。
		//System.out.println(Long.parseLong("00d0ed902e",16));
    	
    	String str="00d0ed902e";
		String myStr[]={"a","b","c","d","e","f"};
		int result=0;
		int n=1;
		for(int i=str.length()-1;i>=0;i--){
			String param=str.substring(i,i+1);
			for(int j=0;j<myStr.length;j++){
			if(param.equalsIgnoreCase(myStr[j])){
				param="1"+String.valueOf(j);
			}
			}
			result+=Integer.parseInt(param)*n;
			n*=16;
		}
		System.out.println(result);
		System.out.println(Integer.parseInt(str, 16));
		
	}
    
    public static String toHex(byte[] hash){
    	StringBuffer buf = new StringBuffer(hash.length * 2);
    	int i;
    	for ( i = 0; i < hash.length; i++) {
			if(((int)hash[i] & 0xff) < 0x10){
				buf.append("0");
			}
			buf.append(Long.toString((int) hash[i] & 0xff, 16 ));
		}
    	return buf.toString();
    }
    
}
