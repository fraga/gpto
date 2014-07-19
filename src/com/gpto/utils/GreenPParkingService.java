package com.gpto.utils;

import java.io.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;

import android.util.Log;

public class GreenPParkingService {
    public static final String SERVICE_ADDRESS = "https://raw.githubusercontent.com/fraga/gpto/master/greenPParking.json";
    
    private GreenPParkingService() {}
    
    public static String GetData() {
        DefaultHttpClient client = new DefaultHttpClient();
  //      client.setParams(new BasicHttpParams());
        
        HttpGet httpget = new HttpGet(GreenPParkingService.SERVICE_ADDRESS);
        
        InputStream stream = null;
        
        try {
            HttpResponse response = client.execute(httpget);
            stream = response.getEntity().getContent();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
            
            StringBuilder resultBuild = new StringBuilder();
            String currentLine = null;
            
            while((currentLine = reader.readLine()) != null)
            {
                resultBuild.append(currentLine);
            }
           
            return resultBuild.toString();
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        finally {
            if(stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e("ERROR", e.getMessage());
                }
        }
        
        return null;
    }
}
