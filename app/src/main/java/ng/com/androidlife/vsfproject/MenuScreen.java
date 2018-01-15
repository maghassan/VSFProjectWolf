package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
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
}
