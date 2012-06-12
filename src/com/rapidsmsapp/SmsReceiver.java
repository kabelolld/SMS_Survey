package com.rapidsmsapp;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
 
public class SmsReceiver extends BroadcastReceiver
{
	
	 SmsMessage[] msgs = null;
	  String str = "";   
	  String sms[];
	  String smsValues ="";
	  String TblName;
	  String data;
	  String Resp ="";
	  int i =1;
	  int len;
    @SuppressWarnings("deprecation")
	@Override
    public void onReceive(Context context, Intent intent) 
    {
    	 Bundle bundle = intent.getExtras();  
    	         
          if (bundle != null)
          {
        	  //---retrieve the SMS message received---
              Object[] pdus = (Object[]) bundle.get("pdus");
              msgs = new SmsMessage[pdus.length];            
              for (int i=0; i<msgs.length; i++){
                  msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                  
                  //str += "SMS from " + msgs[i].getOriginatingAddress();                     
   
                  str += msgs[i].getMessageBody().toString();
                         
              }
             
              smsValues ="";
              sms = str.split(",");
              TblName = sms[0];
              
              len = sms.length;
            
              
             for(i= 1; i < len; i++)
              {
            	 smsValues += "'"+sms[i]+"'";
            	 
            	 if((i+1) != len)
            	 {
            		 smsValues +=",";
            	 }
            	  
              }
             Toast.makeText(context, TblName,Toast.LENGTH_SHORT).show();
             
             Toast.makeText(context, smsValues,Toast.LENGTH_LONG).show();
             try
             { 
            	String strUrl = "http://tutchat.comze.com/PhoneGap/saveSMS.php";
         		
         		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>(); 
         		postParameters.add(new BasicNameValuePair("tblname",TblName));   
         		postParameters.add(new BasicNameValuePair("fieldname",smsValues));
         		
         		Resp = CustomHttpClient.executeHttpPost(strUrl, postParameters);
         		Resp = Resp.substring(0, Resp.indexOf("@"));
         		Toast.makeText(context,"Database Results: \n"+Resp,Toast.LENGTH_LONG ).show();
         		
         		
             }
             catch(Exception e)
             {
            	 Toast.makeText(context,"Error: \n"+ e.toString()+"\n"+e.getMessage(),50000).show();
             }
             
          }

    }
}
    	 