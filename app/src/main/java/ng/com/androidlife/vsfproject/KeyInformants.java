package ng.com.androidlife.vsfproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

public class KeyInformants extends AppCompatActivity {

    TextInputEditText State, LocalGov, TownVillage, Longitude, Latitude, Status, Population, COMMUNITYSizeAdultMaleBefore, COMMUNITYSizeAdultFemaleBefore,
            COMMUNITYSizeChildrenMaleBefore, COMMUNITYSizeChildrenFemaleBefore, COMMUNITYSizeAdultMaleAfter, COMMUNITYSizeAdultFemaleAfter, COMMUNITYSizeChildrenMaleAfter,
            COMMUNITYSizeChildrenFemaleAfter, COMMUNITYMemberConditionElderlyBefore, COMMUNITYMemberConditionDisabledBefore, COMMUNITYMemberConditionPregnantBefore,
            COMMUNITYMemberConditionLactatingBefore, COMMUNITYMemberConditionInfantBefore, COMMUNITYMemberConditionChildrenBefore, COMMUNITYMemberConditionElderlyAfter,
            COMMUNITYMemberConditionDisabledAfter, COMMUNITYMemberConditionPregnantAfter, COMMUNITYMemberConditionLactatingAfter, COMMUNITYMemberConditionInfantAfter,
            COMMUNITYMemberConditionChildrenAfter, AttackedDeath, AttackedMissing, AttacksNumber, COMMUNITYMemberAdultMaleLostAfter, COMMUNITYMemberAdultFemaleLostAfter,
            COMMUNITYMemberChildrenLostAfter, KeyMemberQualificationBefore, KeyMemberQualificationAfter, KeyMemberOccupationBefore, KeyMemberOccupationAfter, KeyMemberOtherOccupationBefore,
            KeyMemberOtherOccupationAfter, LGASecretariat, EmirPalace, DHsPalace, VillageHeadPalace, TownHall, PlayGroundCenter, Primary, Secondary, Technical,
            Commercial, Tertiary, OtherSchool, PrimaryClassRoom, SecondaryClassRoom, TechnicalClassRoom, CommercialClassRoom, TertiaryClassRoom, IslamiyaClassRoom,
            OtherSchoolClassRoom, PrimaryWorkshop, SecondaryWorkshop, TechnicalWorkshop, CommercialWorkshop, TertiaryWorkshop, IslamiyaWorkshop, OtherSchoolWorkshop,
            ToiletFacilities, StaffHousing, Fencing, StaffPrimary, StaffSecondary, StaffTechnical, StaffCommercial, StaffTertiary, StaffIslamiya, StaffOther,
            NonStaffPrimary, NonStaffSecondary, NonStaffTechnical, NonStaffCommercial, NonStaffTertiary, NonStaffIslamiya, NonStaffOther, SportingPrimary, SportingSecondary,
            SportingTechnical, SportingCommercial, SportingTertiary, SportingIslamiya, SportingOther, OtherStaffHousing, StudentHostel, OtherToiletFacilities,
            PerimeterFence, OwnerShipPrimary, OwnerShipSecondary, OwnerShipTechnical, OwnerShipCommercial, OwnerShipTertiary, OwnerShipIslamiya, OwnerShipOther,
            HealthPost, HealthCenter, PrimaryHealthCenter, PrivateHospital, GeneralHospital, FMCSpecialistHospital, OtherHospitals, OperationTheater, Laboratories,
            Mortuary, Ambulances, Maternity, NutritionalServices, Pharmaceuticals, StaffAccommodation, ElectricitySupply, ColdStore, Doctors, Nurses, Midwives, NursesMidwives,
            Pharmacists, Radiographers, PharmacyTechnicians, MedicalLabScientist, PharmacyAssistant, LaboratoryTechnicians, LaboratoryAssistant, HealthSocialWorkers,
            Nutritionist, PublicHealthOfficers, CommunityHealthOfficers, CommunityHealth, ExtensionOfficers, EnvironmentalHealthOfficers, PsychSocial, TraditionalMidwives,
            SourceWater, Condition, ElectricitySource, ElectricityCondition, Roads, RoadsCondition, Railway, RailwayConditions, RailwayFlowSchedule, Airport, AirportConditions,
            SeaTransport, SeaConditions, Communication, InternationalRadio, FederalRadio, StateRadio, PrivateRadio, SateliteTelevision, NTAService, StateTelevisionStation,
            PrivateTelevisionService, SocialMedia, PrintMedia, FireService, ArmyFormationAndCommand, ArmyBarracks, PoliceStation, PoliceBarracks, DeptStateServices, CivilDefence,
            ImmigrationService, CustomService, PrisonService, Vigelante, OtherSecurity, FederalHighCourt, ShariaCourtOfAppeal, IndustrialCourt, StateHighCourt, MagistrateCourt, CustomaryCourt,
            OtherCourt, Banks, BanksCondition, InsuranceCompanies, Factories, DailyMarket, WeeklyMarket, LivestockMarket, SuperMarkets, OtherMarket, Hotels, Tourist, Mosques,
            Church, Shrine, NGOsCBOs;

    EditText ageCOMMUNITYBefore, ageCOMMUNITYAfter, CommunityInformantAge;

    TextView genderBefore, genderAfter, genderInformant;

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

        //General

        State = findViewById(R.id.State);
        LocalGov = findViewById(R.id.LocalGov);
        TownVillage = findViewById(R.id.TownVillage);
        Longitude = findViewById(R.id.Longitude);
        Latitude = findViewById(R.id.Latitude);
        Status = findViewById(R.id.Status);
        Population = findViewById(R.id.Population);

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
        KeyMemberQualificationBefore = findViewById(R.id.KeyMemberQualificationBefore);
        KeyMemberQualificationAfter = findViewById(R.id.KeyMemberQualificationAfter);
        KeyMemberOccupationBefore = findViewById(R.id.KeyMemberOccupationBefore);
        KeyMemberOccupationAfter = findViewById(R.id.KeyMemberOccupationAfter);
        KeyMemberOtherOccupationBefore = findViewById(R.id.KeyMemberOtherOccupationBefore);
        KeyMemberOtherOccupationAfter = findViewById(R.id.KeyMemberOtherOccupationAfter);
        LGASecretariat = findViewById(R.id.LGASecretariat);
        EmirPalace = findViewById(R.id.EmirPalace);
        DHsPalace = findViewById(R.id.DHsPalace);
        VillageHeadPalace = findViewById(R.id.VillageHeadPalace);
        TownHall = findViewById(R.id.TownHall);
        PlayGroundCenter = findViewById(R.id.PlayGroundCenter);
        Primary = findViewById(R.id.Primary);
        Secondary = findViewById(R.id.Secondary);
        Technical = findViewById(R.id.Technical);
        Commercial =  findViewById(R.id.Commercial);
        Tertiary = findViewById(R.id.Tertiary);
        OtherSchool = findViewById(R.id.OtherSchool);
        PrimaryClassRoom = findViewById(R.id.PrimaryClassRoom);
        SecondaryClassRoom = findViewById(R.id.SecondaryClassRoom);
        TechnicalClassRoom = findViewById(R.id.TechnicalClassRoom);
        CommercialClassRoom = findViewById(R.id.CommercialClassRoom);
        TertiaryClassRoom = findViewById(R.id.TertiaryClassRoom);
        IslamiyaClassRoom = findViewById(R.id.IslamiyaClassRoom);
        OtherSchoolClassRoom = findViewById(R.id.OtherSchoolClassRoom);
        PrimaryWorkshop = findViewById(R.id.PrimaryWorkshop);
        SecondaryWorkshop = findViewById(R.id.SecondaryWorkshop);
        TechnicalWorkshop =  findViewById(R.id.TechnicalWorkshop);
        CommercialWorkshop = findViewById(R.id.CommercialWorkshop);
        TertiaryWorkshop = findViewById(R.id.TertiaryWorkshop);
        IslamiyaWorkshop = findViewById(R.id.IslamiyaWorkshop);
        OtherSchoolWorkshop = findViewById(R.id.OtherSchoolWorkshop);
        ToiletFacilities = findViewById(R.id.ToiletFacilities);
        StaffHousing = findViewById(R.id.StaffHousing);
        Fencing = findViewById(R.id.Fencing);
        StaffPrimary = findViewById(R.id.StaffPrimary);
        StaffSecondary = findViewById(R.id.StaffSecondary);
        StaffTechnical = findViewById(R.id.StaffTechnical);
        StaffCommercial = findViewById(R.id.StaffCommercial);
        StaffTertiary = findViewById(R.id.StaffTertiary);
        StaffIslamiya = findViewById(R.id.StaffIslamiya);
        StaffOther = findViewById(R.id.StaffOther);
        NonStaffPrimary = findViewById(R.id.NonStaffPrimary);
        NonStaffSecondary = findViewById(R.id.NonStaffSecondary);
        NonStaffTechnical = findViewById(R.id.NonStaffTechnical);
        NonStaffCommercial = findViewById(R.id.NonStaffCommercial);
        NonStaffTertiary = findViewById(R.id.NonStaffTertiary);
        NonStaffIslamiya = findViewById(R.id.NonStaffIslamiya);
        NonStaffOther = findViewById(R.id.NonStaffOther);
        SportingPrimary = findViewById(R.id.SportingPrimary);
        SportingSecondary = findViewById(R.id.SportingSecondary);
        SportingTechnical = findViewById(R.id.SportingTechnical);
        SportingCommercial = findViewById(R.id.SportingCommercial);
        SportingTertiary = findViewById(R.id.SportingTertiary);
        SportingIslamiya = findViewById(R.id.SportingIslamiya);
        SportingOther = findViewById(R.id.SportingOther);
        OtherStaffHousing = findViewById(R.id.OtherStaffHousing);
        StudentHostel = findViewById(R.id.StudentHostel);
        OtherToiletFacilities = findViewById(R.id.OtherToiletFacilities);
        PerimeterFence = findViewById(R.id.PerimeterFence);
        OwnerShipPrimary = findViewById(R.id.OwnerShipPrimary);
        OwnerShipSecondary = findViewById(R.id.OwnerShipSecondary);
        OwnerShipTechnical = findViewById(R.id.OwnerShipTechnical);
        OwnerShipCommercial = findViewById(R.id.OwnerShipCommercial);
        OwnerShipTertiary = findViewById(R.id.OwnerShipTertiary);
        OwnerShipIslamiya = findViewById(R.id.OwnerShipIslamiya);
        OwnerShipOther = findViewById(R.id.OwnerShipOther);
        HealthPost = findViewById(R.id.HealthPost);
        HealthCenter = findViewById(R.id.HealthCenter);
        PrimaryHealthCenter = findViewById(R.id.PrimaryHealthCenter);
        PrivateHospital = findViewById(R.id.PrivateHospital);
        GeneralHospital = findViewById(R.id.GeneralHospital);
        FMCSpecialistHospital = findViewById(R.id.FMCSpecialistHospital);
        OtherHospitals = findViewById(R.id.OtherHospitals);
        OperationTheater = findViewById(R.id.OperationTheater);
        Laboratories = findViewById(R.id.Laboratories);
        Mortuary = findViewById(R.id.Mortuary);
        Ambulances = findViewById(R.id.Ambulances);
        Maternity = findViewById(R.id.Maternity);
        NutritionalServices = findViewById(R.id.NutritionalServices);
        Pharmaceuticals = findViewById(R.id.Pharmaceuticals);
        StaffAccommodation = findViewById(R.id.StaffAccommodation);
        ElectricitySupply = findViewById(R.id.ElectricitySupply);
        ColdStore = findViewById(R.id.ColdStore);
        Doctors = findViewById(R.id.Doctors);
        Nurses = findViewById(R.id.Nurses);
        Midwives = findViewById(R.id.Midwives);
        NursesMidwives = findViewById(R.id.NursesMidwives);
        Pharmacists = findViewById(R.id.Pharmacists);
        Radiographers = findViewById(R.id.Radiographers);
        PharmacyTechnicians = findViewById(R.id.PharmacyTechnicians);
        MedicalLabScientist = findViewById(R.id.MedicalLabScientist);
        PharmacyAssistant = findViewById(R.id.PharmacyAssistant);
        LaboratoryTechnicians = findViewById(R.id.LaboratoryTechnicians);
        LaboratoryAssistant = findViewById(R.id.LaboratoryAssistant);
        HealthSocialWorkers = findViewById(R.id.HealthSocialWorkers);
        Nutritionist = findViewById(R.id.Nutritionist);
        PublicHealthOfficers = findViewById(R.id.PublicHealthOfficers);
        CommunityHealthOfficers = findViewById(R.id.CommunityHealthOfficers);
        CommunityHealth = findViewById(R.id.CommunityHealth);
        ExtensionOfficers = findViewById(R.id.ExtensionOfficers);
        EnvironmentalHealthOfficers = findViewById(R.id.EnvironmentalHealthOfficers);
        PsychSocial = findViewById(R.id.PsychSocial);
        TraditionalMidwives = findViewById(R.id.TraditionalMidwives);
        SourceWater = findViewById(R.id.SourceWater);
        Condition = findViewById(R.id.Condition);
        ElectricitySource = findViewById(R.id.ElectricitySource);
        ElectricityCondition = findViewById(R.id.ElectricityCondition);
        Roads = findViewById(R.id.Roads);
        RoadsCondition = findViewById(R.id.RoadsCondition);
        Railway = findViewById(R.id.Railway);
        RailwayConditions = findViewById(R.id.RailwayConditions);
        RailwayFlowSchedule = findViewById(R.id.RailwayFlowSchedule);
        Airport = findViewById(R.id.Airport);
        AirportConditions = findViewById(R.id.AirportConditions);
        SeaTransport = findViewById(R.id.SeaTransport);
        SeaConditions = findViewById(R.id.SeaConditions);
        Communication = findViewById(R.id.Communication);
        InternationalRadio = findViewById(R.id.InternationalRadio);
        FederalRadio = findViewById(R.id.FederalRadio);
        StateRadio = findViewById(R.id.StateRadio);
        PrivateRadio = findViewById(R.id.PrivateRadio);
        SateliteTelevision = findViewById(R.id.SateliteTelevision);
        NTAService = findViewById(R.id.NTAService);
        StateTelevisionStation = findViewById(R.id.StateTelevisionStation);
        PrivateTelevisionService = findViewById(R.id.PrivateTelevisionService);
        SocialMedia = findViewById(R.id.SocialMedia);
        PrintMedia = findViewById(R.id.PrintMedia);
        FireService = findViewById(R.id.FireService);
        ArmyFormationAndCommand = findViewById(R.id.ArmyFormationAndCommand);
        ArmyBarracks = findViewById(R.id.ArmyBarracks);
        PoliceStation = findViewById(R.id.PoliceStation);
        PoliceBarracks = findViewById(R.id.PoliceBarracks);
        DeptStateServices = findViewById(R.id.DeptStateServices);
        CivilDefence = findViewById(R.id.CivilDefence);
        ImmigrationService = findViewById(R.id.ImmigrationService);
        CustomService = findViewById(R.id.CustomService);
        PrisonService = findViewById(R.id.PrisonService);
        Vigelante = findViewById(R.id.Vigelante);
        OtherSecurity = findViewById(R.id.OtherSecurity);
        FederalHighCourt = findViewById(R.id.FederalHighCourt);
        ShariaCourtOfAppeal = findViewById(R.id.ShariaCourtOfAppeal);
        IndustrialCourt = findViewById(R.id.IndustrialCourt);
        StateHighCourt = findViewById(R.id.StateHighCourt);
        MagistrateCourt = findViewById(R.id.MagistrateCourt);
        CustomaryCourt = findViewById(R.id.CustomaryCourt);
        OtherCourt = findViewById(R.id.OtherCourt);
        Banks = findViewById(R.id.Banks);
        BanksCondition = findViewById(R.id.BanksCondition);
        InsuranceCompanies = findViewById(R.id.InsuranceCompanies);
        Factories = findViewById(R.id.Factories);
        DailyMarket = findViewById(R.id.DailyMarket);
        WeeklyMarket = findViewById(R.id.WeeklyMarket);
        LivestockMarket = findViewById(R.id.LivestockMarket);
        SuperMarkets = findViewById(R.id.SuperMarkets);
        OtherMarket = findViewById(R.id.OtherMarket);
        Hotels = findViewById(R.id.Hotels);
        Tourist = findViewById(R.id.Tourist);
        Mosques = findViewById(R.id.Mosques);
        Church = findViewById(R.id.Church);
        Shrine = findViewById(R.id.Shrine);
        NGOsCBOs = findViewById(R.id.NGOsCBOs);
    }

    public void FocusSubmit(View view) {
        SubmitToDB();

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
        ToiletFacilities.setText("");
        StaffHousing.setText("");
        Fencing.setText("");
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

    }

    private void SubmitToDB() {

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
        final String Val39 = KeyMemberOtherOccupationAfter.getText().toString().trim();
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
        final String Val66 = ToiletFacilities.getText().toString().trim();
        final String Val67 = StaffHousing.getText().toString().trim();
        final String Val68 = Fencing.getText().toString().trim();
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
        final String Val127 = LaboratoryTechnicians.getText().toString().trim();
        final String Val128 = LaboratoryAssistant.getText().toString().trim();
        final String Val129 = HealthSocialWorkers.getText().toString().trim();
        final String Val130 = Nutritionist.getText().toString().trim();
        final String Val131 = PublicHealthOfficers.getText().toString().trim();
        final String Val132 = CommunityHealthOfficers.getText().toString().trim();
        final String Val133 = CommunityHealth.getText().toString().trim();
        final String Val134 = ExtensionOfficers.getText().toString().trim();
        final String Val135 = EnvironmentalHealthOfficers.getText().toString().trim();
        final String Val136 = PsychSocial.getText().toString().trim();
        final String Val137 = TraditionalMidwives.getText().toString().trim();
        final String Val138 = SourceWater.getText().toString().trim();
        final String Val139 = Condition.getText().toString().trim();
        final String Val140 = ElectricitySource.getText().toString().trim();
        final String Val141 = ElectricityCondition.getText().toString().trim();
        final String Val142 = Roads.getText().toString().trim();
        final String Val143 = RoadsCondition.getText().toString().trim();
        final String Val144 = Railway.getText().toString().trim();
        final String Val145 = RailwayConditions.getText().toString().trim();
        final String Val146 = RailwayFlowSchedule.getText().toString().trim();
        final String Val147 = Airport.getText().toString().trim();
        final String Val148 = AirportConditions.getText().toString().trim();
        final String Val149 = SeaTransport.getText().toString().trim();
        final String Val150 = SeaConditions.getText().toString().trim();
        final String Val151 = Communication.getText().toString().trim();
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
        final String Val172 = Vigelante.getText().toString().trim();
        final String Val173 = OtherSecurity.getText().toString().trim();
        final String Val174 = FederalHighCourt.getText().toString().trim();
        final String Val175 = ShariaCourtOfAppeal.getText().toString().trim();
        final String Val176 = IndustrialCourt.getText().toString().trim();
        final String Val177 = StateHighCourt.getText().toString().trim();
        final String Val178 = MagistrateCourt.getText().toString().trim();
        final String Val179 = CustomaryCourt.getText().toString().trim();
        final String Val180 = OtherCourt.getText().toString().trim();
        final String Val181 = Banks.getText().toString().trim();
        final String Val182 = BanksCondition.getText().toString().trim();
        final String Val183 = InsuranceCompanies.getText().toString().trim();
        final String Val184 = Factories.getText().toString().trim();
        final String Val185 = DailyMarket.getText().toString().trim();
        final String Val186 = WeeklyMarket.getText().toString().trim();
        final String Val187 = LivestockMarket.getText().toString().trim();
        final String Val188 = SuperMarkets.getText().toString().trim();
        final String Val189 = OtherMarket.getText().toString().trim();
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
                !TextUtils.isEmpty(Val39)&&
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
                !TextUtils.isEmpty(Val66)&&
                !TextUtils.isEmpty(Val67)&&
                !TextUtils.isEmpty(Val68)&&
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
                !TextUtils.isEmpty(Val127)&&
                !TextUtils.isEmpty(Val128)&&
                !TextUtils.isEmpty(Val129)&&
                !TextUtils.isEmpty(Val130)&&
                !TextUtils.isEmpty(Val131)&&
                !TextUtils.isEmpty(Val132)&&
                !TextUtils.isEmpty(Val133)&&
                !TextUtils.isEmpty(Val134)&&
                !TextUtils.isEmpty(Val135)&&
                !TextUtils.isEmpty(Val136)&&
                !TextUtils.isEmpty(Val137)&&
                !TextUtils.isEmpty(Val138)&&
                !TextUtils.isEmpty(Val139)&&
                !TextUtils.isEmpty(Val140)&&
                !TextUtils.isEmpty(Val141)&&
                !TextUtils.isEmpty(Val142)&&
                !TextUtils.isEmpty(Val143)&&
                !TextUtils.isEmpty(Val144)&&
                !TextUtils.isEmpty(Val145)&&
                !TextUtils.isEmpty(Val146)&&
                !TextUtils.isEmpty(Val147)&&
                !TextUtils.isEmpty(Val148)&&
                !TextUtils.isEmpty(Val149)&&
                !TextUtils.isEmpty(Val150)&&

                !TextUtils.isEmpty(Val151)&&
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
                !TextUtils.isEmpty(Val172)&&
                !TextUtils.isEmpty(Val173)&&
                !TextUtils.isEmpty(Val174)&&
                !TextUtils.isEmpty(Val175)&&
                !TextUtils.isEmpty(Val176)&&
                !TextUtils.isEmpty(Val177)&&
                !TextUtils.isEmpty(Val178)&&
                !TextUtils.isEmpty(Val179)&&
                !TextUtils.isEmpty(Val180)&&
                !TextUtils.isEmpty(Val181)&&
                !TextUtils.isEmpty(Val182)&&
                !TextUtils.isEmpty(Val183)&&
                !TextUtils.isEmpty(Val184)&&
                !TextUtils.isEmpty(Val185)&&
                !TextUtils.isEmpty(Val186)&&
                !TextUtils.isEmpty(Val187)&&
                !TextUtils.isEmpty(Val188)&&
                !TextUtils.isEmpty(Val189)&&
                !TextUtils.isEmpty(Val190)&&
                !TextUtils.isEmpty(Val191)&&
                !TextUtils.isEmpty(Val192)&&
                !TextUtils.isEmpty(Val193)&&
                !TextUtils.isEmpty(Val194)&&
                !TextUtils.isEmpty(Val195)&&
                !TextUtils.isEmpty(Val196)&&
                !TextUtils.isEmpty(Val197)&&
                !TextUtils.isEmpty(Val198)){
            final DatabaseReference newPost = mDatabase.push();
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
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
                    newPost.child("OtherOccupationAfter").setValue(Val39);
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
                    newPost.child("ToiletFacilities").setValue(Val66);
                    newPost.child("StaffHousing").setValue(Val67);
                    newPost.child("Fencing").setValue(Val68);
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
                    newPost.child("LaboratoryTechnicians").setValue(Val127);
                    newPost.child("LaboratoryAssistant").setValue(Val128);
                    newPost.child("HealthSocialWorkers").setValue(Val129);
                    newPost.child("Nutritionist").setValue(Val130);
                    newPost.child("PublicHealthOfficers").setValue(Val131);
                    newPost.child("CommunityHealthOfficers").setValue(Val132);
                    newPost.child("CommunityHealth").setValue(Val133);
                    newPost.child("ExtensionOfficers").setValue(Val134);
                    newPost.child("EnvironmentalHealthOfficers").setValue(Val135);
                    newPost.child("PsychSocial").setValue(Val136);
                    newPost.child("TraditionalMidwives").setValue(Val137);
                    newPost.child("SourceWater").setValue(Val138);
                    newPost.child("ConditionWater").setValue(Val139);
                    newPost.child("ElectricitySource").setValue(Val140);
                    newPost.child("ElectricityCondition").setValue(Val141);
                    newPost.child("Roads").setValue(Val142);
                    newPost.child("RoadsCondition").setValue(Val143);
                    newPost.child("Railway").setValue(Val144);
                    newPost.child("RailwayConditions").setValue(Val145);
                    newPost.child("RailwayFlowSchedule").setValue(Val146);
                    newPost.child("Airport").setValue(Val147);
                    newPost.child("AirportConditions").setValue(Val148);
                    newPost.child("SeaTransport").setValue(Val149);
                    newPost.child("SeaConditions").setValue(Val150);

                    newPost.child("Communication").setValue(Val151);
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
                    newPost.child("Vigelante").setValue(Val172);
                    newPost.child("OtherSecurity").setValue(Val173);
                    newPost.child("FederalHighCourt").setValue(Val174);
                    newPost.child("ShariaCourtOfAppeal").setValue(Val175);
                    newPost.child("IndustrialCourt").setValue(Val176);
                    newPost.child("StateHighCourt").setValue(Val177);
                    newPost.child("MagistrateCourt").setValue(Val178);
                    newPost.child("CustomaryCourt").setValue(Val179);
                    newPost.child("OtherCourt").setValue(Val180);
                    newPost.child("Banks").setValue(Val181);
                    newPost.child("BanksCondition").setValue(Val182);
                    newPost.child("InsuranceCompanies").setValue(Val183);
                    newPost.child("Factories").setValue(Val184);
                    newPost.child("DailyMarket").setValue(Val185);
                    newPost.child("WeeklyMarket").setValue(Val186);
                    newPost.child("LivestockMarket").setValue(Val187);
                    newPost.child("SuperMarkets").setValue(Val188);
                    newPost.child("OtherMarket").setValue(Val189);
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
}
