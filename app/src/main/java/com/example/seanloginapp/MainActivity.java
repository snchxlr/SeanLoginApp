package com.example.seanloginapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liveramp.ats.*;   //importing the LR sdk


import com.google.android.material.button.MaterialButton;
import com.liveramp.ats.callbacks.LRCompletionHandlerCallback;
import com.liveramp.ats.callbacks.LREnvelopeCallback;
import com.liveramp.ats.model.Envelope;
import com.liveramp.ats.model.LRAtsConfiguration;
import com.liveramp.ats.model.LREmailIdentifier;


public class MainActivity extends AppCompatActivity {

        String LOGTAG = "LiveRamp ATS Sample";
        String appID = "79216a03-802c-4dc5-aff2-0cb3a9637df6"; // Sample App ID only; create your own!
        String env;

        Boolean notCheckingForCCPA = true;
        Boolean supportOtherGeos = true;

        TextView sdkVersionRef;
        TextView initStatusRef;
        TextView envelopeDisplayRef;
        TextView errMessageRef;
        EditText emailInputValue;


        public void initializeLRSDK(){
            LRAtsConfiguration config = new LRAtsConfiguration(appID, false, false);  //new object with 3 parameters (appid, boolean, boolean)
            LRAtsManager.INSTANCE.setHasConsentForNoLegislation(true);  // telling sdk to not look for consent
            LRAtsManager.INSTANCE.initialize(config, new LRCompletionHandlerCallback() {
                //invoking core component of LR SDK, 'instance' refers to 1 instance (read up singleton pattern), calling initialize function
                // LRCompletionHandlerCallback -> read up callbacks, read callbacks (android) & delegates (ios)
                @Override
                public void invoke(boolean success, @Nullable LRError lrError) {
                    if (success) {
                        Log.i("SampleApp", "success!initializeLRSDK ");
                        // SDK is ready! Fetch an envelope.
                    }
                    else {
                        Log.e("sampleApp", "fail!did_not_initializeLRSDK");
                    }
                }
            });


    //create a LR config object
            //initialize LR SDK with config object

        }

        public void LREnvelope(){

            LRAtsManager.INSTANCE.getEnvelope(new LREmailIdentifier("admin@gmail.com"),
                    new  LREnvelopeCallback() {
                @Override
                public void invoke(@Nullable Envelope envelope, @Nullable LRError lrError) {
                    Log.d("Here's your envelope! ", envelope.getEnvelope());
                }
            });

        }




        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            TextView username = (TextView) findViewById(R.id.username);
            TextView password = (TextView) findViewById(R.id.password);
            MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);


            loginbtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (username.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("admin")) {
                        //correct
                        Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        LREnvelope();
                        Log.i("success", "login successful");
                    } else
                        Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                    Log.i("fail", "login failed");
                    //incorrect
                }
// hi jason


            });
            initializeLRSDK();


        }
    }
