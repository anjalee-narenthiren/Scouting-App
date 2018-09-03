package com.example.android.scouting;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.preference.CheckBoxPreference;
//import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
//import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.scouting.R;

import com.takisoft.fix.support.v7.preference.EditTextPreference;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs_visualizer);
        initializeSummary("user_name");
        initializeSummary("team_num");
        initializeSummary("team_filter");
        initializeSummary("tournament_filter");
        /*
        EditTextPreference teamFilter = (EditTextPreference) findPreference("team_filter");
        teamFilter.getEditText();
        if (matchFilter != null) {
            matchFilter.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        }*/

    }

    @Override
    public void onStart() {
        super.onStart();
        //Connect onSharedPreferenceChangeListener to update summary
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPrefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference textPref = (EditTextPreference) pref;
            textPref.setSummary(textPref.getText());

            /*if (key == "team_filter" || key == "tournament_filter")
                if(textPref.getText().trim().)*/
        }
    }
    private void initializeSummary(String key){
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference textPref = (EditTextPreference) pref;
            String curSummary = (String) textPref.getSummary();
            if (curSummary==null || curSummary.isEmpty())
                textPref.setSummary(textPref.getText());
        }
    }
}