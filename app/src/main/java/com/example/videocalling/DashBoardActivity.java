package com.example.videocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.videocalling.databinding.ActivityDashBoardBinding;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashBoardActivity extends AppCompatActivity {
ActivityDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        URL url;
        try {
            url=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOption=new JitsiMeetConferenceOptions.Builder().setServerURL(url).setWelcomePageEnabled(false).build();
            JitsiMeet.setDefaultConferenceOptions(defaultOption);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        binding.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
        .setRoom(binding.MainBox.getText().toString())
        .setWelcomePageEnabled(false)
        .build();
                JitsiMeetActivity.launch(DashBoardActivity.this,options);
            }
        });
    }
}