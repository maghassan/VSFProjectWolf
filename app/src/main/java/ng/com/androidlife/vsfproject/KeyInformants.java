package ng.com.androidlife.vsfproject;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class KeyInformants extends AppCompatActivity {

    TextView genderBefore, genderAfter, genderInformant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_informants);

        genderBefore = findViewById(R.id.COMMUNITYGenderBefore);
        genderAfter = findViewById(R.id.COMMUNITYGenderAfter);
        genderInformant = findViewById(R.id.InformantGenderText);
    }

    public void SetMaleBefore(View view) {
        genderBefore.setText("Male");
    }

    public void SetFemaleBefore(View view) {
        genderBefore.setText("Female");
    }

    public void SetMaleAfter(View view) {
        genderAfter.setText("Male");
    }

    public void SetFemaleAfter(View view) {
        genderAfter.setText("Female");
    }

    public void InformantMale(View view) {
        genderInformant.setText("Male");
    }

    public void InformantFemale(View view) {
        genderInformant.setText("Female");
    }

    public void AvGood(View view) {
    }

    public void AvPartDamage(View view) {
    }

    public void AvNotGood(View view) {
    }

    public void AvNotAval(View view) {
    }

    public void Functional(View view) {
    }

    public void NotFunctional(View view) {
    }

    public void Renovated(View view) {
    }

    public void OngoingRenovation(View view) {
    }

    public void Abandoned(View view) {
    }

    public void Agency(View view) {
    }
}
