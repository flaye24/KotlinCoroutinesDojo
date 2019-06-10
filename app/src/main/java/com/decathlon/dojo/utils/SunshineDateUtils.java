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
package com.decathlon.dojo.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

/**
 * Class for handling date conversions that are useful for Sunshine.
 */
public final class SunshineDateUtils {



    /**
     * Helper method to convert the date into something to display
     * to users.
     * <p/>
     * The day string for forecast uses the following logic:
     * For today: "Today, June 8"
     *
     * @param time               time in seconds
     *
     * @return A user-friendly representation of the date such as "Today, June 8", "Tomorrow",
     * or "Friday"
     */
    public static String getFriendlyDateString(long time) {

        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("EEEE, MMMM dd", cal).toString();
    }


}