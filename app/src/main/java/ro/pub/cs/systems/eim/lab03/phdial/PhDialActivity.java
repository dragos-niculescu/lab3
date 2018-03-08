package ro.pub.cs.systems.eim.lab03.phdial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import ro.pub.cs.systems.eim.lab03.phdial.R;
import ro.pub.cs.systems.eim.lab03.phdial.Constants;

public class PhDialActivity extends AppCompatActivity {

    final public static int buttonIds[] = {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button_star,
            R.id.button_sharp,
            R.id.button_call,
            R.id.button_hangup,
            R.id.button_delete
    };

    private EditText phoneNumberEditText;
    private ImageButton callButton;
    private ImageButton hangupButton;
    private ImageButton deleteButton;
    private Button genericButton;

    private ButtonClickListener ButtonListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(Constants.TAG, "Button pressed: " + ((Button)view).getText().toString());
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button)view).getText().toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_dial);

        phoneNumberEditText = (EditText)findViewById(R.id.number);
        callButton = (ImageButton)findViewById(R.id.button_call);
        hangupButton = (ImageButton)findViewById(R.id.button_hangup);
        deleteButton = (ImageButton)findViewById(R.id.button_delete);

        for(int i = 0; i <= 11; i++) {
            Button b = (Button)findViewById(buttonIds[i]);
            /*
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(Constants.TAG, "Button pressed: " + i);
                }
            });
            */
            b.setOnClickListener(ButtonListener);

        }
        ImageButton bDelete = (ImageButton)findViewById(R.id.button_delete);
        bDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(Constants.TAG, "Delete Button pressed: ");
                    String nr = phoneNumberEditText.getText().toString();
                    int len = nr.length();
                    if(len >= 1)
                        phoneNumberEditText.setText(nr.substring(0, len-1));

                }
        });
        ImageButton bCall = (ImageButton)findViewById(R.id.button_call);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constants.TAG, "Call Button pressed: ");
                if (ContextCompat.checkSelfPermission(PhDialActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhDialActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                    startActivity(intent);
                }
            }
        });
        ImageButton bHangup = (ImageButton)findViewById(R.id.button_hangup);
        bHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constants.TAG, "Hangup Button pressed: ");
            }
        });


    }

}
