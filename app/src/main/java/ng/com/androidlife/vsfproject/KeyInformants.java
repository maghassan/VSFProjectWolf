package ng.com.androidlife.vsfproject;

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

public class KeyInformants extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText  TownVillage, Longitude, Latitude, Status, Population, COMMUNITYSizeAdultMaleBefore, COMMUNITYSizeAdultFemaleBefore,
            COMMUNITYSizeChildrenMaleBefore, COMMUNITYSizeChildrenFemaleBefore, COMMUNITYSizeAdultMaleAfter, COMMUNITYSizeAdultFemaleAfter, COMMUNITYSizeChildrenMaleAfter,
            COMMUNITYSizeChildrenFemaleAfter, COMMUNITYMemberConditionElderlyBefore, COMMUNITYMemberConditionDisabledBefore, COMMUNITYMemberConditionPregnantBefore,
            COMMUNITYMemberConditionLactatingBefore, COMMUNITYMemberConditionInfantBefore, COMMUNITYMemberConditionChildrenBefore, COMMUNITYMemberConditionElderlyAfter,
            COMMUNITYMemberConditionDisabledAfter, COMMUNITYMemberConditionPregnantAfter, COMMUNITYMemberConditionLactatingAfter, COMMUNITYMemberConditionInfantAfter,
            COMMUNITYMemberConditionChildrenAfter, AttackedDeath, AttackedMissing, AttacksNumber, COMMUNITYMemberAdultMaleLostAfter, COMMUNITYMemberAdultFemaleLostAfter,
            COMMUNITYMemberChildrenLostAfter, OtherSchool,
            OtherSchoolClassRoom,
            Vigelante, OtherSecurity,
            OtherCourt, OtherBank, InsuranceCompanies, Factories, OtherMarket, Hotels, Tourist, Mosques,
            Church, Shrine, NGOsCBOs;

    EditText ageCOMMUNITYBefore, ageCOMMUNITYAfter, CommunityInformantAge;

    TextView genderBefore, genderAfter, genderInformant, LGASecretariat, State, LocalGov, EmirPalace, KeyMemberOtherOccupationAfter, DHsPalace, VillageHeadPalace,
            TownHall, PlayGroundCenter, Primary, Secondary, Technical, Commercial, Tertiary, PrimaryClassRoom, SecondaryClassRoom, TechnicalClassRoom,
            CommercialClassRoom, TertiaryClassRoom, IslamiyaClassRoom, Banks, BanksCondition,
            PrimaryWorkshop, SecondaryWorkshop, TechnicalWorkshop, CommercialWorkshop, TertiaryWorkshop, IslamiyaWorkshop, OtherSchoolWorkshop,
            MotoParkText, DailyMarket, WeeklyMarket, LivestockMarket, SuperMarkets, FireService, PrintMedia, SocialMedia, PrivateTelevisionService,
            ArmyFormationAndCommand, ArmyBarracks, PoliceStation, PoliceBarracks, DeptStateServices, CivilDefence, ImmigrationService, CustomService,
            StaffPrimary, StaffSecondary, StaffTechnical, StaffCommercial, StaffTertiary, StaffIslamiya, StaffOther,
            NonStaffPrimary, NonStaffSecondary, NonStaffTechnical, NonStaffCommercial, NonStaffTertiary, NonStaffIslamiya, NonStaffOther, SportingPrimary, SportingSecondary,
            SportingTechnical, SportingCommercial, SportingTertiary, SportingIslamiya, SportingOther, OtherStaffHousing, StudentHostel, OtherToiletFacilities,
            PerimeterFence, OwnerShipPrimary, OwnerShipSecondary, OwnerShipTechnical, OwnerShipCommercial, OwnerShipTertiary, OwnerShipIslamiya, OwnerShipOther,
            HealthPost, HealthCenter, PrimaryHealthCenter, PrivateHospital, GeneralHospital, FMCSpecialistHospital, OtherHospitals, OperationTheater, Laboratories,
            Mortuary, Ambulances, Maternity, NutritionalServices, Pharmaceuticals, StaffAccommodation, ElectricitySupply, ColdStore, Doctors, Nurses, Midwives, NursesMidwives,
            Pharmacists, Radiographers, PharmacyTechnicians, MedicalLabScientist, PharmacyAssistant, LaboratoryTechnicians, LaboratoryAssistant, HealthSocialWorkers,
            Nutritionist, PublicHealthOfficers, CommunityHealthOfficers, CommunityHealth, ExtensionOfficers, EnvironmentalHealthOfficers, PsychSocial, TraditionalMidwives,
            SourceWater, Condition, ElectricitySource, ElectricityCondition, Roads, RoadsCondition, Railway, RailwayConditions, RailwayFlowSchedule, Airport, AirportConditions,
            SeaTransport, SeaConditions, Communication, InternationalRadio, FederalRadio, StateRadio, PrivateRadio, SateliteTelevision, NTAService, StateTelevisionStation,
            PrisonService, FederalHighCourt, ShariaCourtOfAppeal, IndustrialCourt, StateHighCourt, MagistrateCourt, CustomaryCourt, Communication1, Communication2, Communication3, Communication4,
            KeyMemberQualificationBefore, KeyMemberQualificationAfter, KeyMemberOccupationBefore, KeyMemberOccupationAfter, KeyMemberOtherOccupationBefore;

    private DatabaseReference mDatabase;

    static boolean isInitialized = false;
    private static String TAG = "KeyInformantActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_informants);

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

        mDatabase = FirebaseDatabase.getInstance().getReference().child("KeyInformant");
        mDatabase.keepSynced(true);

        ageCOMMUNITYBefore = findViewById(R.id.ageCOMMUNITYBefore);
        ageCOMMUNITYAfter = findViewById(R.id.ageCOMMUNITYAfter);
        CommunityInformantAge = findViewById(R.id.CommunityInformantAge);

        genderBefore = findViewById(R.id.COMMUNITYGenderBefore);
        genderAfter = findViewById(R.id.COMMUNITYGenderAfter);
        genderInformant = findViewById(R.id.InformantGenderText);


        Spinner state = findViewById(R.id.StateSpinner);
        ArrayAdapter<CharSequence> adapterState = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapterState);
        state.setOnItemSelectedListener(this);

        Spinner localGov = findViewById(R.id.LocalGovSpinner);
        ArrayAdapter<CharSequence> adapterLocal = ArrayAdapter.createFromResource(this, R.array.local_governments, android.R.layout.simple_spinner_item);
        adapterLocal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localGov.setAdapter(adapterLocal);
        localGov.setOnItemSelectedListener(this);

        Spinner occupationBefore = findViewById(R.id.cOccupationBeforeSpinner);
        ArrayAdapter<CharSequence> adapterOccupationBefore = ArrayAdapter.createFromResource(this, R.array.occupation, android.R.layout.simple_spinner_item);
        adapterOccupationBefore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupationBefore.setAdapter(adapterOccupationBefore);
        occupationBefore.setOnItemSelectedListener(this);

        Spinner occupationAfter = findViewById(R.id.cOccupationAfterSpinner);
        ArrayAdapter<CharSequence> adapteroccupationAfter = ArrayAdapter.createFromResource(this, R.array.occupation, android.R.layout.simple_spinner_item);
        adapteroccupationAfter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupationAfter.setAdapter(adapteroccupationAfter);
        occupationAfter.setOnItemSelectedListener(this);

        Spinner WaterSource = findViewById(R.id.cWaterSourceSpinner);
        ArrayAdapter<CharSequence> waterSource = ArrayAdapter.createFromResource(this, R.array.water_source, android.R.layout.simple_spinner_item);
        waterSource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterSource.setAdapter(waterSource);
        WaterSource.setOnItemSelectedListener(this);

        Spinner WaterSourceCondition = findViewById(R.id.cWaterConditionSpinner);
        ArrayAdapter<CharSequence> waterSourceCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        waterSourceCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WaterSourceCondition.setAdapter(waterSourceCondition);
        WaterSourceCondition.setOnItemSelectedListener(this);

        Spinner SourceElectricity = findViewById(R.id.cElectricitySourceSpinner);
        ArrayAdapter<CharSequence> adapterElectricity = ArrayAdapter.createFromResource(this, R.array.electricity, android.R.layout.simple_spinner_item);
        adapterElectricity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceElectricity.setAdapter(adapterElectricity);
        SourceElectricity.setOnItemSelectedListener(this);

        Spinner SourceElectricityCondition = findViewById(R.id.cConditionElectricitySpinner);
        ArrayAdapter<CharSequence> adapterElectricityCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterElectricityCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceElectricityCondition.setAdapter(adapterElectricityCondition);
        SourceElectricityCondition.setOnItemSelectedListener(this);

        Spinner SourceLGASecretariatCondition = findViewById(R.id.LGASecretariatSpinner);
        ArrayAdapter<CharSequence> adapterLGASecretariatCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterLGASecretariatCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceLGASecretariatCondition.setAdapter(adapterLGASecretariatCondition);
        SourceLGASecretariatCondition.setOnItemSelectedListener(this);

        Spinner SourceEmirPalaceCondition = findViewById(R.id.EmirSpinner);
        ArrayAdapter<CharSequence> adapterEmirPalaceCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterEmirPalaceCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceEmirPalaceCondition.setAdapter(adapterEmirPalaceCondition);
        SourceEmirPalaceCondition.setOnItemSelectedListener(this);

        Spinner SourceDHsPalaceCondition = findViewById(R.id.DHsPalaceSpinner);
        ArrayAdapter<CharSequence> adapterDHsPalaceCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterDHsPalaceCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceDHsPalaceCondition.setAdapter(adapterDHsPalaceCondition);
        SourceDHsPalaceCondition.setOnItemSelectedListener(this);

        Spinner SourceVillageHeadsPalaceCondition = findViewById(R.id.VillageHeadPalaceSpinner);
        ArrayAdapter<CharSequence> adapterVillageHeadsPalaceCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterVillageHeadsPalaceCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceVillageHeadsPalaceCondition.setAdapter(adapterVillageHeadsPalaceCondition);
        SourceVillageHeadsPalaceCondition.setOnItemSelectedListener(this);

        Spinner SourceTownHallCondition = findViewById(R.id.TownHallSpinner);
        ArrayAdapter<CharSequence> adapterTownHallCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterTownHallCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceTownHallCondition.setAdapter(adapterTownHallCondition);
        SourceTownHallCondition.setOnItemSelectedListener(this);

        Spinner SourcePlayGroundCondition = findViewById(R.id.PlayGroundCenterSpinner);
        ArrayAdapter<CharSequence> adapterPlayGroundCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterPlayGroundCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcePlayGroundCondition.setAdapter(adapterPlayGroundCondition);
        SourcePlayGroundCondition.setOnItemSelectedListener(this);

        Spinner SourceaPrimaryCondition = findViewById(R.id.PrimarySpinner);
        ArrayAdapter<CharSequence> adapteraPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteraPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceaPrimaryCondition.setAdapter(adapteraPrimaryCondition);
        SourceaPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SourceaSecondaryCondition = findViewById(R.id.SecondarySpinner);
        ArrayAdapter<CharSequence> adapteraSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteraSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceaSecondaryCondition.setAdapter(adapteraSecondaryCondition);
        SourceaSecondaryCondition.setOnItemSelectedListener(this);

        Spinner SourceaTechnicalCondition = findViewById(R.id.TechnicalSpinner);
        ArrayAdapter<CharSequence> adapteraTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteraTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceaTechnicalCondition.setAdapter(adapteraTechnicalCondition);
        SourceaTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SourceaCommercialCondition = findViewById(R.id.CommercialSpinner);
        ArrayAdapter<CharSequence> adapteraCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteraCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceaCommercialCondition.setAdapter(adapteraCommercialCondition);
        SourceaCommercialCondition.setOnItemSelectedListener(this);

        Spinner SourceTertiaryCondition = findViewById(R.id.TertiarySpinner);
        ArrayAdapter<CharSequence> adapterTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourceTertiaryCondition.setAdapter(adapterTertiaryCondition);
        SourceTertiaryCondition.setOnItemSelectedListener(this);


        Spinner SourcebPrimaryCondition = findViewById(R.id.ClassroomPrimarySpinner);
        ArrayAdapter<CharSequence> adapterbPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebPrimaryCondition.setAdapter(adapterbPrimaryCondition);
        SourcebPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SourcebSecondaryCondition = findViewById(R.id.ClassroomSecondarySpinner);
        ArrayAdapter<CharSequence> adapterbSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebSecondaryCondition.setAdapter(adapterbSecondaryCondition);
        SourcebSecondaryCondition.setOnItemSelectedListener(this);

        Spinner SourcebTechnicalCondition = findViewById(R.id.ClassroomTechnicalSpinner);
        ArrayAdapter<CharSequence> adapterbTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebTechnicalCondition.setAdapter(adapterbTechnicalCondition);
        SourcebTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SourcebCommercialCondition = findViewById(R.id.ClassroomCommercialSpinner);
        ArrayAdapter<CharSequence> adapterbCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebCommercialCondition.setAdapter(adapterbCommercialCondition);
        SourcebCommercialCondition.setOnItemSelectedListener(this);

        Spinner SourcebTertiaryCondition = findViewById(R.id.ClassroomTertiarySpinner);
        ArrayAdapter<CharSequence> adapterbTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebTertiaryCondition.setAdapter(adapterbTertiaryCondition);
        SourcebTertiaryCondition.setOnItemSelectedListener(this);

        Spinner SourcebIslamiyaCondition = findViewById(R.id.ClassroomIslamiyaSpinner);
        ArrayAdapter<CharSequence> adapterbIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterbIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcebIslamiyaCondition.setAdapter(adapterbIslamiyaCondition);
        SourcebIslamiyaCondition.setOnItemSelectedListener(this);


        Spinner SourcecPrimaryCondition = findViewById(R.id.LaboratoriesPrimarySpinner);
        ArrayAdapter<CharSequence> adaptercPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecPrimaryCondition.setAdapter(adaptercPrimaryCondition);
        SourcecPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SourcecSecondaryCondition = findViewById(R.id.LaboratoriesSecondarySpinner);
        ArrayAdapter<CharSequence> adaptercSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecSecondaryCondition.setAdapter(adaptercSecondaryCondition);
        SourcecSecondaryCondition.setOnItemSelectedListener(this);

        Spinner SourcecTechnicalCondition = findViewById(R.id.LaboratoriesTechnicalSpinner);
        ArrayAdapter<CharSequence> adaptercTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecTechnicalCondition.setAdapter(adaptercTechnicalCondition);
        SourcecTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SourcecCommercialCondition = findViewById(R.id.LaboratoriesCommercialSpinner);
        ArrayAdapter<CharSequence> adaptercCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecCommercialCondition.setAdapter(adaptercCommercialCondition);
        SourcecCommercialCondition.setOnItemSelectedListener(this);

        Spinner SourcecTertiaryCondition = findViewById(R.id.LaboratoriesTertiarySpinner);
        ArrayAdapter<CharSequence> adaptercTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecTertiaryCondition.setAdapter(adaptercTertiaryCondition);
        SourcecTertiaryCondition.setOnItemSelectedListener(this);

        Spinner SourcecIslamiyaCondition = findViewById(R.id.LaboratoriesIslamiyaSpinner);
        ArrayAdapter<CharSequence> adaptercIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SourcecIslamiyaCondition.setAdapter(adaptercIslamiyaCondition);
        SourcecIslamiyaCondition.setOnItemSelectedListener(this);


        Spinner StaffPrimaryCondition = findViewById(R.id.TeachersPrimarySpinner);
        ArrayAdapter<CharSequence> adapterdPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffPrimaryCondition.setAdapter(adapterdPrimaryCondition);
        StaffPrimaryCondition.setOnItemSelectedListener(this);

        Spinner StaffSecondaryCondition = findViewById(R.id.TeachersSecondarySpinner);
        ArrayAdapter<CharSequence> adapterdSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffSecondaryCondition.setAdapter(adapterdSecondaryCondition);
        StaffSecondaryCondition.setOnItemSelectedListener(this);

        Spinner StaffTechnicalCondition = findViewById(R.id.TeachersTechnicalSpinner);
        ArrayAdapter<CharSequence> adapterdTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffTechnicalCondition.setAdapter(adapterdTechnicalCondition);
        StaffTechnicalCondition.setOnItemSelectedListener(this);

        Spinner StaffCommercialCondition = findViewById(R.id.TeachersCommercialSpinner);
        ArrayAdapter<CharSequence> adapterdCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffCommercialCondition.setAdapter(adapterdCommercialCondition);
        StaffCommercialCondition.setOnItemSelectedListener(this);

        Spinner StaffTertiaryCondition = findViewById(R.id.TeachersTertiarySpinner);
        ArrayAdapter<CharSequence> adapterdTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffTertiaryCondition.setAdapter(adapterdTertiaryCondition);
        StaffTertiaryCondition.setOnItemSelectedListener(this);

        Spinner StaffIslamiyaCondition = findViewById(R.id.TeachersIslamiyaSpinner);
        ArrayAdapter<CharSequence> adapterdIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterdIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StaffIslamiyaCondition.setAdapter(adapterdIslamiyaCondition);
        StaffIslamiyaCondition.setOnItemSelectedListener(this);


        Spinner NonStaffPrimaryCondition = findViewById(R.id.NonStaffPrimarySpinner);
        ArrayAdapter<CharSequence> adapterePrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterePrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffPrimaryCondition.setAdapter(adapterePrimaryCondition);
        NonStaffPrimaryCondition.setOnItemSelectedListener(this);

        Spinner NonStaffSecondaryCondition = findViewById(R.id.NonStaffSecondarySpinner);
        ArrayAdapter<CharSequence> adaptereSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptereSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffSecondaryCondition.setAdapter(adaptereSecondaryCondition);
        NonStaffSecondaryCondition.setOnItemSelectedListener(this);

        Spinner NonStaffTechnicalCondition = findViewById(R.id.NonStaffTechnicalSpinner);
        ArrayAdapter<CharSequence> adaptereTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptereTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffTechnicalCondition.setAdapter(adaptereTechnicalCondition);
        NonStaffTechnicalCondition.setOnItemSelectedListener(this);

        Spinner NonStaffCommercialCondition = findViewById(R.id.NonStaffCommercialSpinner);
        ArrayAdapter<CharSequence> adaptereCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptereCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffCommercialCondition.setAdapter(adaptereCommercialCondition);
        NonStaffCommercialCondition.setOnItemSelectedListener(this);

        Spinner NonStaffTertiaryCondition = findViewById(R.id.NonStaffTertiarySpinner);
        ArrayAdapter<CharSequence> adaptereTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptereTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffTertiaryCondition.setAdapter(adaptereTertiaryCondition);
        NonStaffTertiaryCondition.setOnItemSelectedListener(this);

        Spinner NonStaffIslamiyaCondition = findViewById(R.id.NonStaffIslamiyaSpinner);
        ArrayAdapter<CharSequence> adaptereIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptereIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NonStaffIslamiyaCondition.setAdapter(adaptereIslamiyaCondition);
        NonStaffIslamiyaCondition.setOnItemSelectedListener(this);


        Spinner SportingPrimaryCondition = findViewById(R.id.SportingPrimarySpinner);
        ArrayAdapter<CharSequence> adapterfPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingPrimaryCondition.setAdapter(adapterfPrimaryCondition);
        SportingPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SportingSecondaryCondition = findViewById(R.id.SportingSecondarySpinner);
        ArrayAdapter<CharSequence> adapterfSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingSecondaryCondition.setAdapter(adapterfSecondaryCondition);
        SportingSecondaryCondition.setOnItemSelectedListener(this);

        Spinner SportingTechnicalCondition = findViewById(R.id.SportingTechnicalSpinner);
        ArrayAdapter<CharSequence> adapterfTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingTechnicalCondition.setAdapter(adapterfTechnicalCondition);
        SportingTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SportingCommercialCondition = findViewById(R.id.SportingCommercialSpinner);
        ArrayAdapter<CharSequence> adapterfCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingCommercialCondition.setAdapter(adapterfCommercialCondition);
        SportingCommercialCondition.setOnItemSelectedListener(this);

        Spinner SportingTertiaryCondition = findViewById(R.id.SportingTertiarySpinner);
        ArrayAdapter<CharSequence> adapterfTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingTertiaryCondition.setAdapter(adapterfTertiaryCondition);
        SportingTertiaryCondition.setOnItemSelectedListener(this);

        Spinner SportingIslamiyaCondition = findViewById(R.id.SportingIslamiyaSpinner);
        ArrayAdapter<CharSequence> adapterfIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterfIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SportingIslamiyaCondition.setAdapter(adapterfIslamiyaCondition);
        SportingIslamiyaCondition.setOnItemSelectedListener(this);



        //Other Facility
        Spinner OtherFacilityPrimaryCondition = findViewById(R.id.StaffHousingSpinner);
        ArrayAdapter<CharSequence> adaptergPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptergPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherFacilityPrimaryCondition.setAdapter(adaptergPrimaryCondition);
        OtherFacilityPrimaryCondition.setOnItemSelectedListener(this);

        Spinner OtherFacilitySecondaryCondition = findViewById(R.id.StudentsHostelSpinner);
        ArrayAdapter<CharSequence> adaptergSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptergSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherFacilitySecondaryCondition.setAdapter(adaptergSecondaryCondition);
        OtherFacilitySecondaryCondition.setOnItemSelectedListener(this);

        Spinner OtherFacilityTechnicalCondition = findViewById(R.id.ToiletSpinner);
        ArrayAdapter<CharSequence> adaptergTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptergTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherFacilityTechnicalCondition.setAdapter(adaptergTechnicalCondition);
        OtherFacilityTechnicalCondition.setOnItemSelectedListener(this);

        Spinner OtherFacilityCommercialCondition = findViewById(R.id.PerimeterSpinner);
        ArrayAdapter<CharSequence> adaptergCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptergCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherFacilityCommercialCondition.setAdapter(adaptergCommercialCondition);
        OtherFacilityCommercialCondition.setOnItemSelectedListener(this);


        Spinner SchoolOwnershipPrimaryCondition = findViewById(R.id.OwnershipPrimarySpinner);
        ArrayAdapter<CharSequence> adapterhPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipPrimaryCondition.setAdapter(adapterhPrimaryCondition);
        SchoolOwnershipPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SchoolOwnershipSecondaryCondition = findViewById(R.id.OwnershipSecondarySpinner);
        ArrayAdapter<CharSequence> adapterhSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipSecondaryCondition.setAdapter(adapterhSecondaryCondition);
        SchoolOwnershipSecondaryCondition.setOnItemSelectedListener(this);

        Spinner SchoolOwnershipTechnicalCondition = findViewById(R.id.OwnershipTechnicalSpinner);
        ArrayAdapter<CharSequence> adapterhTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipTechnicalCondition.setAdapter(adapterhTechnicalCondition);
        SchoolOwnershipTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SchoolOwnershipCommercialCondition = findViewById(R.id.OwnershipCommercialSpinner);
        ArrayAdapter<CharSequence> adapterhCommercialCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipCommercialCondition.setAdapter(adapterhCommercialCondition);
        SchoolOwnershipCommercialCondition.setOnItemSelectedListener(this);

        Spinner SchoolOwnershipTertiaryCondition = findViewById(R.id.OwnershipTertiarySpinner);
        ArrayAdapter<CharSequence> adapterhTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipTertiaryCondition.setAdapter(adapterhTertiaryCondition);
        SchoolOwnershipTertiaryCondition.setOnItemSelectedListener(this);

        Spinner SchoolOwnershipIslamiyaCondition = findViewById(R.id.OwnershipIslamiyaSpinner);
        ArrayAdapter<CharSequence> adapterhIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterhIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SchoolOwnershipIslamiyaCondition.setAdapter(adapterhIslamiyaCondition);
        SchoolOwnershipIslamiyaCondition.setOnItemSelectedListener(this);


        //Health Infrastructure
        Spinner HealthInfrastructurePrimaryCondition = findViewById(R.id.HealthPostSpinner);
        ArrayAdapter<CharSequence> adapteriPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructurePrimaryCondition.setAdapter(adapteriPrimaryCondition);
        HealthInfrastructurePrimaryCondition.setOnItemSelectedListener(this);

        Spinner HealthInfrastructureSecondaryCondition = findViewById(R.id.HealthCenterSpinner);
        ArrayAdapter<CharSequence> adapteriSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructureSecondaryCondition.setAdapter(adapteriSecondaryCondition);
        HealthInfrastructureSecondaryCondition.setOnItemSelectedListener(this);

        Spinner HealthInfrastructureTechnicalCondition = findViewById(R.id.PrimaryHealthCenterSpinner);
        ArrayAdapter<CharSequence> adapteriTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructureTechnicalCondition.setAdapter(adapteriTechnicalCondition);
        HealthInfrastructureTechnicalCondition.setOnItemSelectedListener(this);

        Spinner HealthInfrastructureCommercialCondition = findViewById(R.id.PrivateHospitalSpinner);
        ArrayAdapter<CharSequence> adapteriCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructureCommercialCondition.setAdapter(adapteriCommercialCondition);
        HealthInfrastructureCommercialCondition.setOnItemSelectedListener(this);

        Spinner HealthInfrastructureTertiaryCondition = findViewById(R.id.GeneralHospitalSpinner);
        ArrayAdapter<CharSequence> adapteriTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructureTertiaryCondition.setAdapter(adapteriTertiaryCondition);
        HealthInfrastructureTertiaryCondition.setOnItemSelectedListener(this);

        Spinner HealthInfrastructureIslamiyaCondition = findViewById(R.id.FMCHospitalSpinner);
        ArrayAdapter<CharSequence> adapteriIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteriIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthInfrastructureIslamiyaCondition.setAdapter(adapteriIslamiyaCondition);
        HealthInfrastructureIslamiyaCondition.setOnItemSelectedListener(this);


        //Other Health
        Spinner OtherHealthInfrastructurePrimaryCondition = findViewById(R.id.OperationTheatersSpinner);
        ArrayAdapter<CharSequence> adapterjPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructurePrimaryCondition.setAdapter(adapterjPrimaryCondition);
        OtherHealthInfrastructurePrimaryCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureSecondaryCondition = findViewById(R.id.LaboratoriesSpinner);
        ArrayAdapter<CharSequence> adapterjSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureSecondaryCondition.setAdapter(adapterjSecondaryCondition);
        OtherHealthInfrastructureSecondaryCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureTechnicalCondition = findViewById(R.id.MortuarySpinner);
        ArrayAdapter<CharSequence> adapterjTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureTechnicalCondition.setAdapter(adapterjTechnicalCondition);
        OtherHealthInfrastructureTechnicalCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureCommercialCondition = findViewById(R.id.AmbulancesSpinner);
        ArrayAdapter<CharSequence> adapterjCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureCommercialCondition.setAdapter(adapterjCommercialCondition);
        OtherHealthInfrastructureCommercialCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureTertiaryCondition = findViewById(R.id.ChildcareSpinner);
        ArrayAdapter<CharSequence> adapterjTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureTertiaryCondition.setAdapter(adapterjTertiaryCondition);
        OtherHealthInfrastructureTertiaryCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureIslamiyaCondition = findViewById(R.id.NutritionalServicesSpinner);
        ArrayAdapter<CharSequence> adapterjIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureIslamiyaCondition.setAdapter(adapterjIslamiyaCondition);
        OtherHealthInfrastructureIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureTertiarykCondition = findViewById(R.id.PharmaceuticalsSpinner);
        ArrayAdapter<CharSequence> adapterjTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureTertiarykCondition.setAdapter(adapterjTertiarykCondition);
        OtherHealthInfrastructureTertiarykCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureIslamiyakCondition = findViewById(R.id.StaffAccommodationSpinner);
        ArrayAdapter<CharSequence> adapterjIslamiyakCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjIslamiyakCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureIslamiyakCondition.setAdapter(adapterjIslamiyakCondition);
        OtherHealthInfrastructureIslamiyakCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureTertiaryjCondition = findViewById(R.id.ElectricitySpinner);
        ArrayAdapter<CharSequence> adapterjTertiaryjCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjTertiaryjCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureTertiaryjCondition.setAdapter(adapterjTertiaryjCondition);
        OtherHealthInfrastructureTertiaryjCondition.setOnItemSelectedListener(this);

        Spinner OtherHealthInfrastructureIslamiyajCondition = findViewById(R.id.ColdStoreSpinner);
        ArrayAdapter<CharSequence> adapterjIslamiyajCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjIslamiyajCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        OtherHealthInfrastructureIslamiyajCondition.setAdapter(adapterjIslamiyajCondition);
        OtherHealthInfrastructureIslamiyajCondition.setOnItemSelectedListener(this);


        //Health Staff
        Spinner HealthStaffTertiaryCondition1 = findViewById(R.id.DoctorsSpinner);
        ArrayAdapter<CharSequence> adapterkTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryCondition1.setAdapter(adapterkTertiaryCondition);
        HealthStaffTertiaryCondition1.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryCondition2 = findViewById(R.id.NursesSpinner);
        ArrayAdapter<CharSequence> adapterklTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterklTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryCondition2.setAdapter(adapterklTertiaryCondition);
        HealthStaffTertiaryCondition2.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryCondition3 = findViewById(R.id.MidwivesSpinner);
        ArrayAdapter<CharSequence> adapterjlTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterjlTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryCondition3.setAdapter(adapterjlTertiaryCondition);
        HealthStaffTertiaryCondition3.setOnItemSelectedListener(this);

        Spinner HealthStaffPrimaryCondition = findViewById(R.id.NursesMidwivesSpinner);
        ArrayAdapter<CharSequence> adaptermPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffPrimaryCondition.setAdapter(adaptermPrimaryCondition);
        HealthStaffPrimaryCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffSecondaryCondition = findViewById(R.id.PharmacistSpinner);
        ArrayAdapter<CharSequence> adaptermSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffSecondaryCondition.setAdapter(adaptermSecondaryCondition);
        HealthStaffSecondaryCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffTechnicalCondition = findViewById(R.id.RadiographersSpinner);
        ArrayAdapter<CharSequence> adaptermTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTechnicalCondition.setAdapter(adaptermTechnicalCondition);
        HealthStaffTechnicalCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffCommercialCondition = findViewById(R.id.PharmacyTechniciansSpinner);
        ArrayAdapter<CharSequence> adaptermCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffCommercialCondition.setAdapter(adaptermCommercialCondition);
        HealthStaffCommercialCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryCondition = findViewById(R.id.MedicalLabScientistSpinner);
        ArrayAdapter<CharSequence> adaptermTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryCondition.setAdapter(adaptermTertiaryCondition);
        HealthStaffTertiaryCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyaCondition = findViewById(R.id.PharmacyAssistantSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyaCondition.setAdapter(adaptermIslamiyaCondition);
        HealthStaffIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiarykCondition = findViewById(R.id.LaboratoryAssistantSpinner);
        ArrayAdapter<CharSequence> adaptermTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiarykCondition.setAdapter(adaptermTertiarykCondition);
        HealthStaffTertiarykCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyakCondition = findViewById(R.id.HealthSocialWorkersSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyakCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyakCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyakCondition.setAdapter(adaptermIslamiyakCondition);
        HealthStaffIslamiyakCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryjCondition = findViewById(R.id.NutritionistSpinner);
        ArrayAdapter<CharSequence> adaptermTertiaryjCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTertiaryjCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryjCondition.setAdapter(adaptermTertiaryjCondition);
        HealthStaffTertiaryjCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyajCondition = findViewById(R.id.PublicHealthOfficersSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyajCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyajCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyajCondition.setAdapter(adaptermIslamiyajCondition);
        HealthStaffIslamiyajCondition.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyaConditiona = findViewById(R.id.CommunityHealthOfficersSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyaConditionz = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyaConditionz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyaConditiona.setAdapter(adaptermIslamiyaConditionz);
        HealthStaffIslamiyaConditiona.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyaConditionb = findViewById(R.id.ExtensionOfficersSpinner);
        ArrayAdapter<CharSequence> adaptermTertiaryxCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTertiaryxCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyaConditionb.setAdapter(adaptermTertiaryxCondition);
        HealthStaffIslamiyaConditionb.setOnItemSelectedListener(this);

        Spinner HealthStaffIslamiyaConditionc = findViewById(R.id.EnviromentalOfficersSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyaxCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyaxCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffIslamiyaConditionc.setAdapter(adaptermIslamiyaxCondition);
        HealthStaffIslamiyaConditionc.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryyConditiond = findViewById(R.id.PsychoSocialSpinner);
        ArrayAdapter<CharSequence> adaptermTertiaryyCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermTertiaryyCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryyConditiond.setAdapter(adaptermTertiaryyCondition);
        HealthStaffTertiaryyConditiond.setOnItemSelectedListener(this);

        Spinner HealthStaffTertiaryyConditione = findViewById(R.id.TradionalMiddwivesSpinner);
        ArrayAdapter<CharSequence> adaptermIslamiyayCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptermIslamiyayCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HealthStaffTertiaryyConditione.setAdapter(adaptermIslamiyayCondition);
        HealthStaffTertiaryyConditione.setOnItemSelectedListener(this);

        //Transportation
        Spinner TransportationTertiaryCondition1 = findViewById(R.id.AirportSpinner);
        ArrayAdapter<CharSequence> adapterzlTertiaryxxCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzlTertiaryxxCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationTertiaryCondition1.setAdapter(adapterzlTertiaryxxCondition);
        TransportationTertiaryCondition1.setOnItemSelectedListener(this);

        Spinner TransportationTertiaryCondition2 = findViewById(R.id.RailwaySpinner);
        ArrayAdapter<CharSequence> adapterzlTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzlTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationTertiaryCondition2.setAdapter(adapterzlTertiaryCondition);
        TransportationTertiaryCondition2.setOnItemSelectedListener(this);

        Spinner TransportationTertiaryCondition3 = findViewById(R.id.RailwayFlowScheduleSpinner);
        ArrayAdapter<CharSequence> adapteryxTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteryxTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationTertiaryCondition3.setAdapter(adapteryxTertiaryCondition);
        TransportationTertiaryCondition3.setOnItemSelectedListener(this);

        Spinner TransportationPrimaryCondition = findViewById(R.id.SeaPortSpinner);
        ArrayAdapter<CharSequence> adapterzPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationPrimaryCondition.setAdapter(adapterzPrimaryCondition);
        TransportationPrimaryCondition.setOnItemSelectedListener(this);

        Spinner TransportationSecondaryCondition = findViewById(R.id.TrunkSpinner);
        ArrayAdapter<CharSequence> adapterzSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationSecondaryCondition.setAdapter(adapterzSecondaryCondition);
        TransportationSecondaryCondition.setOnItemSelectedListener(this);

        Spinner TransportationTechnicalCondition = findViewById(R.id.LateriteRoadSpinner);
        ArrayAdapter<CharSequence> adapterzTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationTechnicalCondition.setAdapter(adapterzTechnicalCondition);
        TransportationTechnicalCondition.setOnItemSelectedListener(this);

        /**
        Spinner TransportationCommercialCondition = findViewById(R.id.FoodpathSpinner);
        ArrayAdapter<CharSequence> adapterzCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationCommercialCondition.setAdapter(adapterzCommercialCondition);
        TransportationCommercialCondition.setOnItemSelectedListener(this);
         **/

        Spinner TransportationTertiaryCondition = findViewById(R.id.BridgeSpinner);
        ArrayAdapter<CharSequence> adapterzyTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzyTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationTertiaryCondition.setAdapter(adapterzyTertiaryCondition);
        TransportationTertiaryCondition.setOnItemSelectedListener(this);

        Spinner TransportationIslamiyaCondition = findViewById(R.id.MotorParkSpinner);
        ArrayAdapter<CharSequence> adapterzIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TransportationIslamiyaCondition.setAdapter(adapterzIslamiyaCondition);
        TransportationIslamiyaCondition.setOnItemSelectedListener(this);

        //Communication
        Spinner CommunicationTechnicalCondition = findViewById(R.id.MTNSpinner);
        ArrayAdapter<CharSequence> adapterzaTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzaTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CommunicationTechnicalCondition.setAdapter(adapterzaTechnicalCondition);
        CommunicationTechnicalCondition.setOnItemSelectedListener(this);

        Spinner CommunicationCommercialCondition = findViewById(R.id.AirtelSpinner);
        ArrayAdapter<CharSequence> adapterzaCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzaCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CommunicationCommercialCondition.setAdapter(adapterzaCommercialCondition);
        CommunicationCommercialCondition.setOnItemSelectedListener(this);

        Spinner CommunicationTertiaryCondition = findViewById(R.id.GloSpinner);
        ArrayAdapter<CharSequence> adapterzayTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzayTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CommunicationTertiaryCondition.setAdapter(adapterzayTertiaryCondition);
        CommunicationTertiaryCondition.setOnItemSelectedListener(this);

        Spinner CommunicationIslamiyaCondition = findViewById(R.id.Mobile9Spinner);
        ArrayAdapter<CharSequence> adapterzaIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterzaIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CommunicationIslamiyaCondition.setAdapter(adapterzaIslamiyaCondition);
        CommunicationIslamiyaCondition.setOnItemSelectedListener(this);

        //MediaComss
        Spinner MediaCommsTertiaryCondition1 = findViewById(R.id.InternationalRadioSpinner);
        ArrayAdapter<CharSequence> adapterkaTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkaTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTertiaryCondition1.setAdapter(adapterkaTertiaryCondition);
        MediaCommsTertiaryCondition1.setOnItemSelectedListener(this);

        Spinner MediaCommsTertiaryCondition2 = findViewById(R.id.FederalRadioSpinner);
        ArrayAdapter<CharSequence> adapterkalTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkalTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTertiaryCondition2.setAdapter(adapterkalTertiaryCondition);
        MediaCommsTertiaryCondition2.setOnItemSelectedListener(this);

        Spinner MediaCommsTertiaryCondition3 = findViewById(R.id.PrivateRadioSpinner);
        ArrayAdapter<CharSequence> adapterkbTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkbTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTertiaryCondition3.setAdapter(adapterkbTertiaryCondition);
        MediaCommsTertiaryCondition3.setOnItemSelectedListener(this);

        Spinner MediaCommsPrimaryCondition = findViewById(R.id.SateliteSpinner);
        ArrayAdapter<CharSequence> adapterkcPrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcPrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsPrimaryCondition.setAdapter(adapterkcPrimaryCondition);
        MediaCommsPrimaryCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsSecondaryCondition = findViewById(R.id.NTAServiceSpinner);
        ArrayAdapter<CharSequence> adapterkcSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsSecondaryCondition.setAdapter(adapterkcSecondaryCondition);
        MediaCommsSecondaryCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsTechnicalCondition = findViewById(R.id.StateTelevisionSpinner);
        ArrayAdapter<CharSequence> adapterkcTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTechnicalCondition.setAdapter(adapterkcTechnicalCondition);
        MediaCommsTechnicalCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsCommercialCondition = findViewById(R.id.PrivateTelevisionSpinner);
        ArrayAdapter<CharSequence> adapterkcCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsCommercialCondition.setAdapter(adapterkcCommercialCondition);
        MediaCommsCommercialCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsTertiaryCondition = findViewById(R.id.SocialMediaSpinner);
        ArrayAdapter<CharSequence> adapterkcTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTertiaryCondition.setAdapter(adapterkcTertiaryCondition);
        MediaCommsTertiaryCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsIslamiyaCondition = findViewById(R.id.NewspaperSpinner);
        ArrayAdapter<CharSequence> adapterkcIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsIslamiyaCondition.setAdapter(adapterkcIslamiyaCondition);
        MediaCommsIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner MediaCommsTertiarykCondition = findViewById(R.id.StateRadioSpinner);
        ArrayAdapter<CharSequence> adapterkcTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkcTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MediaCommsTertiarykCondition.setAdapter(adapterkcTertiarykCondition);
        MediaCommsTertiarykCondition.setOnItemSelectedListener(this);


        //FireServices
        Spinner FireServiceIslamiyaCondition = findViewById(R.id.FireSpinner);
        ArrayAdapter<CharSequence> adapterkdIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkdIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FireServiceIslamiyaCondition.setAdapter(adapterkdIslamiyaCondition);
        FireServiceIslamiyaCondition.setOnItemSelectedListener(this);

        //Security
        Spinner SecurityTertiaryCondition1 = findViewById(R.id.ArmySpinner);
        ArrayAdapter<CharSequence> adapterakTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterakTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTertiaryCondition1.setAdapter(adapterakTertiaryCondition);
        SecurityTertiaryCondition1.setOnItemSelectedListener(this);

        Spinner SecurityTertiaryCondition2 = findViewById(R.id.ArmyBarracksSpinner);
        ArrayAdapter<CharSequence> adapteraklTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapteraklTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTertiaryCondition2.setAdapter(adapteraklTertiaryCondition);
        SecurityTertiaryCondition2.setOnItemSelectedListener(this);

        Spinner SecurityTertiaryCondition3 = findViewById(R.id.PoliceSpinner);
        ArrayAdapter<CharSequence> adapterkbTertiaryxCondition = ArrayAdapter.createFromResource(this, R.array.school_type, android.R.layout.simple_spinner_item);
        adapterkbTertiaryxCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTertiaryCondition3.setAdapter(adapterkbTertiaryxCondition);
        SecurityTertiaryCondition3.setOnItemSelectedListener(this);

        Spinner SecurityPrimaryCondition = findViewById(R.id.PoliceBarracksSpinner);
        ArrayAdapter<CharSequence> adapterkePrimaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkePrimaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityPrimaryCondition.setAdapter(adapterkePrimaryCondition);
        SecurityPrimaryCondition.setOnItemSelectedListener(this);

        Spinner SecuritySecondaryCondition = findViewById(R.id.DeptSpinner);
        ArrayAdapter<CharSequence> adapterkeSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecuritySecondaryCondition.setAdapter(adapterkeSecondaryCondition);
        SecuritySecondaryCondition.setOnItemSelectedListener(this);

        Spinner SecurityTechnicalCondition = findViewById(R.id.CivilSpinner);
        ArrayAdapter<CharSequence> adapterkeTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTechnicalCondition.setAdapter(adapterkeTechnicalCondition);
        SecurityTechnicalCondition.setOnItemSelectedListener(this);

        Spinner SecurityCommercialCondition = findViewById(R.id.ImmigrationSpinner);
        ArrayAdapter<CharSequence> adapterkeCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityCommercialCondition.setAdapter(adapterkeCommercialCondition);
        SecurityCommercialCondition.setOnItemSelectedListener(this);

        Spinner SecurityTertiaryCondition = findViewById(R.id.CustomsSpinner);
        ArrayAdapter<CharSequence> adapterkeTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTertiaryCondition.setAdapter(adapterkeTertiaryCondition);
        SecurityTertiaryCondition.setOnItemSelectedListener(this);

        Spinner SecurityIslamiyaCondition = findViewById(R.id.PrisonSpinner);
        ArrayAdapter<CharSequence> adapterkeIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityIslamiyaCondition.setAdapter(adapterkeIslamiyaCondition);
        SecurityIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner SecurityTertiarykCondition = findViewById(R.id.CJTFSpinner);
        ArrayAdapter<CharSequence> adapterkeTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkeTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecurityTertiarykCondition.setAdapter(adapterkeTertiarykCondition);
        SecurityTertiarykCondition.setOnItemSelectedListener(this);

        //Judicial
        Spinner JudicialSecondaryCondition = findViewById(R.id.FederalHighCourtSpinner);
        ArrayAdapter<CharSequence> adapterkfSecondaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfSecondaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialSecondaryCondition.setAdapter(adapterkfSecondaryCondition);
        JudicialSecondaryCondition.setOnItemSelectedListener(this);

        Spinner JudicialTechnicalCondition = findViewById(R.id.ShariaCourtOfAppealSpinner);
        ArrayAdapter<CharSequence> adapterkfTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialTechnicalCondition.setAdapter(adapterkfTechnicalCondition);
        JudicialTechnicalCondition.setOnItemSelectedListener(this);

        Spinner JudicialCommercialCondition = findViewById(R.id.IndustrialCourtSpinner);
        ArrayAdapter<CharSequence> adapterkfCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialCommercialCondition.setAdapter(adapterkfCommercialCondition);
        JudicialCommercialCondition.setOnItemSelectedListener(this);

        Spinner JudicialTertiaryCondition = findViewById(R.id.StateHighCourtSpinner);
        ArrayAdapter<CharSequence> adapterkfTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialTertiaryCondition.setAdapter(adapterkfTertiaryCondition);
        JudicialTertiaryCondition.setOnItemSelectedListener(this);

        Spinner JudicialIslamiyaCondition = findViewById(R.id.MagistrateCourtSpinner);
        ArrayAdapter<CharSequence> adapterkfIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialIslamiyaCondition.setAdapter(adapterkfIslamiyaCondition);
        JudicialIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner JudicialTertiarykCondition = findViewById(R.id.CustomaryCourtSpinner);
        ArrayAdapter<CharSequence> adapterkfTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkfTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JudicialTertiarykCondition.setAdapter(adapterkfTertiarykCondition);
        JudicialTertiarykCondition.setOnItemSelectedListener(this);


        //Bank
        Spinner BankIslamiyaCondition = findViewById(R.id.cBankSpinner);
        ArrayAdapter<CharSequence> adapterkiIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkiIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BankIslamiyaCondition.setAdapter(adapterkiIslamiyaCondition);
        BankIslamiyaCondition.setOnItemSelectedListener(this);

        Spinner BankTertiarykCondition = findViewById(R.id.cBankConditionSpinner);
        ArrayAdapter<CharSequence> adapterkiTertiarykCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkiTertiarykCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BankTertiarykCondition.setAdapter(adapterkiTertiarykCondition);
        BankTertiarykCondition.setOnItemSelectedListener(this);

        //Market
        Spinner MarketTechnicalCondition = findViewById(R.id.DailyMarketSpinner);
        ArrayAdapter<CharSequence> adapterkxTechnicalCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkxTechnicalCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MarketTechnicalCondition.setAdapter(adapterkxTechnicalCondition);
        MarketTechnicalCondition.setOnItemSelectedListener(this);

        Spinner MarketCommercialCondition = findViewById(R.id.WeeklyMarketSpinner);
        ArrayAdapter<CharSequence> adapterkxCommercialCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkxCommercialCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MarketCommercialCondition.setAdapter(adapterkxCommercialCondition);
        MarketCommercialCondition.setOnItemSelectedListener(this);

        Spinner MarketTertiaryCondition = findViewById(R.id.LivestockMarketSpinner);
        ArrayAdapter<CharSequence> adapterkxTertiaryCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkxTertiaryCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MarketTertiaryCondition.setAdapter(adapterkxTertiaryCondition);
        MarketTertiaryCondition.setOnItemSelectedListener(this);

        Spinner MarketIslamiyaCondition = findViewById(R.id.SuperMarketsSpinner);
        ArrayAdapter<CharSequence> adapterkxIslamiyaCondition = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adapterkxIslamiyaCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MarketIslamiyaCondition.setAdapter(adapterkxIslamiyaCondition);
        MarketIslamiyaCondition.setOnItemSelectedListener(this);


        //General

        State = findViewById(R.id.StateText);
        LocalGov = findViewById(R.id.localGovText);
        TownVillage = findViewById(R.id.TownVillage);
        Longitude = findViewById(R.id.Longitude);
        Latitude = findViewById(R.id.Latitude);
        Status = findViewById(R.id.Status);
        Population = findViewById(R.id.Population);

        RoadsCondition = findViewById(R.id.TrunkText);
        MotoParkText = findViewById(R.id.MotorParkText);

        COMMUNITYSizeAdultMaleBefore = findViewById(R.id.COMMUNITYSizeAdultMaleBefore);
        COMMUNITYSizeAdultFemaleBefore = findViewById(R.id.COMMUNITYSizeAdultFemaleBefore);
        COMMUNITYSizeAdultMaleAfter = findViewById(R.id.COMMUNITYSizeAdultMaleAfter);
        COMMUNITYSizeAdultFemaleAfter = findViewById(R.id.COMMUNITYSizeAdultFemaleAfter);
        COMMUNITYSizeChildrenMaleAfter = findViewById(R.id.COMMUNITYSizeChildrenMaleAfter);
        COMMUNITYSizeChildrenMaleBefore = findViewById(R.id.COMMUNITYSizeChildrenMaleBefore);
        COMMUNITYSizeChildrenFemaleBefore = findViewById(R.id.COMMUNITYSizeChildrenFemaleBefore);
        COMMUNITYSizeChildrenFemaleAfter = findViewById(R.id.COMMUNITYSizeChildrenFemaleAfter);
        COMMUNITYMemberConditionElderlyBefore = findViewById(R.id.COMMUNITYMemberConditionElderlyBefore);
        COMMUNITYMemberConditionDisabledBefore = findViewById(R.id.COMMUNITYMemberConditionDisabledBefore);
        COMMUNITYMemberConditionPregnantBefore = findViewById(R.id.COMMUNITYMemberConditionPregnantBefore);
        COMMUNITYMemberConditionLactatingBefore = findViewById(R.id.COMMUNITYMemberConditionLactatingBefore);
        COMMUNITYMemberConditionInfantBefore = findViewById(R.id.COMMUNITYMemberConditionInfantBefore);
        COMMUNITYMemberConditionChildrenBefore = findViewById(R.id.COMMUNITYMemberConditionChildrenBefore);
        COMMUNITYMemberConditionElderlyAfter = findViewById(R.id.COMMUNITYMemberConditionElderlyAfter);
        COMMUNITYMemberConditionDisabledAfter = findViewById(R.id.COMMUNITYMemberConditionDisabledAfter);
        COMMUNITYMemberConditionPregnantAfter = findViewById(R.id.COMMUNITYMemberConditionPregnantAfter);
        COMMUNITYMemberConditionLactatingAfter = findViewById(R.id.COMMUNITYMemberConditionLactatingAfter);
        COMMUNITYMemberConditionInfantAfter = findViewById(R.id.COMMUNITYMemberConditionInfantAfter);
        COMMUNITYMemberConditionChildrenAfter = findViewById(R.id.COMMUNITYMemberConditionChildrenAfter);
        AttackedDeath = findViewById(R.id.AttackedDeath);
        AttackedMissing = findViewById(R.id.AttackedMissing);
        AttacksNumber = findViewById(R.id.AttacksNumber);
        COMMUNITYMemberAdultMaleLostAfter = findViewById(R.id.COMMUNITYMemberAdultMaleLostAfter);
        COMMUNITYMemberAdultFemaleLostAfter = findViewById(R.id.COMMUNITYMemberAdultFemaleLostAfter);
        COMMUNITYMemberChildrenLostAfter = findViewById(R.id.COMMUNITYMemberChildrenLostAfter);
        KeyMemberQualificationBefore = findViewById(R.id.cQualificationBefore);
        KeyMemberQualificationAfter = findViewById(R.id.cQualificationAfter);
        KeyMemberOccupationBefore = findViewById(R.id.cOccupationBefore);
        KeyMemberOccupationAfter = findViewById(R.id.cOccupationAfter);
        KeyMemberOtherOccupationBefore = findViewById(R.id.cOccupationOther);
        LGASecretariat = findViewById(R.id.LGASecretariatText);
        EmirPalace = findViewById(R.id.EmirTextText);
        DHsPalace = findViewById(R.id.DHsPalaceText);
        VillageHeadPalace = findViewById(R.id.VillageHeadPalacePalaceText);
        TownHall = findViewById(R.id.TownHallText);
        PlayGroundCenter = findViewById(R.id.PlayGroundCenterText);
        Primary = findViewById(R.id.PrimaryText);
        Secondary = findViewById(R.id.SecondaryText);
        Technical = findViewById(R.id.TechnicalText);
        Commercial =  findViewById(R.id.CommercialText);
        Tertiary = findViewById(R.id.TertiaryText);
        OtherSchool = findViewById(R.id.OtherSchool);
        PrimaryClassRoom = findViewById(R.id.ClassroomPrimaryText);
        SecondaryClassRoom = findViewById(R.id.ClassroomSecondaryText);
        TechnicalClassRoom = findViewById(R.id.ClassroomTechnicalText);
        CommercialClassRoom = findViewById(R.id.ClassroomCommercialText);
        TertiaryClassRoom = findViewById(R.id.ClassroomTertiaryText);
        IslamiyaClassRoom = findViewById(R.id.ClassroomIslamiyaText);
        OtherSchoolClassRoom = findViewById(R.id.OtherSchoolClassRoom);
        PrimaryWorkshop = findViewById(R.id.LaboratoriesPrimaryText);
        SecondaryWorkshop = findViewById(R.id.LaboratoriesSecondaryText);
        TechnicalWorkshop =  findViewById(R.id.LaboratoriesTechnicalText);
        CommercialWorkshop = findViewById(R.id.LaboratoriesCommercialText);
        TertiaryWorkshop = findViewById(R.id.LaboratoriesTertiaryText);
        IslamiyaWorkshop = findViewById(R.id.LaboratoriesIslamiyaText);
        OtherSchoolWorkshop = findViewById(R.id.OtherSchoolWorkshop);
        StaffPrimary = findViewById(R.id.TeachersPrimaryText);
        StaffSecondary = findViewById(R.id.TeachersSecondaryText);
        StaffTechnical = findViewById(R.id.TeachersTechnicalText);
        StaffCommercial = findViewById(R.id.TeachersCommercialText);
        StaffTertiary = findViewById(R.id.TeachersTertiaryText);
        StaffIslamiya = findViewById(R.id.TeachersIslamiyaText);
        StaffOther = findViewById(R.id.StaffOther);
        NonStaffPrimary = findViewById(R.id.NonStaffPrimaryText);
        NonStaffSecondary = findViewById(R.id.NonStaffSecondaryText);
        NonStaffTechnical = findViewById(R.id.NonStaffTechnicalText);
        NonStaffCommercial = findViewById(R.id.NonStaffCommercialText);
        NonStaffTertiary = findViewById(R.id.NonStaffTertiaryText);
        NonStaffIslamiya = findViewById(R.id.NonStaffIslamiyaText);
        NonStaffOther = findViewById(R.id.NonStaffOther);
        SportingPrimary = findViewById(R.id.SportingText);
        SportingSecondary = findViewById(R.id.SportingSecondaryText);
        SportingTechnical = findViewById(R.id.SportingTechnicalText);
        SportingCommercial = findViewById(R.id.SportingCommercialText);
        SportingTertiary = findViewById(R.id.SportingTertiaryText);
        SportingIslamiya = findViewById(R.id.SportingIslamiyaText);
        SportingOther = findViewById(R.id.SportingOther);
        OtherStaffHousing = findViewById(R.id.StaffHousingText);
        StudentHostel = findViewById(R.id.StudentsHostelText);
        OtherToiletFacilities = findViewById(R.id.ToiletText);
        PerimeterFence = findViewById(R.id.PerimeterText);
        OwnerShipPrimary = findViewById(R.id.OwnershipPrimaryText);
        OwnerShipSecondary = findViewById(R.id.OwnershipSecondaryText);
        OwnerShipTechnical = findViewById(R.id.OwnershipTechnicalText);
        OwnerShipCommercial = findViewById(R.id.OwnershipCommercialText);
        OwnerShipTertiary = findViewById(R.id.OwnershipTertiaryText);
        OwnerShipIslamiya = findViewById(R.id.OwnershipIslamiyaText);
        OwnerShipOther = findViewById(R.id.OwnerShipOther);
        HealthPost = findViewById(R.id.HealthPostText);
        HealthCenter = findViewById(R.id.HealthCenterText);
        PrimaryHealthCenter = findViewById(R.id.PrimaryHealthCenterText);
        PrivateHospital = findViewById(R.id.PrivateHospitalText);
        GeneralHospital = findViewById(R.id.GeneralHospitalText);
        FMCSpecialistHospital = findViewById(R.id.FMCHospitalText);
        OtherHospitals = findViewById(R.id.OtherHospitals);
        OperationTheater = findViewById(R.id.OperationTheatersText);
        Laboratories = findViewById(R.id.LaboratoriesText);
        Mortuary = findViewById(R.id.MortuaryText);
        Ambulances = findViewById(R.id.AmbulancesText);
        Maternity = findViewById(R.id.ChildcareText);
        NutritionalServices = findViewById(R.id.NutritionalServicesText);
        Pharmaceuticals = findViewById(R.id.PharmaceuticalsText);
        StaffAccommodation = findViewById(R.id.StaffAccommodationText);
        ElectricitySupply = findViewById(R.id.ElectricityText);
        ColdStore = findViewById(R.id.ColdStoreText);
        Doctors = findViewById(R.id.DoctorsText);
        Nurses = findViewById(R.id.NursesText);
        Midwives = findViewById(R.id.MidwivesText);
        NursesMidwives = findViewById(R.id.NursesMidwivesText);
        Pharmacists = findViewById(R.id.PharmacistText);
        Radiographers = findViewById(R.id.RadiographersText);
        PharmacyTechnicians = findViewById(R.id.PharmacyTechniciansText);
        MedicalLabScientist = findViewById(R.id.MedicalLabScientistText);
        PharmacyAssistant = findViewById(R.id.PharmacyAssistantText);
        LaboratoryAssistant = findViewById(R.id.LaboratoryAssistantText);
        HealthSocialWorkers = findViewById(R.id.HealthSocialWorkersText);
        Nutritionist = findViewById(R.id.NutritionistText);
        PublicHealthOfficers = findViewById(R.id.PublicHealthOfficersText);
        CommunityHealthOfficers = findViewById(R.id.CommunityHealthOfficersText);
        ExtensionOfficers = findViewById(R.id.ExtensionOfficersText);
        EnvironmentalHealthOfficers = findViewById(R.id.EnvromentalOfficersText);
        PsychSocial = findViewById(R.id.PsychoSocialText);
        TraditionalMidwives = findViewById(R.id.TradionalMidwivesText);
        SourceWater = findViewById(R.id.cwaterConditionText);
        ElectricitySource = findViewById(R.id.cElectricitySource);
        Roads = findViewById(R.id.LateriteRoadText);
        Railway = findViewById(R.id.RailwayText);
        RailwayFlowSchedule = findViewById(R.id.RailwayFlowScheduleText);
        Airport = findViewById(R.id.AirportText);
        SeaTransport = findViewById(R.id.SeaPortText);
        Communication2 = findViewById(R.id.AirtelText);
        Communication1 = findViewById(R.id.MTNText);
        Communication3 = findViewById(R.id.GloText);
        Communication4 = findViewById(R.id.Mobile9Text);
        InternationalRadio = findViewById(R.id.InternationalRadioText);
        FederalRadio = findViewById(R.id.FederalRadioText);
        StateRadio = findViewById(R.id.StateRadioText);
        PrivateRadio = findViewById(R.id.PrivateRadioText);
        SateliteTelevision = findViewById(R.id.SateliteText);
        NTAService = findViewById(R.id.NTAServiceText);
        StateTelevisionStation = findViewById(R.id.StateTelevisionText);
        PrivateTelevisionService = findViewById(R.id.PrivateTelevisionText);
        SocialMedia = findViewById(R.id.SocialMediaText);
        PrintMedia = findViewById(R.id.NewspaperText);
        FireService = findViewById(R.id.FireText);
        ArmyFormationAndCommand = findViewById(R.id.ArmyText);
        ArmyBarracks = findViewById(R.id.ArmyBarracksText);
        PoliceStation = findViewById(R.id.PoliceText);
        PoliceBarracks = findViewById(R.id.PoliceBarracksText);
        DeptStateServices = findViewById(R.id.DeptText);
        CivilDefence = findViewById(R.id.CivilText);
        ImmigrationService = findViewById(R.id.ImmigrationText);
        CustomService = findViewById(R.id.CustomsText);
        PrisonService = findViewById(R.id.PrisonText);
        FederalHighCourt = findViewById(R.id.FederalHighCourtText);
        ShariaCourtOfAppeal = findViewById(R.id.ShariaCourtOfAppealText);
        IndustrialCourt = findViewById(R.id.IndustrialCourtText);
        StateHighCourt = findViewById(R.id.StateHighCourtText);
        MagistrateCourt = findViewById(R.id.MagistrateCourtText);
        CustomaryCourt = findViewById(R.id.CustomaryCourtText);
        Banks = findViewById(R.id.cBankText);
        BanksCondition = findViewById(R.id.cBankConditionText);
        OtherBank = findViewById(R.id.OtherBank);
        InsuranceCompanies = findViewById(R.id.InsuranceCompanies);
        Factories = findViewById(R.id.Factories);
        DailyMarket = findViewById(R.id.DailyMarketText);
        WeeklyMarket = findViewById(R.id.WeeklyMarketText);
        LivestockMarket = findViewById(R.id.LivestockMarketText);
        SuperMarkets = findViewById(R.id.SuperMarketsText);
        Hotels = findViewById(R.id.Hotels);
        Tourist = findViewById(R.id.Tourist);
        Mosques = findViewById(R.id.Mosques);
        Church = findViewById(R.id.Church);
        Shrine = findViewById(R.id.Shrine);
        NGOsCBOs = findViewById(R.id.NGOsCBOs);
    }

    public void FocusSubmit(View view) {
        SubmitToDB();

    }

    private void SubmitToDB() {

        final String Val001 = MotoParkText.getText().toString().trim();

        final String Val1 = State.getText().toString().trim();
        final String Val2 = LocalGov.getText().toString().trim();
        final String Val3 = TownVillage.getText().toString().trim();
        final String Val4 = Longitude.getText().toString().trim();
        final String Val5 = Latitude.getText().toString().trim();
        final String Val6 = Status.getText().toString().trim();
        final String Val7 = Population.getText().toString().trim();
        final String Val8 = COMMUNITYSizeAdultMaleBefore.getText().toString().trim();
        final String Val9 = COMMUNITYSizeAdultFemaleBefore.getText().toString().trim();
        final String Val10 = COMMUNITYSizeChildrenMaleBefore.getText().toString().trim();
        final String Val11 = COMMUNITYSizeChildrenFemaleBefore.getText().toString().trim();
        final String Val12 = COMMUNITYSizeAdultMaleAfter.getText().toString().trim();
        final String Val13 = COMMUNITYSizeAdultFemaleAfter.getText().toString().trim();
        final String Val14 = COMMUNITYSizeChildrenMaleAfter.getText().toString().trim();
        final String Val15 = COMMUNITYSizeChildrenFemaleAfter.getText().toString().trim();
        final String Val16 = COMMUNITYMemberConditionElderlyBefore.getText().toString().trim();
        final String Val17 = COMMUNITYMemberConditionDisabledBefore.getText().toString().trim();
        final String Val18 = COMMUNITYMemberConditionPregnantBefore.getText().toString().trim();
        final String Val19 = COMMUNITYMemberConditionLactatingBefore.getText().toString().trim();
        final String Val20 = COMMUNITYMemberConditionInfantBefore.getText().toString().trim();
        final String Val21 = COMMUNITYMemberConditionChildrenBefore.getText().toString().trim();
        final String Val22 = COMMUNITYMemberConditionElderlyAfter.getText().toString().trim();
        final String Val23 = COMMUNITYMemberConditionDisabledAfter.getText().toString().trim();
        final String Val24 = COMMUNITYMemberConditionPregnantAfter.getText().toString().trim();
        final String Val25 = COMMUNITYMemberConditionLactatingAfter.getText().toString().trim();
        final String Val26 = COMMUNITYMemberConditionInfantAfter.getText().toString().trim();
        final String Val27 = COMMUNITYMemberConditionChildrenAfter.getText().toString().trim();
        final String Val28 = AttackedDeath.getText().toString().trim();
        final String Val29 = AttackedMissing.getText().toString().trim();
        final String Val30 = AttacksNumber.getText().toString().trim();
        final String Val31 = COMMUNITYMemberAdultMaleLostAfter.getText().toString().trim();
        final String Val32 = COMMUNITYMemberAdultFemaleLostAfter.getText().toString().trim();
        final String Val33 = COMMUNITYMemberChildrenLostAfter.getText().toString().trim();
        final String Val34 = KeyMemberQualificationBefore.getText().toString().trim();
        final String Val35 = KeyMemberQualificationAfter.getText().toString().trim();
        final String Val36 = KeyMemberOccupationBefore.getText().toString().trim();
        final String Val37 = KeyMemberOccupationAfter.getText().toString().trim();
        final String Val38 = KeyMemberOtherOccupationBefore.getText().toString().trim();
        final String Val40 = LGASecretariat.getText().toString().trim();
        final String Val41 = EmirPalace.getText().toString().trim();
        final String Val42 = DHsPalace.getText().toString().trim();
        final String Val43 = VillageHeadPalace.getText().toString().trim();
        final String Val44 = TownHall.getText().toString().trim();
        final String Val45 = PlayGroundCenter.getText().toString().trim();
        final String Val46 = Primary.getText().toString().trim();
        final String Val47 = Secondary.getText().toString().trim();
        final String Val48 = Technical.getText().toString().trim();
        final String Val49 = Commercial.getText().toString().trim();
        final String Val50 = Tertiary.getText().toString().trim();
        final String Val51 = OtherSchool.getText().toString().trim();
        final String Val52 = PrimaryClassRoom.getText().toString().trim();
        final String Val53 = SecondaryClassRoom.getText().toString().trim();
        final String Val54 = TechnicalClassRoom.getText().toString().trim();
        final String Val55 = CommercialClassRoom.getText().toString().trim();
        final String Val56 = TertiaryClassRoom.getText().toString().trim();
        final String Val57 = IslamiyaClassRoom.getText().toString().trim();
        final String Val58 = OtherSchoolClassRoom.getText().toString().trim();
        final String Val59 = PrimaryWorkshop.getText().toString().trim();
        final String Val60 = SecondaryWorkshop.getText().toString().trim();
        final String Val61 = TechnicalWorkshop.getText().toString().trim();
        final String Val62 = CommercialWorkshop.getText().toString().trim();
        final String Val63 = TertiaryWorkshop.getText().toString().trim();
        final String Val64 = IslamiyaWorkshop.getText().toString().trim();
        final String Val65 = OtherSchoolWorkshop.getText().toString().trim();

        final String Val69 = StaffPrimary.getText().toString().trim();
        final String Val70 = StaffSecondary.getText().toString().trim();
        final String Val71 = StaffTechnical.getText().toString().trim();
        final String Val72 = StaffCommercial.getText().toString().trim();
        final String Val73 = StaffTertiary.getText().toString().trim();
        final String Val74 = StaffIslamiya.getText().toString().trim();
        final String Val75 = StaffOther.getText().toString().trim();
        final String Val76 = NonStaffPrimary.getText().toString().trim();
        final String Val77 = NonStaffSecondary.getText().toString().trim();
        final String Val78 = NonStaffTechnical.getText().toString().trim();
        final String Val79 = NonStaffCommercial.getText().toString().trim();
        final String Val80 = NonStaffTertiary.getText().toString().trim();
        final String Val81 = NonStaffIslamiya.getText().toString().trim();
        final String Val82 = NonStaffOther.getText().toString().trim();
        final String Val83 = SportingPrimary.getText().toString().trim();
        final String Val84 = SportingSecondary.getText().toString().trim();
        final String Val85 = SportingTechnical.getText().toString().trim();
        final String Val86 = SportingCommercial.getText().toString().trim();
        final String Val87 = SportingTertiary.getText().toString().trim();
        final String Val88 = SportingIslamiya.getText().toString().trim();
        final String Val89 = SportingOther.getText().toString().trim();
        final String Val90 = OtherStaffHousing.getText().toString().trim();
        final String Val91 = StudentHostel.getText().toString().trim();
        final String Val92 = OtherToiletFacilities.getText().toString().trim();
        final String Val93 = PerimeterFence.getText().toString().trim();
        final String Val94 = OwnerShipPrimary.getText().toString().trim();
        final String Val95 = OwnerShipSecondary.getText().toString().trim();
        final String Val96 = OwnerShipTechnical.getText().toString().trim();
        final String Val97 = OwnerShipCommercial.getText().toString().trim();
        final String Val98 = OwnerShipTertiary.getText().toString().trim();
        final String Val99 = OwnerShipIslamiya.getText().toString().trim();
        final String Val100 = OwnerShipOther.getText().toString().trim();
        final String Val101 = HealthPost.getText().toString().trim();
        final String Val102 = HealthCenter.getText().toString().trim();
        final String Val103 = PrimaryHealthCenter.getText().toString().trim();
        final String Val104 = PrivateHospital.getText().toString().trim();
        final String Val105 = GeneralHospital.getText().toString().trim();
        final String Val106 = FMCSpecialistHospital.getText().toString().trim();
        final String Val107 = OtherHospitals.getText().toString().trim();
        final String Val108 = OperationTheater.getText().toString().trim();
        final String Val109 = Laboratories.getText().toString().trim();
        final String Val110 = Mortuary.getText().toString().trim();
        final String Val111 = Ambulances.getText().toString().trim();
        final String Val112 = Maternity.getText().toString().trim();
        final String Val113 = NutritionalServices.getText().toString().trim();
        final String Val114 = Pharmaceuticals.getText().toString().trim();
        final String Val115 = StaffAccommodation.getText().toString().trim();
        final String Val116 = ElectricitySupply.getText().toString().trim();
        final String Val117 = ColdStore.getText().toString().trim();
        final String Val118 = Doctors.getText().toString().trim();
        final String Val119 = Nurses.getText().toString().trim();
        final String Val120 = Midwives.getText().toString().trim();
        final String Val121 = NursesMidwives.getText().toString().trim();
        final String Val122 = Pharmacists.getText().toString().trim();
        final String Val123 = Radiographers.getText().toString().trim();
        final String Val124 = PharmacyTechnicians.getText().toString().trim();
        final String Val125 = MedicalLabScientist.getText().toString().trim();
        final String Val126 = PharmacyAssistant.getText().toString().trim();

        final String Val128 = LaboratoryAssistant.getText().toString().trim();
        final String Val129 = HealthSocialWorkers.getText().toString().trim();
        final String Val130 = Nutritionist.getText().toString().trim();
        final String Val131 = PublicHealthOfficers.getText().toString().trim();
        final String Val132 = CommunityHealthOfficers.getText().toString().trim();

        final String Val134 = ExtensionOfficers.getText().toString().trim();
        final String Val135 = EnvironmentalHealthOfficers.getText().toString().trim();
        final String Val136 = PsychSocial.getText().toString().trim();
        final String Val137 = TraditionalMidwives.getText().toString().trim();
        final String Val138 = SourceWater.getText().toString().trim();

        final String Val140 = ElectricitySource.getText().toString().trim();

        final String Val142 = Roads.getText().toString().trim();

        final String Val144 = Railway.getText().toString().trim();

        final String Val146 = RailwayFlowSchedule.getText().toString().trim();
        final String Val147 = Airport.getText().toString().trim();

        final String Val149 = SeaTransport.getText().toString().trim();


        final String Val152 = InternationalRadio.getText().toString().trim();
        final String Val153 = FederalRadio.getText().toString().trim();
        final String Val154 = StateRadio.getText().toString().trim();
        final String Val155 = PrivateRadio.getText().toString().trim();
        final String Val156 = SateliteTelevision.getText().toString().trim();
        final String Val157 = NTAService.getText().toString().trim();
        final String Val158 = StateTelevisionStation.getText().toString().trim();
        final String Val159 = PrivateTelevisionService.getText().toString().trim();
        final String Val160 = SocialMedia.getText().toString().trim();
        final String Val161 = PrintMedia.getText().toString().trim();
        final String Val162 = FireService.getText().toString().trim();
        final String Val163 = ArmyFormationAndCommand.getText().toString().trim();
        final String Val164 = ArmyBarracks.getText().toString().trim();
        final String Val165 = PoliceStation.getText().toString().trim();
        final String Val166 = PoliceBarracks.getText().toString().trim();
        final String Val167 = DeptStateServices.getText().toString().trim();
        final String Val168 = CivilDefence.getText().toString().trim();
        final String Val169 = ImmigrationService.getText().toString().trim();
        final String Val170 = CustomService.getText().toString().trim();
        final String Val171 = PrisonService.getText().toString().trim();


        final String Val174 = FederalHighCourt.getText().toString().trim();
        final String Val175 = ShariaCourtOfAppeal.getText().toString().trim();
        final String Val176 = IndustrialCourt.getText().toString().trim();
        final String Val177 = StateHighCourt.getText().toString().trim();
        final String Val178 = MagistrateCourt.getText().toString().trim();
        final String Val179 = CustomaryCourt.getText().toString().trim();

        final String Val181 = Banks.getText().toString().trim();
        final String Val182 = BanksCondition.getText().toString().trim();

        final String Val000 = OtherBank.getText().toString().trim();

        final String Val183 = InsuranceCompanies.getText().toString().trim();
        final String Val184 = Factories.getText().toString().trim();
        final String Val185 = DailyMarket.getText().toString().trim();
        final String Val186 = WeeklyMarket.getText().toString().trim();
        final String Val187 = LivestockMarket.getText().toString().trim();
        final String Val188 = SuperMarkets.getText().toString().trim();

        final String Val190 = Hotels.getText().toString().trim();
        final String Val191 = Tourist.getText().toString().trim();
        final String Val192 = Mosques.getText().toString().trim();
        final String Val193 = Church.getText().toString().trim();
        final String Val194 = Shrine.getText().toString().trim();
        final String Val195 = NGOsCBOs.getText().toString().trim();

        final String Val196 = genderBefore.toString().trim();
        final String Val197 = genderAfter.toString().trim();
        final String Val198 = genderInformant.toString().trim();

        final String Val199 = ageCOMMUNITYBefore.toString().trim();
        final String Val200 = ageCOMMUNITYAfter.toString().trim();
        final String Val201 = CommunityInformantAge.toString().trim();

        if (!TextUtils.isEmpty(Val1)&&
                !TextUtils.isEmpty(Val2)&&

                !TextUtils.isEmpty(Val001)&&

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

                !TextUtils.isEmpty(Val41)&&
                !TextUtils.isEmpty(Val42)&&
                !TextUtils.isEmpty(Val43)&&
                !TextUtils.isEmpty(Val44)&&
                !TextUtils.isEmpty(Val45)&&
                !TextUtils.isEmpty(Val46)&&
                !TextUtils.isEmpty(Val47)&&
                !TextUtils.isEmpty(Val48)&&
                !TextUtils.isEmpty(Val49)&&
                !TextUtils.isEmpty(Val50)&&
                !TextUtils.isEmpty(Val51)&&
                !TextUtils.isEmpty(Val52)&&
                !TextUtils.isEmpty(Val53)&&
                !TextUtils.isEmpty(Val54)&&
                !TextUtils.isEmpty(Val55)&&
                !TextUtils.isEmpty(Val56)&&
                !TextUtils.isEmpty(Val57)&&
                !TextUtils.isEmpty(Val58)&&
                !TextUtils.isEmpty(Val59)&&
                !TextUtils.isEmpty(Val60)&&
                !TextUtils.isEmpty(Val61)&&
                !TextUtils.isEmpty(Val62)&&
                !TextUtils.isEmpty(Val63)&&
                !TextUtils.isEmpty(Val64)&&
                !TextUtils.isEmpty(Val65)&&

                !TextUtils.isEmpty(Val000)&&


                !TextUtils.isEmpty(Val69)&&
                !TextUtils.isEmpty(Val70)&&
                !TextUtils.isEmpty(Val71)&&
                !TextUtils.isEmpty(Val72)&&
                !TextUtils.isEmpty(Val73)&&
                !TextUtils.isEmpty(Val74)&&
                !TextUtils.isEmpty(Val75)&&
                !TextUtils.isEmpty(Val76)&&
                !TextUtils.isEmpty(Val77)&&
                !TextUtils.isEmpty(Val78)&&
                !TextUtils.isEmpty(Val79)&&
                !TextUtils.isEmpty(Val80)&&
                !TextUtils.isEmpty(Val81)&&
                !TextUtils.isEmpty(Val82)&&
                !TextUtils.isEmpty(Val83)&&
                !TextUtils.isEmpty(Val84)&&
                !TextUtils.isEmpty(Val85)&&
                !TextUtils.isEmpty(Val86)&&
                !TextUtils.isEmpty(Val87)&&
                !TextUtils.isEmpty(Val88)&&
                !TextUtils.isEmpty(Val89)&&
                !TextUtils.isEmpty(Val90)&&
                !TextUtils.isEmpty(Val91)&&
                !TextUtils.isEmpty(Val92)&&
                !TextUtils.isEmpty(Val93)&&
                !TextUtils.isEmpty(Val94)&&
                !TextUtils.isEmpty(Val95)&&
                !TextUtils.isEmpty(Val96)&&
                !TextUtils.isEmpty(Val97)&&
                !TextUtils.isEmpty(Val98)&&
                !TextUtils.isEmpty(Val99)&&
                !TextUtils.isEmpty(Val100)&&
                !TextUtils.isEmpty(Val101)&&
                !TextUtils.isEmpty(Val102)&&
                !TextUtils.isEmpty(Val103)&&
                !TextUtils.isEmpty(Val104)&&
                !TextUtils.isEmpty(Val105)&&
                !TextUtils.isEmpty(Val106)&&
                !TextUtils.isEmpty(Val107)&&
                !TextUtils.isEmpty(Val108)&&
                !TextUtils.isEmpty(Val109)&&
                !TextUtils.isEmpty(Val110)&&
                !TextUtils.isEmpty(Val111)&&
                !TextUtils.isEmpty(Val112)&&
                !TextUtils.isEmpty(Val113)&&
                !TextUtils.isEmpty(Val114)&&
                !TextUtils.isEmpty(Val115)&&
                !TextUtils.isEmpty(Val116)&&
                !TextUtils.isEmpty(Val117)&&
                !TextUtils.isEmpty(Val118)&&
                !TextUtils.isEmpty(Val119)&&
                !TextUtils.isEmpty(Val120)&&
                !TextUtils.isEmpty(Val121)&&
                !TextUtils.isEmpty(Val122)&&
                !TextUtils.isEmpty(Val123)&&
                !TextUtils.isEmpty(Val124)&&
                !TextUtils.isEmpty(Val125)&&
                !TextUtils.isEmpty(Val126)&&

                !TextUtils.isEmpty(Val128)&&
                !TextUtils.isEmpty(Val129)&&
                !TextUtils.isEmpty(Val130)&&
                !TextUtils.isEmpty(Val131)&&
                !TextUtils.isEmpty(Val132)&&

                !TextUtils.isEmpty(Val134)&&
                !TextUtils.isEmpty(Val135)&&
                !TextUtils.isEmpty(Val136)&&
                !TextUtils.isEmpty(Val137)&&
                !TextUtils.isEmpty(Val138)&&

                !TextUtils.isEmpty(Val140)&&

                !TextUtils.isEmpty(Val142)&&

                !TextUtils.isEmpty(Val144)&&

                !TextUtils.isEmpty(Val146)&&
                !TextUtils.isEmpty(Val147)&&

                !TextUtils.isEmpty(Val149)&&



                !TextUtils.isEmpty(Val152)&&
                !TextUtils.isEmpty(Val153)&&
                !TextUtils.isEmpty(Val154)&&
                !TextUtils.isEmpty(Val155)&&
                !TextUtils.isEmpty(Val156)&&
                !TextUtils.isEmpty(Val157)&&
                !TextUtils.isEmpty(Val158)&&
                !TextUtils.isEmpty(Val159)&&

                !TextUtils.isEmpty(Val160)&&
                !TextUtils.isEmpty(Val161)&&
                !TextUtils.isEmpty(Val162)&&
                !TextUtils.isEmpty(Val163)&&
                !TextUtils.isEmpty(Val164)&&
                !TextUtils.isEmpty(Val165)&&
                !TextUtils.isEmpty(Val166)&&
                !TextUtils.isEmpty(Val167)&&
                !TextUtils.isEmpty(Val168)&&
                !TextUtils.isEmpty(Val169)&&
                !TextUtils.isEmpty(Val170)&&
                !TextUtils.isEmpty(Val171)&&


                !TextUtils.isEmpty(Val174)&&
                !TextUtils.isEmpty(Val175)&&
                !TextUtils.isEmpty(Val176)&&
                !TextUtils.isEmpty(Val177)&&
                !TextUtils.isEmpty(Val178)&&
                !TextUtils.isEmpty(Val179)&&

                !TextUtils.isEmpty(Val181)&&
                !TextUtils.isEmpty(Val182)&&
                !TextUtils.isEmpty(Val183)&&
                !TextUtils.isEmpty(Val184)&&
                !TextUtils.isEmpty(Val185)&&
                !TextUtils.isEmpty(Val186)&&
                !TextUtils.isEmpty(Val187)&&
                !TextUtils.isEmpty(Val188)&&

                !TextUtils.isEmpty(Val190)&&
                !TextUtils.isEmpty(Val191)&&
                !TextUtils.isEmpty(Val192)&&
                !TextUtils.isEmpty(Val193)&&
                !TextUtils.isEmpty(Val194)&&
                !TextUtils.isEmpty(Val195)&&
                !TextUtils.isEmpty(Val196)&&
                !TextUtils.isEmpty(Val197)&&
                !TextUtils.isEmpty(Val198)){

            State.setText("");
            LocalGov.setText("");
            TownVillage.setText("");
            Longitude.setText("");
            Latitude.setText("");
            Status.setText("");
            Population.setText("");
            COMMUNITYSizeAdultMaleBefore.setText("");
            COMMUNITYSizeAdultFemaleBefore.setText("");
            COMMUNITYSizeChildrenMaleBefore.setText("");
            COMMUNITYSizeChildrenFemaleBefore.setText("");
            COMMUNITYSizeAdultMaleAfter.setText("");
            COMMUNITYSizeAdultFemaleAfter.setText("");
            COMMUNITYSizeChildrenMaleAfter.setText("");
            COMMUNITYSizeChildrenFemaleAfter.setText("");
            COMMUNITYMemberConditionElderlyBefore.setText("");
            COMMUNITYMemberConditionDisabledBefore.setText("");
            COMMUNITYMemberConditionPregnantBefore.setText("");
            COMMUNITYMemberConditionLactatingBefore.setText("");
            COMMUNITYMemberConditionInfantBefore.setText("");
            COMMUNITYMemberConditionChildrenBefore.setText("");
            COMMUNITYMemberConditionElderlyAfter.setText("");
            COMMUNITYMemberConditionDisabledAfter.setText("");
            COMMUNITYMemberConditionPregnantAfter.setText("");
            COMMUNITYMemberConditionLactatingAfter.setText("");
            COMMUNITYMemberConditionInfantAfter.setText("");
            COMMUNITYMemberConditionChildrenAfter.setText("");
            AttackedDeath.setText("");
            AttackedMissing.setText("");
            AttacksNumber.setText("");
            COMMUNITYMemberAdultMaleLostAfter.setText("");
            COMMUNITYMemberAdultFemaleLostAfter.setText("");
            COMMUNITYMemberChildrenLostAfter.setText("");
            KeyMemberQualificationBefore.setText("");
            KeyMemberQualificationAfter.setText("");
            KeyMemberOccupationBefore.setText("");
            KeyMemberOccupationAfter.setText("");
            KeyMemberOtherOccupationBefore.setText("");
            KeyMemberOtherOccupationAfter.setText("");
            LGASecretariat.setText("");
            EmirPalace.setText("");
            DHsPalace.setText("");
            VillageHeadPalace.setText("");
            TownHall.setText("");
            PlayGroundCenter.setText("");
            Primary.setText("");
            Secondary.setText("");
            Technical.setText("");
            Commercial.setText("");
            Tertiary.setText("");
            OtherSchool.setText("");
            PrimaryClassRoom.setText("");
            SecondaryClassRoom.setText("");
            TechnicalClassRoom.setText("");
            CommercialClassRoom.setText("");
            TertiaryClassRoom.setText("");
            IslamiyaClassRoom.setText("");
            OtherSchoolClassRoom.setText("");
            PrimaryWorkshop.setText("");
            SecondaryWorkshop.setText("");
            TechnicalWorkshop.setText("");
            CommercialWorkshop.setText("");
            TertiaryWorkshop.setText("");
            IslamiyaWorkshop.setText("");
            OtherSchoolWorkshop.setText("");
            StaffPrimary.setText("");
            StaffSecondary.setText("");
            StaffTechnical.setText("");
            StaffCommercial.setText("");
            StaffTertiary.setText("");
            StaffIslamiya.setText("");
            StaffOther.setText("");
            NonStaffPrimary.setText("");
            NonStaffSecondary.setText("");
            NonStaffTechnical.setText("");
            NonStaffCommercial.setText("");
            NonStaffTertiary.setText("");
            NonStaffIslamiya.setText("");
            NonStaffOther.setText("");
            SportingPrimary.setText("");
            SportingSecondary.setText("");
            SportingTechnical.setText("");
            SportingCommercial.setText("");
            SportingTertiary.setText("");
            SportingIslamiya.setText("");
            SportingOther.setText("");
            OtherStaffHousing.setText("");
            StudentHostel.setText("");
            OtherToiletFacilities.setText("");
            PerimeterFence.setText("");
            OwnerShipPrimary.setText("");
            OwnerShipSecondary.setText("");
            OwnerShipTechnical.setText("");
            OwnerShipCommercial.setText("");
            OwnerShipTertiary.setText("");
            OwnerShipIslamiya.setText("");
            OwnerShipOther.setText("");
            HealthPost.setText("");
            HealthCenter.setText("");
            PrimaryHealthCenter.setText("");
            PrivateHospital.setText("");
            GeneralHospital.setText("");
            FMCSpecialistHospital.setText("");
            OtherHospitals.setText("");
            OperationTheater.setText("");
            Laboratories.setText("");
            Mortuary.setText("");
            Ambulances.setText("");
            Maternity.setText("");
            NutritionalServices.setText("");
            Pharmaceuticals.setText("");
            StaffAccommodation.setText("");
            ElectricitySupply.setText("");
            ColdStore.setText("");
            Doctors.setText("");
            Nurses.setText("");
            Midwives.setText("");
            NursesMidwives.setText("");
            Pharmacists.setText("");
            Radiographers.setText("");
            PharmacyTechnicians.setText("");
            MedicalLabScientist.setText("");
            PharmacyAssistant.setText("");
            LaboratoryTechnicians.setText("");
            LaboratoryAssistant.setText("");
            HealthSocialWorkers.setText("");
            Nutritionist.setText("");
            PublicHealthOfficers.setText("");
            CommunityHealthOfficers.setText("");
            CommunityHealth.setText("");
            ExtensionOfficers.setText("");
            EnvironmentalHealthOfficers.setText("");
            PsychSocial.setText("");
            TraditionalMidwives.setText("");
            SourceWater.setText("");
            Condition.setText("");
            ElectricitySource.setText("");
            ElectricityCondition.setText("");
            Roads.setText("");
            RoadsCondition.setText("");
            Railway.setText("");
            RailwayConditions.setText("");
            RailwayFlowSchedule.setText("");
            Airport.setText("");
            AirportConditions.setText("");
            SeaTransport.setText("");
            SeaConditions.setText("");
            Communication.setText("");
            InternationalRadio.setText("");
            FederalRadio.setText("");
            StateRadio.setText("");
            PrivateRadio.setText("");
            SateliteTelevision.setText("");
            NTAService.setText("");
            StateTelevisionStation.setText("");
            PrivateTelevisionService.setText("");
            SocialMedia.setText("");
            PrintMedia.setText("");
            FireService.setText("");
            ArmyFormationAndCommand.setText("");
            ArmyBarracks.setText("");
            PoliceStation.setText("");
            PoliceBarracks.setText("");
            DeptStateServices.setText("");
            CivilDefence.setText("");
            ImmigrationService.setText("");
            CustomService.setText("");
            PrisonService.setText("");
            Vigelante.setText("");
            OtherSecurity.setText("");
            FederalHighCourt.setText("");
            ShariaCourtOfAppeal.setText("");
            IndustrialCourt.setText("");
            StateHighCourt.setText("");
            MagistrateCourt.setText("");
            CustomaryCourt.setText("");
            OtherCourt.setText("");
            Banks.setText("");
            BanksCondition.setText("");
            InsuranceCompanies.setText("");
            Factories.setText("");
            DailyMarket.setText("");
            WeeklyMarket.setText("");
            LivestockMarket.setText("");
            SuperMarkets.setText("");
            OtherMarket.setText("");
            Hotels.setText("");
            Tourist.setText("");
            Mosques.setText("");
            Church.setText("");
            Shrine.setText("");
            NGOsCBOs.setText("");

            final DatabaseReference newPost = mDatabase.push();
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("MotoPark").setValue(Val001);
                    newPost.child("State").setValue(Val1);
                    newPost.child("LocalGovernment").setValue(Val2);
                    newPost.child("TownVillage").setValue(Val3);
                    newPost.child("GeographicalLocationLongitude").setValue(Val4);
                    newPost.child("GeographicalLocationLatitude").setValue(Val5);
                    newPost.child("StatusOfTownVillage").setValue(Val6);
                    newPost.child("EstimatedTotalPopulationOfTownVillage").setValue(Val7);
                    newPost.child("NameOfMaleAdultBefore").setValue(Val8);
                    newPost.child("NameOfFemaleAdultBefore").setValue(Val9);
                    newPost.child("NameOfMaleChildrenBefore").setValue(Val10);
                    newPost.child("NameOfFemaleChildrenBefore").setValue(Val11);
                    newPost.child("NameOfMaleAdultAfter").setValue(Val12);
                    newPost.child("NameOfFemaleAdultAfter").setValue(Val13);
                    newPost.child("NameOfMaleChildrenAfter").setValue(Val14);
                    newPost.child("NameOfFemaleChildrenAfter").setValue(Val15);
                    newPost.child("ElderlyBefore").setValue(Val16);
                    newPost.child("DisabledBefore").setValue(Val17);
                    newPost.child("PregnantBefore").setValue(Val18);
                    newPost.child("LactatingBefore").setValue(Val19);
                    newPost.child("InfantBefore").setValue(Val20);
                    newPost.child("InfantAfter").setValue(Val21);
                    newPost.child("ChildrenBefore").setValue(Val22);
                    newPost.child("ElderlyAfter").setValue(Val23);
                    newPost.child("DisabledAfter").setValue(Val24);
                    newPost.child("PregnantAfter").setValue(Val25);
                    newPost.child("InfantAfter").setValue(Val26);
                    newPost.child("ChildrenAfter").setValue(Val27);
                    newPost.child("WhenWasYourCommunityAttackedByInsurgentDeath").setValue(Val28);
                    newPost.child("WhenWasYourCommunityAttackedByInsurgentMissing").setValue(Val29);
                    newPost.child("HowManyTimesDidInsurgentAttackedYourCommunityNumberOfAttacks").setValue(Val30);
                    newPost.child("HaveYouLostAnyAdultMale").setValue(Val31);
                    newPost.child("HaveYouLostAnyAdultFemale").setValue(Val32);
                    newPost.child("HaveYouLostAnyChildren").setValue(Val33);
                    newPost.child("EducationQualificationBefore").setValue(Val34);
                    newPost.child("EducationQualificationAfter").setValue(Val35);
                    newPost.child("PrimaryOccupationBefore").setValue(Val36);
                    newPost.child("PrimaryOccupationAfter").setValue(Val37);
                    newPost.child("OtherOccupationBefore").setValue(Val38);

                    newPost.child("LGASecretariat").setValue(Val40);
                    newPost.child("EmirsPalace").setValue(Val41);
                    newPost.child("DistrictHeadPalace").setValue(Val42);
                    newPost.child("VillageWardHeadPalace").setValue(Val43);
                    newPost.child("TownHall").setValue(Val44);
                    newPost.child("PalaceGroundCulturalCenter").setValue(Val45);
                    newPost.child("EducationInfrastructurePrimary").setValue(Val46);
                    newPost.child("EducationInfrastructureSecondary").setValue(Val47);
                    newPost.child("EducationInfrastructureTechnical").setValue(Val48);
                    newPost.child("EducationInfrastructureCommercial").setValue(Val49);
                    newPost.child("EducationInfrastructureTertiary").setValue(Val50);
                    newPost.child("EducationInfrastructureOther").setValue(Val51);
                    newPost.child("ClassRoomOfficePrimary").setValue(Val52);
                    newPost.child("ClassRoomOfficeSecondary").setValue(Val53);
                    newPost.child("ClassRoomOfficeTechnical").setValue(Val54);
                    newPost.child("ClassRoomOfficeCommercial").setValue(Val55);
                    newPost.child("ClassRoomOfficeTertiary").setValue(Val56);
                    newPost.child("ClassRoomOfficeIslamiya").setValue(Val57);
                    newPost.child("ClassRoomOfficeOther").setValue(Val58);
                    newPost.child("PrimaryWorkshop").setValue(Val59);
                    newPost.child("SecondaryWorkshop").setValue(Val60);
                    newPost.child("TechnicalWorkshop").setValue(Val61);
                    newPost.child("CommercialWorkshop").setValue(Val62);
                    newPost.child("TertiaryWorkshop").setValue(Val63);
                    newPost.child("IslamiyaWorkshop").setValue(Val64);
                    newPost.child("OtherSchoolWorkshop").setValue(Val65);


                    newPost.child("StaffPrimary").setValue(Val69);
                    newPost.child("StaffSecondary").setValue(Val70);
                    newPost.child("StaffTechnical").setValue(Val71);
                    newPost.child("StaffCommercial").setValue(Val72);
                    newPost.child("StaffTertiary").setValue(Val73);
                    newPost.child("StaffIslamiya").setValue(Val74);
                    newPost.child("StaffOther").setValue(Val75);
                    newPost.child("NonStaffPrimary").setValue(Val76);
                    newPost.child("NonStaffSecondary").setValue(Val77);
                    newPost.child("NonStaffTechnical").setValue(Val78);
                    newPost.child("NonStaffCommercial").setValue(Val79);
                    newPost.child("NonStaffTertiary").setValue(Val80);
                    newPost.child("NonStaffIslamiya").setValue(Val81);
                    newPost.child("NonStaffOther").setValue(Val82);
                    newPost.child("NonStaffOther").setValue(Val83);
                    newPost.child("SportingSecondary").setValue(Val84);
                    newPost.child("SportingTechnical").setValue(Val85);
                    newPost.child("SportingCommercial").setValue(Val86);
                    newPost.child("SportingTertiary").setValue(Val87);
                    newPost.child("SportingIslamiya").setValue(Val88);
                    newPost.child("SportingOther").setValue(Val89);
                    newPost.child("OtherStaffHousing").setValue(Val90);
                    newPost.child("StudentHostel").setValue(Val91);
                    newPost.child("OtherToiletFacilities").setValue(Val92);
                    newPost.child("PerimeterFence").setValue(Val93);
                    newPost.child("OwnerShipPrimary").setValue(Val94);
                    newPost.child("OwnerShipSecondary").setValue(Val95);
                    newPost.child("OwnerShipTechnical").setValue(Val96);
                    newPost.child("OwnerShipCommercial").setValue(Val97);
                    newPost.child("OwnerShipTertiary").setValue(Val98);
                    newPost.child("OwnerShipIslamiya").setValue(Val99);
                    newPost.child("OwnerShipOther").setValue(Val100);
                    newPost.child("HealthPost").setValue(Val101);
                    newPost.child("HealthCenter").setValue(Val102);
                    newPost.child("PrimaryHealthCenter").setValue(Val103);
                    newPost.child("PrivateHospital").setValue(Val104);
                    newPost.child("GeneralHospital").setValue(Val105);
                    newPost.child("FMCSpecialistHospital").setValue(Val106);
                    newPost.child("OtherHospitals").setValue(Val107);
                    newPost.child("OperationTheater").setValue(Val108);
                    newPost.child("Laboratories").setValue(Val109);
                    newPost.child("Mortuary").setValue(Val110);
                    newPost.child("Ambulances").setValue(Val111);
                    newPost.child("Maternity").setValue(Val112);
                    newPost.child("NutritionalServices").setValue(Val113);
                    newPost.child("Pharmaceuticals").setValue(Val114);
                    newPost.child("StaffAccommodation").setValue(Val115);
                    newPost.child("ElectricitySupply").setValue(Val116);
                    newPost.child("ColdStore").setValue(Val117);
                    newPost.child("Doctors").setValue(Val118);
                    newPost.child("Nurses").setValue(Val119);
                    newPost.child("Midwives").setValue(Val120);
                    newPost.child("NursesMidwives").setValue(Val121);
                    newPost.child("Pharmacists").setValue(Val122);
                    newPost.child("Radiographers").setValue(Val123);
                    newPost.child("PharmacyTechnicians").setValue(Val124);
                    newPost.child("MedicalLabScientist").setValue(Val125);
                    newPost.child("PharmacyAssistant").setValue(Val126);

                    newPost.child("LaboratoryAssistant").setValue(Val128);
                    newPost.child("HealthSocialWorkers").setValue(Val129);
                    newPost.child("Nutritionist").setValue(Val130);
                    newPost.child("PublicHealthOfficers").setValue(Val131);
                    newPost.child("CommunityHealthOfficers").setValue(Val132);

                    newPost.child("ExtensionOfficers").setValue(Val134);
                    newPost.child("EnvironmentalHealthOfficers").setValue(Val135);
                    newPost.child("PsychSocial").setValue(Val136);
                    newPost.child("TraditionalMidwives").setValue(Val137);
                    newPost.child("SourceWater").setValue(Val138);

                    newPost.child("ElectricitySource").setValue(Val140);

                    newPost.child("Roads").setValue(Val142);

                    newPost.child("Railway").setValue(Val144);

                    newPost.child("RailwayFlowSchedule").setValue(Val146);
                    newPost.child("Airport").setValue(Val147);

                    newPost.child("SeaTransport").setValue(Val149);



                    newPost.child("InternationalRadio").setValue(Val152);
                    newPost.child("FederalRadio").setValue(Val153);
                    newPost.child("StateRadio").setValue(Val154);
                    newPost.child("PrivateRadio").setValue(Val155);
                    newPost.child("SateliteTelevision").setValue(Val156);
                    newPost.child("NTAService").setValue(Val157);
                    newPost.child("StateTelevisionStation").setValue(Val158);
                    newPost.child("PrivateTelevisionService").setValue(Val159);
                    newPost.child("SocialMedia").setValue(Val160);
                    newPost.child("PrintMedia").setValue(Val161);
                    newPost.child("FireService").setValue(Val162);
                    newPost.child("ArmyFormationAndCommand").setValue(Val163);
                    newPost.child("ArmyBarracks").setValue(Val164);
                    newPost.child("PoliceStation").setValue(Val165);
                    newPost.child("PoliceBarracks").setValue(Val166);
                    newPost.child("DeptStateServices").setValue(Val167);
                    newPost.child("CivilDefence").setValue(Val168);
                    newPost.child("ImmigrationService").setValue(Val169);
                    newPost.child("CustomService").setValue(Val170);

                    newPost.child("PrisonService").setValue(Val171);


                    newPost.child("FederalHighCourt").setValue(Val174);
                    newPost.child("ShariaCourtOfAppeal").setValue(Val175);
                    newPost.child("IndustrialCourt").setValue(Val176);
                    newPost.child("StateHighCourt").setValue(Val177);
                    newPost.child("MagistrateCourt").setValue(Val178);
                    newPost.child("CustomaryCourt").setValue(Val179);

                    newPost.child("Banks").setValue(Val181);

                    newPost.child("OtherBanks").setValue(Val000);

                    newPost.child("BanksCondition").setValue(Val182);
                    newPost.child("InsuranceCompanies").setValue(Val183);
                    newPost.child("Factories").setValue(Val184);
                    newPost.child("DailyMarket").setValue(Val185);
                    newPost.child("WeeklyMarket").setValue(Val186);
                    newPost.child("LivestockMarket").setValue(Val187);
                    newPost.child("SuperMarkets").setValue(Val188);

                    newPost.child("Hotels").setValue(Val190);

                    newPost.child("Tourist").setValue(Val191);
                    newPost.child("Mosques").setValue(Val192);
                    newPost.child("Church").setValue(Val193);
                    newPost.child("Shrine").setValue(Val194);
                    newPost.child("NGOsCBOs").setValue(Val195);

                    newPost.child("GenderOfCommunityHeadBefore").setValue(Val196);
                    newPost.child("GenderOfCommunityHeadAfter").setValue(Val197);
                    newPost.child("GenderOfInformant").setValue(Val198)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(KeyInformants.this,"Your Data Is Stored To CloudDatabase", Toast.LENGTH_SHORT);
                                    }else {
                                        Toast.makeText(KeyInformants.this,"Data Stored, Connect to Internet to Push to CloudDatabase", Toast.LENGTH_SHORT);
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

    public void FocusBackToMenu(View view) {
    }

    public void BackToMenu(View view) {
        Intent Back = new Intent(this, MenuScreen.class);
        startActivity(Back);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.StateSpinner){
            String state = parent.getItemAtPosition(position).toString();
            State.setText(state);
        }else if (spinner.getId() == R.id.LocalGovSpinner){
            String localGov = parent.getItemAtPosition(position).toString();
            LocalGov.setText(localGov);
        }else if (spinner.getId() == R.id.cOccupationBeforeSpinner){
            String memberOccupationBefore = parent.getItemAtPosition(position).toString();
            KeyMemberOccupationBefore.setText(memberOccupationBefore);
        }else if (spinner.getId() == R.id.cOccupationAfterSpinner){
            String memberOccupationAfter = parent.getItemAtPosition(position).toString();
            KeyMemberOccupationAfter.setText(memberOccupationAfter);
        }else if(spinner.getId() == R.id.LGASecretariatSpinner){
            String LGASec = parent.getItemAtPosition(position).toString();
            LGASecretariat.setText(LGASec);
        }else if (spinner.getId() == R.id.EmirSpinner){
            String Emir = parent.getItemAtPosition(position).toString();
            EmirPalace.setText(Emir);
        }else if (spinner.getId() == R.id.DHsPalaceSpinner){
            String DHs = parent.getItemAtPosition(position).toString();
            DHsPalace.setText(DHs);
        }else if (spinner.getId() == R.id.VillageHeadPalaceSpinner){
            String Village = parent.getItemAtPosition(position).toString();
            VillageHeadPalace.setText(Village);
        }else if (spinner.getId() == R.id.TownHallSpinner){
            String Town = parent.getItemAtPosition(position).toString();
            TownHall.setText(Town);
        }else if (spinner.getId() == R.id.PlayGroundCenterSpinner){
            String PayGrounf = parent.getItemAtPosition(position).toString();
            PlayGroundCenter.setText(PayGrounf);
        }else if (spinner.getId() == R.id.PrimarySpinner){
            String PrimaryS = parent.getItemAtPosition(position).toString();
            Primary.setText(PrimaryS);
        }else if (spinner.getId() == R.id.SecondarySpinner){
            String SecText = parent.getItemAtPosition(position).toString();
            Secondary.setText(SecText);
        }else if (spinner.getId() == R.id.TechnicalSpinner){
            String TecTex = parent.getItemAtPosition(position).toString();
            Technical.setText(TecTex);
        }else if (spinner.getId() == R.id.CommercialSpinner){
            String ComText = parent.getItemAtPosition(position).toString();
            Commercial.setText(ComText);
        }else if (spinner.getId() == R.id.TertiarySpinner){
            String TerText = parent.getItemAtPosition(position).toString().trim();
            Tertiary.setText(TerText);
        }else if (spinner.getId() == R.id.ClassroomPrimarySpinner){
            String ClassPri = parent.getItemAtPosition(position).toString();
                PrimaryClassRoom.setText(ClassPri);
        }else if (spinner.getId() == R.id.ClassroomSecondarySpinner){
            String ClassSec = parent.getItemAtPosition(position).toString();
            SecondaryClassRoom.setText(ClassSec);
        }else if (spinner.getId() == R.id.ClassroomTechnicalSpinner){
            String ClassTec = parent.getItemAtPosition(position).toString();
            CommercialClassRoom.setText(ClassTec);
        }else if (spinner.getId() == R.id.ClassroomTertiarySpinner){
            String ClassTer = parent.getItemAtPosition(position).toString();
            TertiaryClassRoom.setText(ClassTer);
        }else if (spinner.getId() == R.id.ClassroomIslamiyaSpinner){
            String ClassIslam = parent.getItemAtPosition(position).toString();
            IslamiyaClassRoom.setText(ClassIslam);
        }else if (spinner.getId() == R.id.LaboratoriesPrimarySpinner){
            String LabPri = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LabPri);
        }else if (spinner.getId() == R.id.LaboratoriesSecondarySpinner){
            String LabSec = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LabSec);
        }else if (spinner.getId() == R.id.LaboratoriesTechnicalSpinner){
            String LabTec = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LabTec);
        }else if (spinner.getId() == R.id.LaboratoriesTertiarySpinner){
            String LabTer = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LabTer);
        }else if (spinner.getId() == R.id.LaboratoriesIslamiyaSpinner){
            String LabIslam = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LabIslam);
        }else if (spinner.getId() == R.id.TeachersPrimarySpinner){
            String TeaPri = parent.getItemAtPosition(position).toString();
            StaffPrimary.setText(TeaPri);
        }else if (spinner.getId() == R.id.TeachersSecondarySpinner){
            String TeaSec = parent.getItemAtPosition(position).toString();
            StaffSecondary.setText(TeaSec);
        }else if (spinner.getId() == R.id.TeachersTechnicalSpinner){
            String TeaTec = parent.getItemAtPosition(position).toString();
            StaffTechnical.setText(TeaTec);
        }else if (spinner.getId() == R.id.TeachersTertiarySpinner){
            String TeaTer = parent.getItemAtPosition(position).toString();
            StaffTertiary.setText(TeaTer);
        }else if (spinner.getId() == R.id.TeachersIslamiyaSpinner){
            String TeaIslam = parent.getItemAtPosition(position).toString();
            StaffIslamiya.setText(TeaIslam);
        }else if (spinner.getId() == R.id.NonStaffPrimarySpinner){
            String NonStaffPri = parent.getItemAtPosition(position).toString();
            NonStaffPrimary.setText(NonStaffPri);
        }else if (spinner.getId() == R.id.NonStaffSecondarySpinner){
            String NonStaffSec = parent.getItemAtPosition(position).toString();
            NonStaffSecondary.setText(NonStaffSec);
        }else if (spinner.getId() == R.id.NonStaffTechnicalSpinner){
            String NonStaffTec = parent.getItemAtPosition(position).toString();
            NonStaffTechnical.setText(NonStaffTec);
        }else if (spinner.getId() == R.id.NonStaffTertiarySpinner){
            String NonStaffTer = parent.getItemAtPosition(position).toString();
            NonStaffCommercial.setText(NonStaffTer);
        }else if (spinner.getId() == R.id.NonStaffCommercialSpinner){
            String NonStaffIslam = parent.getItemAtPosition(position).toString();
            NonStaffCommercial.setText(NonStaffIslam);
        }else if (spinner.getId() == R.id.NonStaffIslamiyaSpinner){
            String IslamStaff = parent.getItemAtPosition(position).toString();
            NonStaffIslamiya.setText(IslamStaff);
        }else if (spinner.getId() == R.id.SportingPrimarySpinner){
            String SportingPri = parent.getItemAtPosition(position).toString();
            SportingPrimary.setText(SportingPri);
        }else if (spinner.getId() == R.id.SportingSecondarySpinner){
            String SportingSec = parent.getItemAtPosition(position).toString();
            SportingSecondary.setText(SportingSec);
        }else if (spinner.getId() == R.id.SportingTechnicalSpinner){
            String SportingTec = parent.getItemAtPosition(position).toString();
            SportingTechnical.setText(SportingTec);
        }else if (spinner.getId() == R.id.SportingTertiarySpinner){
            String SportingTer = parent.getItemAtPosition(position).toString();
            SportingCommercial.setText(SportingTer);
        }else if (spinner.getId() == R.id.SportingIslamiyaSpinner){
            String SportingIslam = parent.getItemAtPosition(position).toString();
            SportingCommercial.setText(SportingIslam);
        }else if (spinner.getId() == R.id.SportingIslamiyaSpinner){
            String IslamStaff = parent.getItemAtPosition(position).toString();
            SportingIslamiya.setText(IslamStaff);
        }else  if (spinner.getId() == R.id.StaffHousingSpinner){
            String StaffHouse = parent.getItemAtPosition(position).toString();
            StaffAccommodation.setText(StaffHouse);
        }else  if (spinner.getId() == R.id.StudentsHostelSpinner){
            String StudHostel = parent.getItemAtPosition(position).toString();
            StudentHostel.setText(StudHostel);
        }else if (spinner.getId() == R.id.ToiletSpinner){
            String ToiText = parent.getItemAtPosition(position).toString();
            OtherToiletFacilities.setText(ToiText);
        }else if (spinner.getId() == R.id.PerimeterSpinner){
            String PerText = parent.getItemAtPosition(position).toString();
            PerimeterFence.setText(PerText);
        }else if (spinner.getId() == R.id.OwnershipPrimarySpinner){
            String OwnershipPri = parent.getItemAtPosition(position).toString();
            OwnerShipPrimary.setText(OwnershipPri);
        }else if (spinner.getId() == R.id.OwnershipSecondarySpinner){
            String OwnershipSec = parent.getItemAtPosition(position).toString();
            OwnerShipSecondary.setText(OwnershipSec);
        }else if (spinner.getId() == R.id.OwnershipTechnicalSpinner){
            String OwnershipTec = parent.getItemAtPosition(position).toString();
            OwnerShipTechnical.setText(OwnershipTec);
        }else if (spinner.getId() == R.id.OwnershipTertiarySpinner){
            String OwnershipTer = parent.getItemAtPosition(position).toString();
            OwnerShipCommercial.setText(OwnershipTer);
        }else if (spinner.getId() == R.id.OwnershipCommercialSpinner){
            String OwnershipIslam = parent.getItemAtPosition(position).toString();
            OwnerShipIslamiya.setText(OwnershipIslam);
        }else if (spinner.getId() == R.id.OwnershipIslamiyaSpinner){
            String IslamStaff = parent.getItemAtPosition(position).toString();
            OwnerShipIslamiya.setText(IslamStaff);
        }else if (spinner.getId() == R.id.HealthPostSpinner){
            String HPost = parent.getItemAtPosition(position).toString();
            HealthPost.setText(HPost);
        }else if (spinner.getId() == R.id.HealthCenterSpinner){
            String HCPost = parent.getItemAtPosition(position).toString();
            HealthCenter.setText(HCPost);
        }else if (spinner.getId() == R.id.PrivateHospitalSpinner){
            String PHC = parent.getItemAtPosition(position).toString();
            PrivateHospital.setText(PHC);
        }else if (spinner.getId() == R.id.GeneralHospitalSpinner){
            String GHs = parent.getItemAtPosition(position).toString();
            GeneralHospital.setText(GHs);
        }else if (spinner.getId() == R.id.FMCHospitalSpinner){
            String FMC = parent.getItemAtPosition(position).toString();
            FMCSpecialistHospital.setText(FMC);
        }else if (spinner.getId() == R.id.OperationTheatersSpinner){
            String OPT = parent.getItemAtPosition(position).toString();
            OperationTheater.setText(OPT);
        }

        else if (spinner.getId() == R.id.LaboratoriesSpinner){
            String LAB = parent.getItemAtPosition(position).toString();
            Laboratories.setText(LAB);
        }

        else if (spinner.getId() == R.id.MortuarySpinner){
            String MOT = parent.getItemAtPosition(position).toString();
            Mortuary.setText(MOT);
        }

        else if (spinner.getId() == R.id.AmbulancesSpinner){
            String AMB = parent.getItemAtPosition(position).toString();
            Ambulances.setText(AMB);
        }

        else if (spinner.getId() == R.id.ChildcareSpinner){
            String CHD = parent.getItemAtPosition(position).toString();
            Maternity.setText(CHD);
        }

        else if (spinner.getId() == R.id.NutritionalServicesSpinner){
            String NTS = parent.getItemAtPosition(position).toString();
            NutritionalServices.setText(NTS);
        }

        else if (spinner.getId() == R.id.PharmaceuticalsSpinner){
            String PHN = parent.getItemAtPosition(position).toString();
            Pharmaceuticals.setText(PHN);
        }

        else if (spinner.getId() == R.id.StaffHousingSpinner){
            String STF = parent.getItemAtPosition(position).toString();
            OtherStaffHousing.setText(STF);
        }

        else if (spinner.getId() == R.id.ElectricitySpinner){
            String ELC = parent.getItemAtPosition(position).toString();
            ElectricitySupply.setText(ELC);
        }

        else if (spinner.getId() == R.id.ColdStoreSpinner){
            String CLD = parent.getItemAtPosition(position).toString();
            ColdStore.setText(CLD);
        }

        else if (spinner.getId() == R.id.DoctorsSpinner){
            String DOC = parent.getItemAtPosition(position).toString();
            Doctors.setText(DOC);
        }

        else if (spinner.getId() == R.id.NursesMidwivesSpinner){
            String NRS = parent.getItemAtPosition(position).toString();
            Doctors.setText(NRS);
        }

        else if (spinner.getId() == R.id.MidwivesSpinner){
            String MDW = parent.getItemAtPosition(position).toString();
            Midwives.setText(MDW);
        }

        else if (spinner.getId() == R.id.NursesMidwivesSpinner){
            String NAW = parent.getItemAtPosition(position).toString();
            NursesMidwives.setText(NAW);
        }

        else if (spinner.getId() == R.id.PharmacistSpinner){
            String PHM = parent.getItemAtPosition(position).toString();
            Pharmacists.setText(PHM);
        }

        else if (spinner.getId() == R.id.RadiographersSpinner){
            String RAD = parent.getItemAtPosition(position).toString();
            Radiographers.setText(RAD);
        }

        else if (spinner.getId() == R.id.PharmacyTechniciansSpinner){
            String PCT = parent.getItemAtPosition(position).toString();
            PharmacyTechnicians.setText(PCT);
        }

        else if (spinner.getId() == R.id.MedicalLabScientistSpinner){
            String MEDL = parent.getItemAtPosition(position).toString();
            MedicalLabScientist.setText(MEDL);
        }

        else if (spinner.getId() == R.id.PharmacyAssistantSpinner){
            String PHS = parent.getItemAtPosition(position).toString();
            PharmacyAssistant.setText(PHS);
        }

        else if (spinner.getId() == R.id.LaboratoryAssistantSpinner){
            String LAA = parent.getItemAtPosition(position).toString();
            LaboratoryAssistant.setText(LAA);
        }

        else if (spinner.getId() == R.id.HealthSocialWorkersSpinner){
            String HSW = parent.getItemAtPosition(position).toString();
            HealthSocialWorkers.setText(HSW);
        }

        else if (spinner.getId() == R.id.NutritionistSpinner){
            String NUT = parent.getItemAtPosition(position).toString();
            Nutritionist.setText(NUT);
        }

        else if (spinner.getId() == R.id.PublicHealthOfficersSpinner){
            String PHO = parent.getItemAtPosition(position).toString();
            PublicHealthOfficers.setText(PHO);
        }

        else if (spinner.getId() == R.id.CommunityHealthOfficersSpinner){
            String CHO = parent.getItemAtPosition(position).toString();
            CommunityHealthOfficers.setText(CHO);
        }

        else if (spinner.getId() == R.id.ExtensionOfficersSpinner){
            String EXT = parent.getItemAtPosition(position).toString();
            ExtensionOfficers.setText(EXT);
        }

        else if (spinner.getId() == R.id.EnviromentalOfficersSpinner){
            String EVR = parent.getItemAtPosition(position).toString();
            EnvironmentalHealthOfficers.setText(EVR);
        }

        else if (spinner.getId() == R.id.PsychoSocialSpinner){
            String PSY = parent.getItemAtPosition(position).toString();
            PsychSocial.setText(PSY);
        }

        else if (spinner.getId() == R.id.TradionalMiddwivesSpinner){
            String TRD = parent.getItemAtPosition(position).toString();
            TraditionalMidwives.setText(TRD);
        }

        else if (spinner.getId() == R.id.WaterSourceSpinner){
            String WTs = parent.getItemAtPosition(position).toString();
            SourceWater.setText(WTs);
        }

        else if (spinner.getId() == R.id.WaterConditionSpinner){
            String WTc = parent.getItemAtPosition(position).toString();
            Condition.setText(WTc);
        }

        else if (spinner.getId() == R.id.RailwaySpinner){
            String RLW = parent.getItemAtPosition(position).toString();
            Railway.setText(RLW);
        }

        else if (spinner.getId() == R.id.RailwayFlowScheduleSpinner){
            String RLT = parent.getItemAtPosition(position).toString();
            RailwayFlowSchedule.setText(RLT);
        }

        else if (spinner.getId() == R.id.AirportSpinner){
            String AIR = parent.getItemAtPosition(position).toString();
            Airport.setText(AIR);
        }

        else if (spinner.getId() == R.id.SeaPortSpinner){
            String SEA = parent.getItemAtPosition(position).toString();
            SeaTransport.setText(SEA);
        }

        else if (spinner.getId() == R.id.TrunkSpinner){
            String TRK = parent.getItemAtPosition(position).toString();
            RoadsCondition.setText(TRK);
        }

        else if (spinner.getId() == R.id.BridgeSpinner){
            String BRG = parent.getItemAtPosition(position).toString();
            Roads.setText(BRG);
        }

        else if (spinner.getId() == R.id.MotorParkSpinner){
            String OPT = parent.getItemAtPosition(position).toString();
            MotoParkText.setText(OPT);
        }

        else if (spinner.getId() == R.id.MTNSpinner){
            String MTN = parent.getItemAtPosition(position).toString();
            Communication1.setText(MTN);
        }

        else if (spinner.getId() == R.id.AirtelSpinner){
            String AIT = parent.getItemAtPosition(position).toString();
            Communication2.setText(AIT);
        }

        else if (spinner.getId() == R.id.GloSpinner){
            String GLO = parent.getItemAtPosition(position).toString();
            Communication3.setText(GLO);
        }

        else if (spinner.getId() == R.id.Mobile9Spinner){
            String MB = parent.getItemAtPosition(position).toString();
            Communication4.setText(MB);
        }

        else if (spinner.getId() == R.id.InternationalRadioSpinner){
            String INR = parent.getItemAtPosition(position).toString();
            InternationalRadio.setText(INR);
        }

        else if (spinner.getId() == R.id.FederalRadioSpinner){
            String FRN = parent.getItemAtPosition(position).toString();
            FederalRadio.setText(FRN);
        }

        else if (spinner.getId() == R.id.StateRadioSpinner){
            String SRC = parent.getItemAtPosition(position).toString();
            StateRadio.setText(SRC);
        }

        else if (spinner.getId() == R.id.PrivateRadioSpinner){
            String PRD = parent.getItemAtPosition(position).toString();
            PrivateRadio.setText(PRD);
        }

        else if (spinner.getId() == R.id.SateliteSpinner){
            String STL = parent.getItemAtPosition(position).toString();
            SateliteTelevision.setText(STL);
        }

        else if (spinner.getId() == R.id.NTAServiceSpinner){
            String NTA = parent.getItemAtPosition(position).toString();
            NTAService.setText(NTA);
        }

        else if (spinner.getId() == R.id.StateTelevisionSpinner){
            String STV = parent.getItemAtPosition(position).toString();
            StateTelevisionStation.setText(STV);
        }

        else if (spinner.getId() == R.id.PrivateTelevisionSpinner){
            String PTV = parent.getItemAtPosition(position).toString();
            PrivateTelevisionService.setText(PTV);
        }

        else if (spinner.getId() == R.id.SocialMediaSpinner){
            String SOC = parent.getItemAtPosition(position).toString();
            SocialMedia.setText(SOC);
        }

        else if (spinner.getId() == R.id.NewspaperSpinner){
            String OPT = parent.getItemAtPosition(position).toString();
            PrintMedia.setText(OPT);
        }

        else if (spinner.getId() == R.id.FireSpinner){
            String FSV = parent.getItemAtPosition(position).toString();
            FireService.setText(FSV);
        }

        else if (spinner.getId() == R.id.ArmySpinner){
            String ARM = parent.getItemAtPosition(position).toString();
            ArmyFormationAndCommand.setText(ARM);
        }

        else if (spinner.getId() == R.id.ArmyBarracksSpinner){
            String BRK = parent.getItemAtPosition(position).toString();
            ArmyBarracks.setText(BRK);
        }

        else if (spinner.getId() == R.id.PoliceSpinner){
            String PLO = parent.getItemAtPosition(position).toString();
            PoliceStation.setText(PLO);
        }

        else if (spinner.getId() == R.id.PoliceBarracksSpinner){
            String PBR = parent.getItemAtPosition(position).toString();
            PoliceBarracks.setText(PBR);
        }

        else if (spinner.getId() == R.id.DeptSpinner){
            String DPT = parent.getItemAtPosition(position).toString();
            DeptStateServices.setText(DPT);
        }

        else if (spinner.getId() == R.id.CivilSpinner){
            String CVL = parent.getItemAtPosition(position).toString();
            CivilDefence.setText(CVL);
        }

        else if (spinner.getId() == R.id.ImmigrationSpinner){
            String IMG = parent.getItemAtPosition(position).toString();
            ImmigrationService.setText(IMG);
        }

        else if (spinner.getId() == R.id.CustomsSpinner){
            String CST = parent.getItemAtPosition(position).toString();
            CustomService.setText(CST);
        }

        else if (spinner.getId() == R.id.PrisonSpinner){
            String PRS = parent.getItemAtPosition(position).toString();
            PrisonService.setText(PRS);
        }

        else if (spinner.getId() == R.id.FederalHighCourtSpinner){
            String FHC = parent.getItemAtPosition(position).toString();
            FederalHighCourt.setText(FHC);
        }

        else if (spinner.getId() == R.id.StateHighCourtSpinner){
            String SHC = parent.getItemAtPosition(position).toString();
            StateHighCourt.setText(SHC);
        }

        else if (spinner.getId() == R.id.MagistrateCourtSpinner){
            String MGS = parent.getItemAtPosition(position).toString();
            MagistrateCourt.setText(MGS);
        }

        else if (spinner.getId() == R.id.CustomaryCourtSpinner){
            String CTS = parent.getItemAtPosition(position).toString();
            CustomaryCourt.setText(CTS);
        }

        else if (spinner.getId() == R.id.cBankSpinner){
            String BNK = parent.getItemAtPosition(position).toString();
            Banks.setText(BNK);
        }

        else if (spinner.getId() == R.id.cBankConditionSpinner){
            String BNKc = parent.getItemAtPosition(position).toString();
            BanksCondition.setText(BNKc);
        }

        else if (spinner.getId() == R.id.DailyMarketSpinner){
            String MRKd = parent.getItemAtPosition(position).toString();
            DailyMarket.setText(MRKd);
        }

        else if (spinner.getId() == R.id.WeeklyMarketSpinner){
            String WKM = parent.getItemAtPosition(position).toString();
            WeeklyMarket.setText(WKM);
        }

        else if (spinner.getId() == R.id.LivestockMarketSpinner){
            String LVS = parent.getItemAtPosition(position).toString();
            LivestockMarket.setText(LVS);
        }

        else if (spinner.getId() == R.id.MotorParkSpinner){
            String OPT = parent.getItemAtPosition(position).toString();
            MotoParkText.setText(OPT);
        }

        else if (spinner.getId() == R.id.SuperMarketsSpinner){
            String SPM = parent.getItemAtPosition(position).toString();
            SuperMarkets.setText(SPM);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
