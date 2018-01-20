package ng.com.androidlife.vsfproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HouseHold extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextInputEditText TownVillage, Longitude, Latitude, Status, Population, HouseHoldSizeAdultMaleBefore, HouseHoldSizeAdultFemaleBefore,
            HouseHoldSizeChildrenMaleBefore, HouseHoldSizeChildrenFemaleBefore, HouseHoldSizeAdultMaleAfter, HouseHoldSizeAdultFemaleAfter,
            HouseHoldSizeChildrenMaleAfter, HouseHoldSizeChildrenFemaleAfter, HouseHoldSizeAdoptedNumber, HouseHoldSizeAdoptedNames, HouseHoldMemberConditionElderlyBefore,
            HouseHoldMemberConditionDisabledBefore, HouseHoldMemberConditionPregnantBefore, HouseHoldMemberConditionLactatingBefore,
            HouseHoldMemberConditionInfantBefore, HouseHoldMemberConditionChildrenBefore, HouseHoldMemberConditionElderlyAfter,
            HouseHoldMemberConditionDisabledAfter, HouseHoldMemberConditionPregnantAfter, HouseHoldMemberConditionLactatingAfter,
            HouseHoldMemberConditionInfantAfter, HouseHoldMemberConditionChildrenAfter, HouseHoldMemberAdultMaleLostAfter, HouseHoldMemberAdultFemaleLostAfter,
            HouseHoldMemberChildrenLostAfter, MemberQualificationBefore, MemberQualificationAfter, MemberOtherOccupationBefore, MemberOtherOccupationAfter,
            OrganizationBenefit, OrganizationBenefitYes, OrganizationBenefitSpecify, Livelihood, LivelihoodBeforeDisplaced,
            LivelihoodAfterDisplaced, LivelihoodHowLong, LivelihoodTimes, LivelihoodYear, LivelihoodItemLost, LivelihoodHowMany, LivelihoodMemberNotRelocated,
            LivelihoodMemberReturned, LivelihoodReturneesCondition, LivelihoodReturneesAdjusting, LivelihoodReturneesAdjustingMeans, LivelihoodReturneesLivelyUnable,
            LivelihoodReturneesLivelyNeeds, LivelihoodInterventions, LivelihoodMedicalPsycho, LivelihoodReturneeAssistance, LivelihoodReturneesOrganizationAssistant, LivelihoodHouseholdSustainable;
    Button pushBtn;

    TextView genderBefore, genderAfter, genderInformant, AdoptedText, State, LocalGov, MemberOccupationBefore, MemberOccupationAfter,
            RespondentIncomeBefore, RespondentIncomeAfter, WaterSourcesText, ElectricitySource, WaterConditionsText, ElectricityCondition;

    TextView waterSourceText, waterConditionText;

    EditText ageBefore, ageAfter, ageInformant;

    private DatabaseReference mDatabase;

    static boolean isInitialized = false;
    private static String TAG = "HouseHoldActivity";

    @SuppressLint("WrongViewCast")
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

        //Spinner
        Spinner state = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> adapterState = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapterState);
        state.setOnItemSelectedListener(this);

        Spinner localGov = findViewById(R.id.localGovSpinner);
        ArrayAdapter<CharSequence> adapterLocal = ArrayAdapter.createFromResource(this, R.array.local_governments, android.R.layout.simple_spinner_item);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localGov.setAdapter(adapterLocal);
        localGov.setOnItemSelectedListener(this);

        Spinner occupationBefore = findViewById(R.id.OccupationBeforeSpinner);
        ArrayAdapter<CharSequence> adapterOccupationBefore = ArrayAdapter.createFromResource(this, R.array.occupation, android.R.layout.simple_spinner_item);
        adapterOccupationBefore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupationBefore.setAdapter(adapterOccupationBefore);
        occupationBefore.setOnItemSelectedListener(this);

        Spinner occupationAfter = findViewById(R.id.OccupationAfterSpinner);
        ArrayAdapter<CharSequence> adapteroccupationAfter = ArrayAdapter.createFromResource(this, R.array.occupation, android.R.layout.simple_spinner_item);
        adapteroccupationAfter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupationAfter.setAdapter(adapteroccupationAfter);
        occupationAfter.setOnItemSelectedListener(this);

        Spinner IncomeBefore = findViewById(R.id.RespondentIncomeBeforeSpinner);
        ArrayAdapter<CharSequence> adapterIncomeBefore = ArrayAdapter.createFromResource(this, R.array.income, android.R.layout.simple_spinner_item);
        adapterIncomeBefore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IncomeBefore.setAdapter(adapterIncomeBefore);
        IncomeBefore.setOnItemSelectedListener(this);

        Spinner IncomeAfter = findViewById(R.id.RespondentIncomeAfterSpinner);
        ArrayAdapter<CharSequence> adapterIncomeAfter = ArrayAdapter.createFromResource(this, R.array.income, android.R.layout.simple_spinner_item);
        adapterIncomeAfter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IncomeAfter.setAdapter(adapterIncomeAfter);
        IncomeAfter.setOnItemSelectedListener(this);

        Spinner WaterSource = findViewById(R.id.WaterSourceSpinner);
        ArrayAdapter<CharSequence> waterSource = ArrayAdapter.createFromResource(this, R.array.water_source, android.R.layout.simple_spinner_item);
        waterSource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterSource.setAdapter(waterSource);
        WaterSource.setOnItemSelectedListener(this);

        Spinner SourceElectricity = findViewById(R.id.ElectricitySourceSpinner);
        ArrayAdapter<CharSequence> adapterElectricity = ArrayAdapter.createFromResource(this, R.array.electricity, android.R.layout.simple_spinner_item);
        adapterElectricity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceElectricity.setAdapter(adapterElectricity);
        SourceElectricity.setOnItemSelectedListener(this);

        Spinner WaterCondition = findViewById(R.id.WaterConditionSpinner);
        ArrayAdapter<CharSequence> waterCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        waterCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterCondition.setAdapter(waterCondition);
        WaterCondition.setOnItemSelectedListener(this);

        Spinner ConditionElectricity = findViewById(R.id.ConditionElectricitySpinner);
        ArrayAdapter<CharSequence> conditionElectricity = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        conditionElectricity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ConditionElectricity.setAdapter(conditionElectricity);
        ConditionElectricity.setOnItemSelectedListener(this);

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

        waterSourceText = findViewById(R.id.waterSourceText);
        waterConditionText = findViewById(R.id.waterConditionText);
        ElectricitySource = findViewById(R.id.ElectricitySource);
        ElectricityCondition = findViewById(R.id.ConditionElectricity);


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

        State.setText("");
        LocalGov.setText("");
        TownVillage.setText("");
        Longitude.setText("");
        Latitude.setText("");
        Status.setText("");
        Population.setText("");

        genderBefore.setText("");
        genderAfter.setText("");
        ageBefore.setText("");
        ageAfter.setText("");
        genderInformant.setText("");
        ageInformant.setText("");

        HouseHoldSizeAdultMaleBefore.setText("");
        HouseHoldSizeAdultFemaleBefore.setText("");
        HouseHoldSizeChildrenMaleBefore.setText("");
        HouseHoldSizeChildrenFemaleBefore.setText("");
        HouseHoldSizeAdultMaleAfter.setText("");
        HouseHoldSizeAdultFemaleAfter.setText("");
        HouseHoldSizeChildrenMaleAfter.setText("");
        HouseHoldSizeChildrenFemaleAfter.setText("");
        AdoptedText = findViewById(R.id.AdoptedText);
        HouseHoldSizeAdoptedNumber.setText("");
        HouseHoldSizeAdoptedNames.setText("");
        HouseHoldMemberConditionElderlyBefore.setText("");
        HouseHoldMemberConditionDisabledBefore.setText("");
        HouseHoldMemberConditionPregnantBefore.setText("");
        HouseHoldMemberConditionLactatingBefore.setText("");
        HouseHoldMemberConditionInfantBefore.setText("");
        HouseHoldMemberConditionChildrenBefore.setText("");
        HouseHoldMemberConditionElderlyAfter.setText("");
        HouseHoldMemberConditionDisabledAfter.setText("");
        HouseHoldMemberConditionPregnantAfter.setText("");
        HouseHoldMemberConditionLactatingAfter.setText("");
        HouseHoldMemberConditionInfantAfter.setText("");
        HouseHoldMemberConditionChildrenAfter.setText("");
        HouseHoldMemberAdultMaleLostAfter.setText("");
        HouseHoldMemberAdultFemaleLostAfter.setText("");
        HouseHoldMemberChildrenLostAfter.setText("");

        MemberQualificationBefore.setText("");
        MemberQualificationAfter.setText("");
        MemberOccupationBefore.setText("");
        MemberOccupationAfter.setText("");
        MemberOtherOccupationBefore.setText("");
        MemberOtherOccupationAfter.setText("");
        RespondentIncomeBefore.setText("");
        RespondentIncomeAfter.setText("");

        ElectricitySource.setText("");
        ElectricityCondition.setText("");
        waterSourceText.setText("");
        waterConditionText.setText("");

        OrganizationBenefit.setText("");
        OrganizationBenefitYes.setText("");
        OrganizationBenefitSpecify.setText("");

        Livelihood.setText("");
        LivelihoodBeforeDisplaced.setText("");
        LivelihoodAfterDisplaced.setText("");
        LivelihoodHowLong.setText("");
        LivelihoodTimes.setText("");
        LivelihoodYear.setText("");
        LivelihoodItemLost.setText("");
        LivelihoodHowMany.setText("");
        LivelihoodMemberNotRelocated.setText("");
        LivelihoodMemberReturned.setText("");
        LivelihoodReturneesCondition.setText("");
        LivelihoodReturneesAdjusting.setText("");
        LivelihoodReturneesAdjustingMeans.setText("");
        LivelihoodReturneesLivelyUnable.setText("");
        LivelihoodReturneesLivelyNeeds.setText("");
        LivelihoodInterventions.setText("");
        LivelihoodMedicalPsycho.setText("");
        LivelihoodReturneeAssistance.setText("");
        LivelihoodReturneesOrganizationAssistant.setText("");
        LivelihoodHouseholdSustainable.setText("");
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
        final String Val9 = WaterSourcesText.getText().toString().trim();
        final String Val10 = WaterConditionsText.getText().toString().trim();

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
                                Toast.makeText(HouseHold.this,"Your Data Is Stored To CloudDatabase", Toast.LENGTH_SHORT).show();
                            }else {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.stateSpinner){
            String state = parent.getItemAtPosition(position).toString();
            State.setText(state);
        }else if (spinner.getId() == R.id.localGovSpinner){
            String localGov = parent.getItemAtPosition(position).toString();
            LocalGov.setText(localGov);
        }else if (spinner.getId() == R.id.OccupationBeforeSpinner){
            String memberOccupationBefore = parent.getItemAtPosition(position).toString();
            MemberOccupationBefore.setText(memberOccupationBefore);
        }else if (spinner.getId() == R.id.OccupationAfterSpinner){
            String memberOccupationAfter = parent.getItemAtPosition(position).toString();
            MemberOccupationAfter.setText(memberOccupationAfter);
        }else if (spinner.getId() == R.id.RespondentIncomeBeforeSpinner){
            String memberIncomeBeforer = parent.getItemAtPosition(position).toString();
            RespondentIncomeBefore.setText(memberIncomeBeforer);
        }else if (spinner.getId() == R.id.RespondentIncomeAfterSpinner){
            String memberIncomeAfter = parent.getItemAtPosition(position).toString();
            RespondentIncomeAfter.setText(memberIncomeAfter);
        }else if (spinner.getId() == R.id.WaterSourceSpinner){
            String WaterSource = parent.getItemAtPosition(position).toString();
            waterSourceText.setText(WaterSource);
        }else if (spinner.getId() == R.id.WaterConditionSpinner){
            String WaterCondition = parent.getItemAtPosition(position).toString();
            waterConditionText.setText(WaterCondition);
        }else if (spinner.getId() == R.id.ElectricitySourceSpinner){
            String electricitySource = parent.getItemAtPosition(position).toString();
            ElectricitySource.setText(electricitySource);
        }else if (spinner.getId() == R.id.ConditionElectricitySpinner){
            String electricityCondition = parent.getItemAtPosition(position).toString();
            ElectricityCondition.setText(electricityCondition);
        }


        /**String state = parent.getItemAtPosition(position).toString();
        State.setText(state);

        String localGov = parent.getItemAtPosition(position).toString();
        LocalGov.setText(localGov);**/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
