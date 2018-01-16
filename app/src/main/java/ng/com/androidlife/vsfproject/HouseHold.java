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
            HouseHoldMemberConditionDisabledBefore, HouseHoldMemberConditionPregnantBefore, HouseHoldMemberConditionLactatingBefore,
            HouseHoldMemberConditionInfantBefore, HouseHoldMemberConditionChildrenBefore, HouseHoldMemberConditionElderlyAfter,
            HouseHoldMemberConditionDisabledAfter, HouseHoldMemberConditionPregnantAfter, HouseHoldMemberConditionLactatingAfter,
            HouseHoldMemberConditionInfantAfter, HouseHoldMemberConditionChildrenAfter, HouseHoldMemberAdultMaleLostAfter, HouseHoldMemberAdultFemaleLostAfter,
            HouseHoldMemberChildrenLostAfter, MemberQualificationBefore, MemberQualificationAfter, MemberOccupationBefore, MemberOccupationAfter, MemberOtherOccupationBefore,
            MemberOtherOccupationAfter, BoreHole, CementWell, EarthDam, River, Pond, TreatmentPlant, OtherWater, NationalGrid, REBElectricity, PrivatePlant, Solar,
            OrganizationBenefit, OrganizationBenefitYes, OrganizationBenefitSpecify, RespondentIncomeBefore, RespondentIncomeAfter, Livelihood, LivelihoodDisplaced;
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

        //General

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

        //Household Stuffs

        HouseHoldSizeAdultMaleBefore = findViewById(R.id.HouseHoldSizeAdultMaleBefore);
        HouseHoldSizeAdultFemaleBefore = findViewById(R.id.HouseHoldSizeAdultFemaleBefore);
        HouseHoldSizeChildrenMaleBefore = findViewById(R.id.HouseHoldSizeChildrenMmaleBefore);
        HouseHoldSizeChildrenFemaleBefore = findViewById(R.id.HouseHoldSizeChildrenFemaleBefore);
        HouseHoldSizeAdultMaleAfter = findViewById(R.id.HouseHoldSizeAdultMaleAfter);
        HouseHoldSizeAdultFemaleAfter = findViewById(R.id.HouseHoldSizeAdultFemaleAfter);
        HouseHoldSizeChildrenMaleAfter = findViewById(R.id.HouseHoldSizeChildrenMmaleAfter);
        HouseHoldSizeChildrenFemaleAfter = findViewById(R.id.HouseHoldSizeChildrenFemaleAfter);
        AdoptedText = findViewById(R.id.AdoptedText);
        HouseHoldSizeAdoptedNumber = findViewById(R.id.HouseHoldSizeAdoptedNumber);
        HouseHoldSizeAdoptedNames = findViewById(R.id.HouseHoldSizeAdoptedNames);
        HouseHoldMemberConditionElderlyBefore = findViewById(R.id.HouseHoldMemberConditionElderlyBefore);
        HouseHoldMemberConditionDisabledBefore = findViewById(R.id.HouseHoldMemberConditionDisabledBefore);
        HouseHoldMemberConditionPregnantBefore = findViewById(R.id.HouseHoldMemberConditionPregnantBefore);
        HouseHoldMemberConditionLactatingBefore = findViewById(R.id.HouseHoldMemberConditionLactatingBefore);
        HouseHoldMemberConditionInfantBefore = findViewById(R.id.HouseHoldMemberConditionInfantBefore);
        HouseHoldMemberConditionChildrenBefore = findViewById(R.id.HouseHoldMemberConditionChildrenBefore);
        HouseHoldMemberConditionElderlyAfter = findViewById(R.id.HouseHoldMemberConditionElderlyAfter);
        HouseHoldMemberConditionDisabledAfter = findViewById(R.id.HouseHoldMemberConditionDisabledAfter);
        HouseHoldMemberConditionPregnantAfter = findViewById(R.id.HouseHoldMemberConditionPregnantAfter);
        HouseHoldMemberConditionLactatingAfter = findViewById(R.id.HouseHoldMemberConditionLactatingAfter);
        HouseHoldMemberConditionInfantAfter = findViewById(R.id.HouseHoldMemberConditionInfantAfter);
        HouseHoldMemberConditionChildrenAfter = findViewById(R.id.HouseHoldMemberConditionChildrenAfter);
        HouseHoldMemberAdultMaleLostAfter = findViewById(R.id.HouseHoldMemberAdultMaleLostAfter);
        HouseHoldMemberAdultFemaleLostAfter = findViewById(R.id.HouseHoldMemberAdultFemaleLostAfter);
        HouseHoldMemberChildrenLostAfter = findViewById(R.id.HouseHoldMemberChildrenLostAfter);

        //SociaEconomic Stuffs

        MemberQualificationBefore = findViewById(R.id.HouseHoldMemberQualificationBefore);
        MemberQualificationAfter = findViewById(R.id.HouseHoldMemberQualificationAfter);
        MemberOccupationBefore = findViewById(R.id.HouseHoldMemberOccupationBefore);
        MemberOccupationAfter = findViewById(R.id.HouseHoldMemberOccupationAfter);
        MemberOtherOccupationBefore = findViewById(R.id.HouseHoldMemberOtherOccupationBefore);
        MemberOtherOccupationAfter = findViewById(R.id.HouseHoldMemberOtherOccupationAfter);
        RespondentIncomeBefore = findViewById(R.id.RespondentIncomeBefore);
        RespondentIncomeAfter = findViewById(R.id.RespondentIncomeAfter);
        BoreHole = findViewById(R.id.BoreHole);
        CementWell = findViewById(R.id.CementWell);
        EarthDam = findViewById(R.id.EarthDam);
        River = findViewById(R.id.River);
        Pond = findViewById(R.id.Pond);
        TreatmentPlant = findViewById(R.id.TreatmentPlant);
        OtherWater = findViewById(R.id.OtherWater);
        NationalGrid = findViewById(R.id.NationalGrid);
        REBElectricity = findViewById(R.id.REBElectricity);
        PrivatePlant = findViewById(R.id.PrivatePlant);
        Solar = findViewById(R.id.Solar);
        OrganizationBenefit = findViewById(R.id.OrganizationBenefit);
        OrganizationBenefitYes = findViewById(R.id.OrganizationBenefitYes);
        OrganizationBenefitSpecify = findViewById(R.id.OrganizationBenefitSpecify);

        pushBtn = findViewById(R.id.pushBtn);
    }

    public void Submit(View view) {
        Submit();
    }

    private void Submit() {

        final String Val1 = MemberQualificationBefore.getText().toString().trim();
        final String Val2 = MemberQualificationAfter.getText().toString().trim();
        final String Val3 = MemberOccupationBefore.getText().toString().trim();
        final String Val4 = MemberOccupationAfter.getText().toString().trim();
        final String Val5 = MemberOtherOccupationBefore.getText().toString().trim();
        final String Val6 = MemberOtherOccupationAfter.getText().toString().trim();
        final String Val7 = BoreHole.getText().toString().trim();
        final String Val8 = CementWell.getText().toString().trim();
        final String Val9 = EarthDam.getText().toString().trim();
        final String Val10 = River.getText().toString().trim();
        final String Val11 = Pond.getText().toString().trim();
        final String Val12 = TreatmentPlant.getText().toString().trim();
        final String Val13 = OtherWater.getText().toString().trim();
        final String Val14 = NationalGrid.getText().toString().trim();
        final String Val15 = REBElectricity.getText().toString().trim();
        final String Val16 = PrivatePlant.getText().toString().trim();
        final String Val17 = Solar.getText().toString().trim();
        final String Val18 = OrganizationBenefit.getText().toString().trim();
        final String Val19 = OrganizationBenefitYes.getText().toString().trim();
        final String Val20 = OrganizationBenefitSpecify.getText().toString().trim();
        final String Val21 = RespondentIncomeBefore.getText().toString().trim();
        final String Val22 = RespondentIncomeAfter.getText().toString().trim();

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

        final String HouseHoldSizeAdultMaleBeforeVal = HouseHoldSizeAdultMaleBefore.getText().toString().trim();
        final String HouseHoldSizeAdultFemaleBeforeVal = HouseHoldSizeAdultFemaleBefore.getText().toString().trim();
        final String HouseHoldSizeChildrenMaleBeforeVal = HouseHoldSizeChildrenMaleBefore.getText().toString().trim();
        final String HouseHoldSizeChildrenFemaleBeforeVal = HouseHoldSizeChildrenFemaleBefore.getText().toString().trim();
        final String HouseHoldSizeAdultMaleAfterVal = HouseHoldSizeAdultMaleAfter.getText().toString().trim();
        final String HouseHoldSizeAdultFemaleAfterVal = HouseHoldSizeAdultFemaleAfter.getText().toString().trim();
        final String HouseHoldSizeChildrenMaleAfterVal = HouseHoldSizeChildrenMaleAfter.getText().toString().trim();
        final String HouseHoldSizeChildrenFemaleAfterVal = HouseHoldSizeChildrenFemaleAfter.getText().toString().trim();
        final String AdoptedTextVal = AdoptedText.getText().toString().trim();
        final String HouseHoldSizeAdoptedNumberVal = HouseHoldSizeAdoptedNumber.getText().toString().trim();
        final String HouseHoldSizeAdoptedNamesVal = HouseHoldSizeAdoptedNames.getText().toString().trim();
        final String HouseHoldMemberConditionElderlyBeforeVal = HouseHoldMemberConditionElderlyBefore.getText().toString().trim();
        final String HouseHoldMemberConditionDisabledBeforeVal = HouseHoldMemberConditionDisabledBefore.getText().toString().trim();
        final String HouseHoldMemberConditionPregnantBeforeVal = HouseHoldMemberConditionPregnantBefore.getText().toString().trim();
        final String HouseHoldMemberConditionLactatingBeforeVal = HouseHoldMemberConditionLactatingBefore.getText().toString().trim();
        final String HouseHoldMemberConditionInfantBeforeVal = HouseHoldMemberConditionInfantBefore.getText().toString().trim();
        final String HouseHoldMemberConditionChildrenBeforeVal = HouseHoldMemberConditionChildrenBefore.getText().toString().trim();
        final String HouseHoldMemberConditionElderlyAfterVal = HouseHoldMemberConditionElderlyAfter.getText().toString().trim();
        final String HouseHoldMemberConditionDisabledAfterVal = HouseHoldMemberConditionDisabledAfter.getText().toString().trim();
        final String HouseHoldMemberConditionPregnantAfterVal = HouseHoldMemberConditionPregnantAfter.getText().toString().trim();
        final String HouseHoldMemberConditionLactatingAfterVal = HouseHoldMemberConditionLactatingAfter.getText().toString().trim();
        final String HouseHoldMemberConditionInfantAfterVal = HouseHoldMemberConditionInfantAfter.getText().toString().trim();
        final String HouseHoldMemberConditionChildrenAfterVal = HouseHoldMemberConditionChildrenAfter.getText().toString().trim();
        final String HouseHoldMemberAdultMaleLostAfterVal = HouseHoldMemberAdultMaleLostAfter.getText().toString().trim();
        final String HouseHoldMemberAdultFemaleLostAfterVal = HouseHoldMemberAdultFemaleLostAfter.getText().toString().trim();
        final String HouseHoldMemberChildrenLostAfterVal = HouseHoldMemberChildrenLostAfter.getText().toString().trim();

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
                !TextUtils.isEmpty(ageInformantVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdultMaleBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdultFemaleBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldSizeChildrenMaleBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldSizeChildrenFemaleBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdultMaleAfterVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdultFemaleAfterVal)&&
                !TextUtils.isEmpty(HouseHoldSizeChildrenMaleAfterVal)&&
                !TextUtils.isEmpty(HouseHoldSizeChildrenFemaleAfterVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdoptedNumberVal)&&
                !TextUtils.isEmpty(HouseHoldSizeAdoptedNamesVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionElderlyBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionDisabledBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionPregnantBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionLactatingBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionInfantBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionChildrenBeforeVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionElderlyAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionDisabledAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionPregnantAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionLactatingAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionInfantAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberConditionChildrenAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberAdultMaleLostAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberAdultFemaleLostAfterVal)&&
                !TextUtils.isEmpty(HouseHoldMemberChildrenLostAfterVal)&&
                !TextUtils.isEmpty(AdoptedTextVal)&&
                !TextUtils.isEmpty(Val1)&&
                !TextUtils.isEmpty(Val2)&&
                !TextUtils.isEmpty(Val3)&&
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
                !TextUtils.isEmpty(Val22)){
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
                    newPost.child("InformantAge").setValue(ageInformantVal);
                    newPost.child("HouseHoldSizeAdultMaleNameAge").setValue(HouseHoldSizeAdultMaleBeforeVal);
                    newPost.child("HouseHoldSizeAdultFemaleNameAge").setValue(HouseHoldSizeAdultFemaleBeforeVal);
                    newPost.child("HouseHoldSizeChildrenMaleNameAge").setValue(HouseHoldSizeChildrenMaleBeforeVal);
                    newPost.child("HouseHoldSizeChildrenFemaleNameAge").setValue(HouseHoldSizeChildrenFemaleBeforeVal);
                    newPost.child("HouseHoldSizeAdopted").setValue(AdoptedTextVal);
                    newPost.child("HouseHoldSizeAdoptedNumber").setValue(HouseHoldSizeAdoptedNumberVal);
                    newPost.child("HouseHoldSizeAdoptedNamesGenderAge").setValue(HouseHoldSizeAdoptedNamesVal);
                    newPost.child("HouseHoldConditionElderlyBefore").setValue(HouseHoldMemberConditionElderlyBeforeVal);
                    newPost.child("HouseHoldConditionDisabledBefore").setValue(HouseHoldMemberConditionDisabledBeforeVal);
                    newPost.child("HouseHoldConditionPregnantBefore").setValue(HouseHoldMemberConditionPregnantBeforeVal);
                    newPost.child("HouseHoldConditionLactatingBefore").setValue(HouseHoldMemberConditionLactatingBeforeVal);
                    newPost.child("HouseHoldConditionInfantsBefore").setValue(HouseHoldMemberConditionInfantBeforeVal);
                    newPost.child("HouseHoldConditionChildrenBefore").setValue(HouseHoldMemberConditionChildrenBeforeVal);
                    newPost.child("HouseHoldConditionElderlyAfter").setValue(HouseHoldMemberConditionElderlyAfterVal);
                    newPost.child("HouseHoldConditionDisabledAfter").setValue(HouseHoldMemberConditionDisabledAfterVal);
                    newPost.child("HouseHoldConditionPregnantAfter").setValue(HouseHoldMemberConditionPregnantAfterVal);
                    newPost.child("HouseHoldConditionLactatingAfter").setValue(HouseHoldMemberConditionLactatingAfterVal);
                    newPost.child("HouseHoldConditionInfantsAfter").setValue(HouseHoldMemberConditionInfantAfterVal);
                    newPost.child("HouseHoldConditionChildrenAfter").setValue(HouseHoldMemberConditionChildrenAfterVal);
                    newPost.child("HouseHoldLostAdultsMale").setValue(HouseHoldMemberAdultMaleLostAfterVal);
                    newPost.child("HouseHoldLostAdultsFemale").setValue(HouseHoldMemberAdultFemaleLostAfterVal);
                    newPost.child("HouseHoldLostChildren").setValue(HouseHoldMemberChildrenLostAfterVal);
                    newPost.child("QualificationBeforeInsurgency").setValue(Val1);
                    newPost.child("QualificationAfterInsurgency").setValue(Val2);
                    newPost.child("PrimaryOccupationOfTheRespondentBefore").setValue(Val3);
                    newPost.child("PrimaryOccupationOfTheRespondentAfter").setValue(Val4);
                    newPost.child("OtherOccupationOfTheRespondentBefore").setValue(Val5);
                    newPost.child("OtherOccupationOfTheRespondentAfter").setValue(Val6);
                    newPost.child("AnnualIncomeOfRespondentBefore").setValue(Val21);
                    newPost.child("AnnualIncomeOfRespondentAfter").setValue(Val22);
                    newPost.child("BoreHole").setValue(Val7);
                    newPost.child("CementWell").setValue(Val8);
                    newPost.child("EarthDam").setValue(Val9);
                    newPost.child("RiverOrStream").setValue(Val10);
                    newPost.child("Pond").setValue(Val11);
                    newPost.child("TreatmentPlant").setValue(Val12);
                    newPost.child("OtherWaterSource").setValue(Val13);
                    newPost.child("NationalGrid").setValue(Val14);
                    newPost.child("REBElectricity").setValue(Val15);
                    newPost.child("PrivatePlant").setValue(Val16);
                    newPost.child("SolarElectricity").setValue(Val17);
                    newPost.child("HouseholdBenefitFromOrganization").setValue(Val18);
                    newPost.child("WhichOrganizationAssistedYourHousehold").setValue(Val19);
                    newPost.child("WhatDidYouBenefitFromTheOrganization").setValue(Val20)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void YesAdopted(View view) {
        AdoptedText.setText("Yes");
    }

    public void NoAdopted(View view) {
        AdoptedText.setText("No");
    }
}
