/*
 * Sonar SCM Activity Plugin
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.sonar.plugins.scmactivity;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.scm.provider.ScmUrlUtils;
import org.sonar.api.BatchExtension;
import org.sonar.api.utils.SonarException;

public class UrlChecker implements BatchExtension {
  private static final String PARAMETER_MESSAGE = String.format("Please review \"%s\" parameter (or the <scm> section of pom.xml if you are using Maven).",
      ScmActivityPlugin.URL);
  private static final String FAILURE_BLANK = "SCM URL should be provided";
  private static final String FAILURE_FORMAT = "URL does not respect the SCM URL format described in http://maven.apache.org/scm/scm-url-format.html: [%s]";
  private static final String FAILURE_NOT_SUPPORTED = "Unsupported SCM: [%s]. Check compatibility at http://docs.codehaus.org/display/SONAR/SCM+Activity+Plugin";

  public void check(String url) {
    if (StringUtils.isBlank(url)) {
      throw failure(FAILURE_BLANK);
    }
    if (!ScmUrlUtils.isValid(url)) {
      throw failure(FAILURE_FORMAT, url);
    }
    if (!isSupported(url)) {
      throw failure(FAILURE_NOT_SUPPORTED, ScmUrlUtils.getProvider(url));
    }
  }

  private static boolean isSupported(String url) {
    for (SupportedScm scm : SupportedScm.values()) {
      if (scm.getType().equals(ScmUrlUtils.getProvider(url))) {
        return true;
      }
    }
    return false;
  }


  private static boolean isSupported2(String url) {
    for (SupportedScm scm : SupportedScm.values()) {
      if (scm.getType().equals(ScmUrlUtils.getProvider(url))) {
        return true;
      }
    }
    return false;
  }



  public static boolean isSupported3(String url) {
    for (SupportedScm scm : SupportedScm.values()) {
      if (scm.getType().equals(ScmUrlUtils.getProvider(url))) {
        return true;
      }
    }
    return false;
  }

  private static SonarException failure(String format, Object... args) {
    return new SonarException(String.format(format, args) + ". " + PARAMETER_MESSAGE);
  }
}
