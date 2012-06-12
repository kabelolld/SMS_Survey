package com.rapidsmsapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendsms extends Activity 
{
	
	Button btnback,btnSend;
	
	BroadcastReceiver br;
	PendingIntent sentPI;
	PendingIntent deliveredPI;
	
	String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";

	EditText phoneNo;
	EditText Msg;
	String strMsg,strPhone;
	Toast toast;
	
	  public void onCreate(Bundle savedInstanceState)
	   {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sendsms);
	        sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
	        
	        btnback =		(Button)findViewById(R.id.btnBack3); 
	        btnSend = 		(Button)findViewById(R.id.btnSendMsg);    
	       
	        phoneNo =		(EditText)findViewById(R.id.edtphoneNumber);
	        Msg=			(EditText)findViewById(R.id.edtMsg); 
	        
	        
	        btnback.setOnClickListener(new View.OnClickListener() {
	    		
	    		public void onClick(View v) 
	    		{
	    			// TODO Auto-generated method stub
	    			 
	    			 Intent i = new Intent(sendsms.this,messagemenu.class); //calling a class forms
	    			 sendsms.this.finish();// close the form class 
	    			 startActivity(i);
	    			
	    		}
	    	});
	        btnSend.setOnClickListener(new View.OnClickListener() {
	    		
	    		public void onClick(View v) 
	    		{
	    			// TODO Auto-generated method stub
	    			
	    			strMsg = Msg.getText().toString();
	    			strPhone = phoneNo.getText().toString();				
	    			
	    			                
	                if (strPhone.length()>0 && strMsg.length()>0)
	                 {
	                     sendSMS(strPhone, strMsg);
	                	 Toast.makeText(sendsms.this,"Message Sent is:\n"+strMsg,3000).show();
	                 }
	                 else
	                     Toast.makeText(sendsms.this,"Please enter both phone number and message.",7000).show();
	    		}
	    	});
	         
  }
	  
	  public void sendSMS(String phoneNumber, String message)
	  { 
	 				String SENT = "SMS_SENT";
	 	            String DELIVERED = "SMS_DELIVERED";
	 	            PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
	 	            
	 	            PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,new Intent(DELIVERED), 0);
	 				
	 				 registerReceiver(new BroadcastReceiver(){
	 	                @Override
	 	                public void onReceive(Context arg0, Intent arg1) {
	 	                    switch (getResultCode())
	 	                    {
	 	                        case Activity.RESULT_OK:
	 	                            Toast.makeText(getBaseContext(), "SMS sent", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	 	                            Toast.makeText(getBaseContext(), "Generic failure", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                        case SmsManager.RESULT_ERROR_NO_SERVICE:
	 	                            Toast.makeText(getBaseContext(), "No service", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                        case SmsManager.RESULT_ERROR_NULL_PDU:
	 	                            Toast.makeText(getBaseContext(), "Null PDU", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                        case SmsManager.RESULT_ERROR_RADIO_OFF:
	 	                            Toast.makeText(getBaseContext(), "Radio off", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                    }
	 	                }
	 	            }, new IntentFilter(SENT));

	 				
	 				registerReceiver(new BroadcastReceiver(){
	 	                @Override
	 	                public void onReceive(Context arg0, Intent arg1) {
	 	                    switch (getResultCode())
	 	                    {
	 	                        case Activity.RESULT_OK:
	 	                            Toast.makeText(getBaseContext(), "SMS delivered", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;
	 	                        case Activity.RESULT_CANCELED:
	 	                            Toast.makeText(getBaseContext(), "SMS not delivered", 
	 	                                    Toast.LENGTH_SHORT).show();
	 	                            break;                        
	 	                    }
	 	                }
	 	            }, new IntentFilter(DELIVERED));     
	 				
	 				 SmsManager sms = SmsManager.getDefault();
	 				 sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);   
	  }
}