package ng.com.androidlife.vsfproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.security.AccessController.getContext;

public class FocusGroup extends AppCompatActivity {

    TextInputEditText FocusGroup1, FocusGroup2, FocusGroup3, FocusGroup4, FocusGroup5,
            FocusGroup6, FocusGroup7,FocusGroup8,FocusGroup9,FocusGroup10,
            FocusGroup11,FocusGroup12,FocusGroup13,FocusGroup14,FocusGroup15,
            FocusGroup16,FocusGroup17,FocusGroup18,FocusGroup19,FocusGroup20,
            FocusGroup21,FocusGroup22,FocusGroup23,FocusGroup24,FocusGroup25;

    TextView flongitude, DisplayName;

    private String m_Text = "";

    private DatabaseReference mDatabase;

    static boolean isInitialized = false;
    private static String TAG = "KeyInformantActivity";

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_group);

        //Popup Show
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Enter your Name");
        builder.setCancelable(false);

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString().trim();
                DisplayName.setText(m_Text);

                if (m_Text.isEmpty()){
                    Intent YME = new Intent(FocusGroup.this, MenuScreen.class);
                    startActivity(YME);
                }
            }
        });
        /**builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });**/

        builder.show();

        //GetUsername
        //DisplayName.setText(getIntent().getStringExtra("inputEmail"));
        try{
            if (!isInitialized){
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                isInitialized = true;
            }else {
                Log.d(TAG, "Already Initialized");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("FocusGroup");
        mDatabase.keepSynced(true);

        flongitude = findViewById(R.id.fLongitude);

        DisplayName = findViewById(R.id.username);


        FocusGroup1 = findViewById(R.id.FocusGroup1);
        FocusGroup2 = findViewById(R.id.FocusGroup2);
        FocusGroup3 = findViewById(R.id.FocusGroup3);
        FocusGroup4 = findViewById(R.id.FocusGroup4);
        FocusGroup5 = findViewById(R.id.FocusGroup5);
        FocusGroup6 = findViewById(R.id.FocusGroup6);
        FocusGroup7 = findViewById(R.id.FocusGroup7);
        FocusGroup8 = findViewById(R.id.FocusGroup8);
        FocusGroup9 = findViewById(R.id.FocusGroup9);
        FocusGroup10 = findViewById(R.id.FocusGroup10);
        FocusGroup11 = findViewById(R.id.FocusGroup11);
        FocusGroup12 = findViewById(R.id.FocusGroup12);
        FocusGroup13 = findViewById(R.id.FocusGroup13);
        FocusGroup14 = findViewById(R.id.FocusGroup14);
        FocusGroup15 = findViewById(R.id.FocusGroup15);
        FocusGroup16 = findViewById(R.id.FocusGroup16);
        FocusGroup17 = findViewById(R.id.FocusGroup17);
        FocusGroup18 = findViewById(R.id.FocusGroup18);
        FocusGroup19 = findViewById(R.id.FocusGroup19);
        FocusGroup20 = findViewById(R.id.FocusGroup20);
        FocusGroup21 = findViewById(R.id.FocusGroup21);
        FocusGroup22 = findViewById(R.id.FocusGroup22);
        FocusGroup23 = findViewById(R.id.FocusGroup23);
        FocusGroup24 = findViewById(R.id.FocusGroup24);
        FocusGroup25 = findViewById(R.id.FocusGroup25);
    }

    public void GroupSubmit(View view) {
        SubmitGroup();


    }

    private void SubmitGroup() {

        final String Val0 = flongitude.getText().toString().trim();

        final String Val26 = DisplayName.getText().toString().trim();

        final String Val1 = FocusGroup1.getText().toString().trim();
        final String Val2 = FocusGroup2.getText().toString().trim();
        final String Val3 = FocusGroup3.getText().toString().trim();
        final String Val4 = FocusGroup4.getText().toString().trim();
        final String Val5 = FocusGroup5.getText().toString().trim();
        final String Val6 = FocusGroup6.getText().toString().trim();
        final String Val7 = FocusGroup7.getText().toString().trim();
        final String Val8 = FocusGroup8.getText().toString().trim();
        final String Val9 = FocusGroup9.getText().toString().trim();
        final String Val10 = FocusGroup10.getText().toString().trim();
        final String Val11 = FocusGroup11.getText().toString().trim();
        final String Val12 = FocusGroup12.getText().toString().trim();
        final String Val13 = FocusGroup13.getText().toString().trim();
        final String Val14 = FocusGroup14.getText().toString().trim();
        final String Val15 = FocusGroup15.getText().toString().trim();
        final String Val16 = FocusGroup16.getText().toString().trim();
        final String Val17 = FocusGroup17.getText().toString().trim();
        final String Val18 = FocusGroup18.getText().toString().trim();
        final String Val19 = FocusGroup19.getText().toString().trim();
        final String Val20 = FocusGroup20.getText().toString().trim();
        final String Val21 = FocusGroup21.getText().toString().trim();
        final String Val22 = FocusGroup22.getText().toString().trim();
        final String Val23 = FocusGroup23.getText().toString().trim();
        final String Val24 = FocusGroup24.getText().toString().trim();
        final String Val25 = FocusGroup25.getText().toString().trim();

        if (!TextUtils.isEmpty(Val1)&&

                !TextUtils.isEmpty(Val0)&&

                !TextUtils.isEmpty(Val26)&&

                !TextUtils.isEmpty(Val2)&&
                !TextUtils.isEmpty(Val3)&&
                !TextUtils.isEmpty(Val4)&&
                !TextUtils.isEmpty(Val4)&&
                !TextUtils.isEmpty(Val5)&&
                !TextUtils.isEmpty(Val6)&&
                !TextUtils.isEmpty(Val7)&&
                !TextUtils.isEmpty(Val8)&&
                !TextUtils.isEmpty(Val9)&&
                !TextUtils.isEmpty(Val10)&&
                !TextUtils.isEmpty(Val11)&&
                !TextUtils.isEmpty(Val12)&&
                !TextUtils.isEmpty(Val13)&&
                !TextUtils.isEmpty(Val14)&&
                !TextUtils.isEmpty(Val15)&&
                !TextUtils.isEmpty(Val16)&&
                !TextUtils.isEmpty(Val17)&&
                !TextUtils.isEmpty(Val18)&&
                !TextUtils.isEmpty(Val19)&&
                !TextUtils.isEmpty(Val20)&&
                !TextUtils.isEmpty(Val21)&&
                !TextUtils.isEmpty(Val22)&&
                !TextUtils.isEmpty(Val23)&&
                !TextUtils.isEmpty(Val24)&&
                !TextUtils.isEmpty(Val25)){

            FocusGroup1.setText("");
            FocusGroup2.setText("");
            FocusGroup3.setText("");
            FocusGroup4.setText("");
            FocusGroup5.setText("");
            FocusGroup6.setText("");
            FocusGroup7.setText("");
            FocusGroup8.setText("");
            FocusGroup9.setText("");
            FocusGroup10.setText("");
            FocusGroup11.setText("");
            FocusGroup12.setText("");
            FocusGroup13.setText("");
            FocusGroup14.setText("");
            FocusGroup15.setText("");
            FocusGroup16.setText("");
            FocusGroup17.setText("");
            FocusGroup18.setText("");
            FocusGroup19.setText("");
            FocusGroup20.setText("");
            FocusGroup21.setText("");
            FocusGroup22.setText("");
            FocusGroup23.setText("");
            FocusGroup24.setText("");
            FocusGroup25.setText("");

            final DatabaseReference newPost = mDatabase.push();
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    newPost.child("Q26").setValue(Val0);

                    newPost.child("Username").setValue(Val26);

                    newPost.child("Q1").setValue(Val1);
                    newPost.child("Q2").setValue(Val2);
                    newPost.child("Q3").setValue(Val3);
                    newPost.child("Q4").setValue(Val4);
                    newPost.child("Q5").setValue(Val5);
                    newPost.child("Q6").setValue(Val6);
                    newPost.child("Q7").setValue(Val7);
                    newPost.child("Q8").setValue(Val8);
                    newPost.child("Q9").setValue(Val9);
                    newPost.child("Q10").setValue(Val10);
                    newPost.child("Q11").setValue(Val11);
                    newPost.child("Q12").setValue(Val12);
                    newPost.child("Q13").setValue(Val13);
                    newPost.child("Q14").setValue(Val14);
                    newPost.child("Q15").setValue(Val15);
                    newPost.child("Q16").setValue(Val16);
                    newPost.child("Q17").setValue(Val17);
                    newPost.child("Q18").setValue(Val18);
                    newPost.child("Q19").setValue(Val19);
                    newPost.child("Q20").setValue(Val20);
                    newPost.child("Q21").setValue(Val21);
                    newPost.child("Q22").setValue(Val22);
                    newPost.child("Q23").setValue(Val23);
                    newPost.child("Q24").setValue(Val24);
                    newPost.child("Q25").setValue(Val25)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(FocusGroup.this,"Your Data Is Stored To CloudDatabase", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(FocusGroup.this,"Data Stored, Connect to Internet to Push to CloudDatabase", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            Toast.makeText(FocusGroup.this,"Please Check Unanswered Questions", Toast.LENGTH_SHORT).show();
        }
    }

    public void BackToMenu(View view) {
        Intent Back = new Intent(this, MenuScreen.class);
        startActivity(Back);
    }

    public void fGetLocationDetails(View view) {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            buildAlertMessageNoGPS();
        }else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            GetLocationDetails();
        }
    }

    private void GetLocationDetails() {
        if (ActivityCompat.checkSelfPermission(FocusGroup.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (FocusGroup.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FocusGroup.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                latitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                flongitude.setText("Your current location is"+ "\n" + "Latitude = " + latitude + "\n" + "longitude = " + longitude);
            }else {
                Toast.makeText(this, "Unable to trace your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void buildAlertMessageNoGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please turn on your Location Access")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to menu", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }


}
