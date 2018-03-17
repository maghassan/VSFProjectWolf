package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    TextView DisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DisplayName = findViewById(R.id.username);

        //DisplayName.setText(getIntent().getStringExtra("inputEmail"));

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            /**startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();**/

            Toast.makeText(MainActivity.this, "Welcome!" ,
                    Toast.LENGTH_SHORT).show();
        }else if (auth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    public void OpenUserSetting(View view) {
        Intent UserSettings = new Intent(this, UserSettings.class);
        startActivity(UserSettings);
    }

    public void OpenMenu(View view) {
        Intent OpenMenu = new Intent(this, MenuScreen.class);
        startActivity(OpenMenu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}