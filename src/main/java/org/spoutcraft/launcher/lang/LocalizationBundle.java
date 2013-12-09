/*
 * This file is part of Technic Launcher.
 * Copyright (C) 2013 Syndicate, LLC
 *
 * Technic Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Technic Launcher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Technic Launcher.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.spoutcraft.launcher.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationBundle {
	private ResourceBundle resourceBundle;
	private String resourceName;

	public static final Locale[] SUPPORTED_LOCALES = { Locale.GERMAN, Locale.ENGLISH, new Locale("no"), new Locale("pt") };
	public static final String DEFAULT_LOCALE = "default";

	public LocalizationBundle(String resourceName, String localeCode) {
		this.resourceName = resourceName;

		this.setLocale(this.getLocaleFromCode(localeCode));
	}

	public String getString(String stringKey, String... replacements) {
		String outString = resourceBundle.getString(stringKey);

		for (int i = 0; i < replacements.length; i++) {
			String find = String.format("{%d}", i);
			String replace = replacements[i];

			if (outString.contains(find)) {
				outString = outString.replace(find, replace);
			}
		}

		return outString;
	}

	public void setLocale(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(resourceName, locale);
	}

	public String getCodeFromLocale(Locale locale) {
		if (locale.getLanguage().isEmpty()) {
			return "default";
		} else if (locale.getCountry().isEmpty()) {
			return locale.getLanguage();
		} else if (locale.getVariant().isEmpty()) {
			return String.format("%s,%s",locale.getLanguage(),locale.getCountry());
		} else {
			return String.format("%s,%s,%s", locale.getLanguage(), locale.getCountry(), locale.getVariant());
		}
	}

	public Locale getLocaleFromCode(String localeCode) {
		if (localeCode == null || localeCode.isEmpty() || localeCode.equals(DEFAULT_LOCALE)) {
			return Locale.getDefault();
		}

		String[] results = localeCode.split(",");
		String language = "";
		String country = "";
		String variant = "";

		if (results.length > 0) {
			language = results[0];
		}

		if (results.length > 1) {
			country = results[1];
		}

		if (results.length > 2) {
			variant = results[2];
		}

		Locale definiteLocale = new Locale(language,country,variant);

		return matchClosestSupportedLocale(definiteLocale);
	}

	private Locale matchClosestSupportedLocale(Locale definiteLocale) {
		Locale bestSupportedLocale = null;
		int bestLocaleScore = 0;
		for (int i = 0; i < SUPPORTED_LOCALES.length; i++) {
			Locale testLocale = SUPPORTED_LOCALES[i];
			int testScore = 0;

			if (testLocale.getLanguage().equals(definiteLocale.getLanguage())) {
				testScore++;

				if (testLocale.getCountry().equals(definiteLocale.getCountry())) {
					testScore++;

					if (testLocale.getVariant().equals(definiteLocale.getVariant())) {
						testScore++;
					}
				}
			}

			if (testScore != 0 && testScore > bestLocaleScore) {
				bestLocaleScore = testScore;
				bestSupportedLocale = testLocale;
			}
		}

		if (bestSupportedLocale != null) {
			return bestSupportedLocale;
		} else {
			return Locale.getDefault();
		}
	}
}
