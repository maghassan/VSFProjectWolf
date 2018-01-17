package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
            MemberOtherOccupationAfter, WaterSource, ElectricitySource, WaterCondition, ElectricityCondition,
            OrganizationBenefit, OrganizationBenefitYes, OrganizationBenefitSpecify, RespondentIncomeBefore, RespondentIncomeAfter, Livelihood, LivelihoodBeforeDisplaced,
            LivelihoodAfterDisplaced, LivelihoodHowLong, LivelihoodTimes, LivelihoodYear, LivelihoodItemLost, LivelihoodHowMany, LivelihoodMemberNotRelocated,
            LivelihoodMemberReturned, LivelihoodReturneesCondition, LivelihoodReturneesAdjusting, LivelihoodReturneesAdjustingMeans, LivelihoodReturneesLivelyUnable,
            LivelihoodReturneesLivelyNeeds, LivelihoodInterventions, LivelihoodMedicalPsycho, LivelihoodReturneeAssistance, LivelihoodReturneesOrganizationAssistant, LivelihoodHouseholdSustainable;
    Button pushBtn;
    TextView genderBefore, genderAfter, genderInformant, AdoptedText;
    EditText ageBefore, ageAfter, ageInformant;

    private DatabaseReference mDatabase;

    static boolean isInitialized = false;
    private static String TAG = "HouseHoldActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold);

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

        ElectricitySource = findViewById(R.id.ElectricitySource);
        ElectricityCondition = findViewById(R.id.ConditionElectricity);
        WaterSource = findViewById(R.id.WaterSource);
        WaterCondition = findViewById(R.id.WaterCondition);

        OrganizationBenefit = findViewById(R.id.OrganizationBenefit);
        OrganizationBenefitYes = findViewById(R.id.OrganizationBenefitYes);
        OrganizationBenefitSpecify = findViewById(R.id.OrganizationBenefitSpecify);

        //Livelihood
        Livelihood = findViewById(R.id.Livelihood);
        LivelihoodBeforeDisplaced = findViewById(R.id.LivelihoodBeforeDisplaced);
        LivelihoodAfterDisplaced = findViewById(R.id.LivelihoodAfterDisplaced);
        LivelihoodHowLong = findViewById(R.id.LivelihoodHowLong);
        LivelihoodTimes = findViewById(R.id.LivelihoodTimes);
        LivelihoodYear = findViewById(R.id.LivelihoodYear);
        LivelihoodItemLost = findViewById(R.id.LivelihoodItemLost);
        LivelihoodHowMany = findViewById(R.id.LivelihoodHowMany);
        LivelihoodMemberNotRelocated = findViewById(R.id.LivelihoodMemberNotRelocated);
        LivelihoodMemberReturned = findViewById(R.id.LivelihoodMemberReturned);
        LivelihoodReturneesCondition = findViewById(R.id.LivelihoodReturneesCondition);
        LivelihoodReturneesAdjusting = findViewById(R.id.LivelihoodReturneesAdjusting);
        LivelihoodReturneesAdjustingMeans = findViewById(R.id.LivelihoodReturneesAdjustingMeans);
        LivelihoodReturneesLivelyUnable = findViewById(R.id.LivelihoodReturneesLivelyUnable);
        LivelihoodReturneesLivelyNeeds = findViewById(R.id.LivelihoodReturneesLivelyNeeds);
        LivelihoodInterventions = findViewById(R.id.LivelihoodInterventions);
        LivelihoodMedicalPsycho = findViewById(R.id.LivelihoodMedicalPsycho);
        LivelihoodReturneeAssistance = findViewById(R.id.LivelihoodReturneeAssistance);
        LivelihoodReturneesOrganizationAssistant = findViewById(R.id.LivelihoodReturneesOrganizationAssistant);
        LivelihoodHouseholdSustainable = findViewById(R.id.LivelihoodHouseholdSustainable);

        pushBtn = findViewById(R.id.pushBtn);
    }

    public void Submit(View view) {
        Submit();
    }

    private void Submit() {

        final String Val23 = Livelihood.getText().toString().trim();
        final String Val24 = LivelihoodBeforeDisplaced.getText().toString().trim();
        final String Val25 = LivelihoodAfterDisplaced.getText().toString().trim();
        final String Val26 = LivelihoodHowLong.getText().toString().trim();
        final String Val27 = LivelihoodTimes.getText().toString().trim();
        final String Val28 = LivelihoodYear.getText().toString().trim();
        final String Val29 = LivelihoodItemLost.getText().toString().trim();
        final String Val30 = LivelihoodHowMany.getText().toString().trim();
        final String Val31 = LivelihoodMemberNotRelocated.getText().toString().trim();
        final String Val32 = LivelihoodMemberReturned.getText().toString().trim();
        final String Val33 = LivelihoodReturneesCondition.getText().toString().trim();
        final String Val34 = LivelihoodReturneesAdjusting.getText().toString().trim();
        final String Val35 = LivelihoodReturneesAdjustingMeans.getText().toString().trim();
        final String Val36 = LivelihoodReturneesLivelyUnable.getText().toString().trim();
        final String Val37 = LivelihoodReturneesLivelyNeeds.getText().toString().trim();
        final String Val38 = LivelihoodInterventions.getText().toString().trim();
        final String Val39 = LivelihoodMedicalPsycho.getText().toString().trim();
        final String Val40 = LivelihoodReturneeAssistance.getText().toString().trim();
        final String Val41 = LivelihoodReturneesOrganizationAssistant.getText().toString().trim();
        final String Val42 = LivelihoodHouseholdSustainable.getText().toString().trim();

        final String Val1 = MemberQualificationBefore.getText().toString().trim();
        final String Val2 = MemberQualificationAfter.getText().toString().trim();
        final String Val3 = MemberOccupationBefore.getText().toString().trim();
        final String Val4 = MemberOccupationAfter.getText().toString().trim();
        final String Val5 = MemberOtherOccupationBefore.getText().toString().trim();
        final String Val6 = MemberOtherOccupationAfter.getText().toString().trim();

        final String Val7 = ElectricitySource.getText().toString().trim();
        final String Val8 = ElectricityCondition.getText().toString().trim();
        final String Val9 = WaterSource.getText().toString().trim();
        final String Val10 = WaterCondition.getText().toString().trim();

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
                !TextUtils.isEmpty(Val18)&&
                !TextUtils.isEmpty(Val19)&&
                !TextUtils.isEmpty(Val20)&&
                !TextUtils.isEmpty(Val21)&&
                !TextUtils.isEmpty(Val22)&&
                !TextUtils.isEmpty(Val23)&&
                !TextUtils.isEmpty(Val24)&&
                !TextUtils.isEmpty(Val25)&&
                !TextUtils.isEmpty(Val26)&&
                !TextUtils.isEmpty(Val27)&&
                !TextUtils.isEmpty(Val28)&&
                !TextUtils.isEmpty(Val29)&&
                !TextUtils.isEmpty(Val30)&&
                !TextUtils.isEmpty(Val31)&&
                !TextUtils.isEmpty(Val32)&&
                !TextUtils.isEmpty(Val33)&&
                !TextUtils.isEmpty(Val34)&&
                !TextUtils.isEmpty(Val35)&&
                !TextUtils.isEmpty(Val36)&&
                !TextUtils.isEmpty(Val37)&&
                !TextUtils.isEmpty(Val38)&&
                !TextUtils.isEmpty(Val39)&&
                !TextUtils.isEmpty(Val40)&&
                !TextUtils.isEmpty(Val41)&&
                !TextUtils.isEmpty(Val42)){
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

                    newPost.child("HouseHoldSizeAdultMaleNameAgeBefore").setValue(HouseHoldSizeAdultMaleBeforeVal);
                    newPost.child("HouseHoldSizeAdultFemaleNameAgeBefore").setValue(HouseHoldSizeAdultFemaleBeforeVal);
                    newPost.child("HouseHoldSizeChildrenMaleNameAgeBefore").setValue(HouseHoldSizeChildrenMaleBeforeVal);
                    newPost.child("HouseHoldSizeChildrenFemaleNameAgeBefore").setValue(HouseHoldSizeChildrenFemaleBeforeVal);

                    newPost.child("HouseHoldSizeAdultMaleNameAgeAfter").setValue(HouseHoldSizeAdultMaleAfterVal);
                    newPost.child("HouseHoldSizeAdultFemaleNameAgeAfter").setValue(HouseHoldSizeAdultFemaleAfterVal);
                    newPost.child("HouseHoldSizeChildrenMaleNameAgeAfter").setValue(HouseHoldSizeChildrenMaleAfterVal);
                    newPost.child("HouseHoldSizeChildrenFemaleNameAgeAfter").setValue(HouseHoldSizeChildrenFemaleAfterVal);

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
                    newPost.child("ElectricitySource").setValue(Val7);
                    newPost.child("ElectricityCondition").setValue(Val8);
                    newPost.child("WaterSource").setValue(Val9);
                    newPost.child("WaterCondition").setValue(Val10);
                    newPost.child("HouseholdBenefitFromOrganization").setValue(Val18);
                    newPost.child("WhichOrganizationAssistedYourHousehold").setValue(Val19);
                    newPost.child("WhatDidYouBenefitFromTheOrganization").setValue(Val20);
                    newPost.child("Livelihood").setValue(Val23);
                    newPost.child("LivelihoodBeforeDisplaced").setValue(Val24);
                    newPost.child("LivelihoodAfterDisplaced").setValue(Val25);
                    newPost.child("LivelihoodHowLong").setValue(Val26);
                    newPost.child("LivelihoodTimes").setValue(Val27);
                    newPost.child("LivelihoodYear").setValue(Val28);
                    newPost.child("LivelihoodItemLost").setValue(Val29);
                    newPost.child("LivelihoodHowMany").setValue(Val30);
                    newPost.child("LivelihoodMemberNotRelocated").setValue(Val31);
                    newPost.child("LivelihoodMemberReturned").setValue(Val32);
                    newPost.child("LivelihoodReturneesCondition").setValue(Val33);
                    newPost.child("LivelihoodReturneesAdjusting").setValue(Val34);
                    newPost.child("LivelihoodReturneesAdjustingMeans").setValue(Val35);
                    newPost.child("LivelihoodReturneesLivelyUnable").setValue(Val36);
                    newPost.child("LivelihoodReturneesLivelyNeeds").setValue(Val37);
                    newPost.child("LivelihoodInterventions").setValue(Val38);
                    newPost.child("LivelihoodMedicalPsycho").setValue(Val39);
                    newPost.child("LivelihoodReturneeAssistance").setValue(Val40);
                    newPost.child("LivelihoodReturneesOrganizationAssistant").setValue(Val41);
                    newPost.child("LivelihoodHouseholdSustainable").setValue(Val42)
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

    public void SubmitAgain(View view) {
        Intent NewData = new Intent(this, HouseHold.class);
        startActivity(NewData);
    }

    public void BackToMenu(View view) {
        Intent BackToMenu = new Intent(this, MenuScreen.class);
        startActivity(BackToMenu);
    }
}
