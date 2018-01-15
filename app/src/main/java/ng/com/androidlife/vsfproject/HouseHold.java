package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class HouseHold extends AppCompatActivity {

    TextInputEditText State, LocalGov, TownVillage, Longitude, Latitude, Status, Population, HouseHoldSizeAdultMaleBefore, HouseHoldSizeAdultFemaleBefore,
            HouseHoldSizeChildrenMaleBefore, HouseHoldSizeChildrenFemaleBefore, HouseHoldSizeAdultMaleAfter, HouseHoldSizeAdultFemaleAfter,
            HouseHoldSizeChildrenMaleAfter, HouseHoldSizeChildrenFemaleAfter, HouseHoldSizeAdoptedNumber, HouseHoldSizeAdoptedNames, HouseHoldMemberConditionElderlyBefore,
            HouseHoldMemberConditionDisabledBefore, HouseHoldMemberConditionPregnantBefore, HouseHoldMemberConditionLactingBefore,
            HouseHoldMemberConditionInfantBefore, HouseHoldMemberConditionChildrenBefore, HouseHoldMemberConditionElderlyAfter,
            HouseHoldMemberConditionDisabledAfter, HouseHoldMemberConditionPregnantAfter, HouseHoldMemberConditionLactingAfter,
            HouseHoldMemberConditionInfantAfter, HouseHoldMemberConditionChildrenAfter, HouseHoldMemberAdultMaleLostAfter, HouseHoldMemberAdultFemaleLostAfter,
            HouseHoldMemberAdultChildrenLostAfter;
    Button pushBtn;
    TextView genderBefore, genderAfter, genderInformant, AdoptedText;
    EditText ageBefore, ageAfter, ageInformant;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("HouseHoldData");
        mDatabase.keepSynced(true);

        State = findViewById(R.id.State);
        LocalGov = findViewById(R.id.LocalGov);
        TownVillage = findViewById(R.id.TownVillage);
        Longitude = findViewById(R.id.Longitude);
        Latitude = findViewById(R.id.Latitude);
        Status = findViewById(R.id.Status);
        Population = findViewById(R.id.Population);

        genderBefore = findViewById(R.id.houseHoldGenderBefore);
        genderAfter = findViewById(R.id.houseHoldGenderAfter);
        ageBefore = findViewById(R.id.ageHouseHoldBefore);
        ageAfter = findViewById(R.id.ageHouseHoldAfter);
        genderInformant = findViewById(R.id.InformantGenderText);
        ageInformant = findViewById(R.id.InformantAge);

        HouseHoldSizeAdultMaleBefore = findViewById(R.id.HouseHoldSizeAdultMaleBefore);
        HouseHoldSizeAdultFemaleBefore = findViewById(R.id.HouseHoldSizeAdultFemaleBefore);
        HouseHoldSizeChildrenMaleBefore = findViewById(R.id.HouseHoldSizeChildrenMmaleBefore);
        HouseHoldSizeChildrenFemaleBefore = findViewById(R.id.HouseHoldSizeChildrenFemaleBefore);
        HouseHoldSizeAdultMaleAfter = findViewById(R.id.HouseHoldSizeAdultMaleAfter);
        HouseHoldSizeAdultFemaleAfter = findViewById(R.id.HouseHoldSizeAdultFemaleAfter);
        HouseHoldSizeChildrenMaleAfter = findViewById(R.id.HouseHoldSizeChildrenMmaleAfter);
        HouseHoldSizeChildrenFemaleAfter = findViewById(R.id.HouseHoldSizeChildrenFemaleAfter);
        HouseHoldSizeAdoptedNumber = findViewById(R.id.HouseHoldSizeAdoptedNumber);
        HouseHoldSizeAdoptedNames = findViewById(R.id.HouseHoldSizeAdoptedNames);
        HouseHoldMemberConditionElderlyBefore = findViewById(R.id.HouseHoldMemberConditionElderlyBefore);
        HouseHoldMemberConditionDisabledBefore = findViewById(R.id.HouseHoldMemberConditionDisabledBefore);
        HouseHoldMemberConditionPregnantBefore = findViewById(R.id.HouseHoldMemberConditionPregnantBefore);
        HouseHoldMemberConditionLactingBefore = findViewById(R.id.HouseHoldMemberConditionLactatingBefore);
        HouseHoldMemberConditionInfantBefore = findViewById(R.id.HouseHoldMemberConditionInfantBefore);
        HouseHoldMemberConditionChildrenBefore = findViewById(R.id.HouseHoldMemberConditionChildrenBefore);

        pushBtn = findViewById(R.id.pushBtn);
    }

    public void Submit(View view) {
        Submit();
    }

    private void Submit() {
        final String StateVal = State.getText().toString().trim();
        final String LocalGovVal = LocalGov.getText().toString().trim();
        final String TownVillageVal = TownVillage.getText().toString().trim();
        final String LongitudeVal = Longitude.getText().toString().trim();
        final String LatitudeVal = Latitude.getText().toString().trim();
        final String StatusVal = Status.getText().toString().trim();
        final String PopulationVal = Population.getText().toString().trim();

        final String genderBeforeVal = genderBefore.getText().toString().trim();
        final String genderAfterVal = genderAfter.getText().toString().trim();
        final String ageBeforeVal = ageBefore.getText().toString().trim();
        final String ageAfterVal = ageAfter.getText().toString().trim();
        final String genderInformantVal = genderInformant.getText().toString().trim();
        final String ageInformantVal = ageInformant.getText().toString().trim();

        if (!TextUtils.isEmpty(StateVal)&&
                !TextUtils.isEmpty(LocalGovVal)&&
                !TextUtils.isEmpty(TownVillageVal)&&
                !TextUtils.isEmpty(LongitudeVal)&&
                !TextUtils.isEmpty(LatitudeVal)&&
                !TextUtils.isEmpty(StatusVal)&&
                !TextUtils.isEmpty(PopulationVal)&&
                !TextUtils.isEmpty(genderBeforeVal)&&
                !TextUtils.isEmpty(genderAfterVal)&&
                !TextUtils.isEmpty(ageBeforeVal)&&
                !TextUtils.isEmpty(ageAfterVal)&&
                !TextUtils.isEmpty(genderInformantVal)&&
                !TextUtils.isEmpty(ageInformantVal)){
            final DatabaseReference newPost = mDatabase.push();
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("State").setValue(StateVal);
                    newPost.child("LocalGovernment").setValue(LocalGovVal);
                    newPost.child("TownVillage").setValue(TownVillageVal);
                    newPost.child("Longitude").setValue(LongitudeVal);
                    newPost.child("Latitude").setValue(LatitudeVal);
                    newPost.child("StatusOfTownVillage").setValue(StateVal);
                    newPost.child("EstimatedPopulation").setValue(PopulationVal);
                    newPost.child("HouseHoldGenderBefore").setValue(genderBeforeVal);
                    newPost.child("HouseHoldGenderAfter").setValue(genderAfterVal);
                    newPost.child("HouseHoldAgeBefore").setValue(ageBeforeVal);
                    newPost.child("HouseHoldAgeAfter").setValue(ageAfterVal);
                    newPost.child("InformantGender").setValue(genderInformantVal);
                    newPost.child("InformantAge").setValue(ageInformantVal).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(HouseHold.this,"Data Stored, Connect to Internet to Push to CloudDatabase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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

    public void ChangeAdopted(View view) {
    }
}
