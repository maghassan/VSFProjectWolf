package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuScreen extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            /**startActivity(new Intent(MainActivity.this, MainActivity.class));
             finish();**/

            Toast.makeText(MenuScreen.this, "Welcome!" ,
                    Toast.LENGTH_SHORT).show();
        }else if (auth.getCurrentUser() == null){
            startActivity(new Intent(MenuScreen.this, LoginActivity.class));
            finish();
        }
    }

    public void OpenHouse(View view) {
        Intent House = new Intent(this, HouseHold.class);
        startActivity(House);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(this, MenuScreen.class);
        startActivity(back);
    }

    public void Logout(View view) {
        Intent Logout = new Intent(this, MainActivity.class);
        startActivity(Logout);
    }

    public void KeyInformants(View view) {
        Intent KeyInformants = new Intent(this, KeyInformants.class);
        startActivity(KeyInformants);
    }

    public void FocusGroup(View view) {
        Intent FocusGroup = new Intent(this, FocusGroup.class);
        startActivity(FocusGroup);
    }

    public void OpenHelp(View view) {
        Intent OpenHelp = new Intent(this, Help.class);
        startActivity(OpenHelp);
    }
}
