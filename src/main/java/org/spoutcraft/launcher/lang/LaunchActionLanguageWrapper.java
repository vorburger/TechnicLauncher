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

import net.technicpack.launchercore.util.LaunchAction;

public class LaunchActionLanguageWrapper {
	private LaunchAction action;
	private LocalizationBundle uiTextLocalization;

	public LaunchActionLanguageWrapper(LaunchAction action, LocalizationBundle uiText) {
		this.action = action;
		this.uiTextLocalization = uiText;
	}

	public LaunchAction getAction() { return action; }
	public String toString() {
		switch (action) {
			case HIDE:
				return this.uiTextLocalization.getString("launcheroptions.dropdown.hidelauncher");
			case CLOSE:
				return this.uiTextLocalization.getString("launcheroptions.dropdown.closelauncher");
			default:
				return this.uiTextLocalization.getString("launcheroptions.dropdown.stayopen");
		}
	}
}
