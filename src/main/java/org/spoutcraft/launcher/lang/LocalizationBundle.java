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

	public LocalizationBundle(String resourceName, Locale locale) {
		resourceBundle = ResourceBundle.getBundle(resourceName, locale);
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
}
