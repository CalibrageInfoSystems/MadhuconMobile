package com.trst01.locationtracker.di.module;

import com.trst01.locationtracker.MainActivity;
import com.trst01.locationtracker.activity.ChooseFarmerPlotActivity;
import com.trst01.locationtracker.activity.DashBoardActivity;
import com.trst01.locationtracker.activity.LoginActivity;
import com.trst01.locationtracker.activity.SettingsActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.activity.complains.ComplainListDetailsActivity;
import com.trst01.locationtracker.activity.complains.ComplainListsActivity;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerActivity;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.activity.farmerPlot.plot.ViewFarmerPlotListActivity;
import com.trst01.locationtracker.activity.farmerPlot.plot.ViewPlotActivity;
import com.trst01.locationtracker.activity.growthMonitoring.AgreementedDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.BioFertilizerActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ChooseGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.DiseaseActivity;
import com.trst01.locationtracker.activity.growthMonitoring.MeasuredDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.NetAreaDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.PestsActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ReportedDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.WeedicideActivity;
import com.trst01.locationtracker.activity.plantation.AddPlantation;
import com.trst01.locationtracker.activity.plantation.AgreementedAreaActivity;
import com.trst01.locationtracker.activity.plantation.ChoosePlantationActivity;
import com.trst01.locationtracker.activity.plantation.GuarantorListActivity;
import com.trst01.locationtracker.activity.plantation.PlotOfferActivity;
import com.trst01.locationtracker.activity.plantation.ReportedAreaActivity;
import com.trst01.locationtracker.activity.plantation.ViewFarmerListPlantationActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotListActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotOfferListActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotOfferReportedAreaListActivity;
import com.trst01.locationtracker.services.areacalculator.FieldCalculatorActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DashBoardActivity contributeDashBoardActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewFarmerActivity contributeViewFarmerActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewFarmerListActivity contributeViewFarmerListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewPlotActivity contributeViewPlotActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewFarmerPlotListActivity contributeViewFarmerPlotListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AddPlantation contributeAddPlantationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ChooseFarmerPlotActivity contributeChooseFarmerPlotActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ChoosePlantationActivity contributeChoosePlantationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract SettingsActivity contributeSettingsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PlotOfferActivity contributePlantOfferActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract FieldCalculatorActivity contributeFieldCalculatorActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewStatusPlotListActivity contributeViewStatusPlotListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewStatusPlotOfferListActivity contributeViewStatusPlotOfferListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ReportedAreaActivity contributeReportedAreaActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AgreementedAreaActivity contributeAgreementedAreaActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewStatusPlotOfferReportedAreaListActivity ViewStatusPlotOfferReportedAreaListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ViewFarmerListPlantationActivity ViewFarmerListPlantationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract WeedicideActivity ViewWeedicideActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DiseaseActivity ViewDiseaseActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract BioFertilizerActivity ViewBioFertilizerActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PestsActivity ViewPestsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ChooseGrowthMonitoringActivity ViewChooseGrowthMonitoringActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ReportedDetailsGrowthMonitoringActivity ViewReportedDetailsGrowthMonitoringActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ComplainListDetailsActivity ComplainListDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ComplainFormActivity ComplainFormActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ComplainListsActivity ComplainListsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GuarantorListActivity GuarantorListActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MeasuredDetailsGrowthMonitoringActivity MeasuredDetailsGrowthMonitoringActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract NetAreaDetailsGrowthMonitoringActivity NetAreaDetailsGrowthMonitoringActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AgreementedDetailsGrowthMonitoringActivity AgreementedDetailsGrowthMonitoringActivity();

}
