package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void Login(View view) {
        Intent Lgn = new Intent(this, MenuScreen.class);
        if (username.getText().toString().trim().equals("a") && password.getText().toString().trim().equals("a")){
                startActivity(Lgn);
            }
            else {
                Toast.makeText(this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
            }
    }

    public void Reset(View view) {
        username.setText("");
        password.setText("");
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
