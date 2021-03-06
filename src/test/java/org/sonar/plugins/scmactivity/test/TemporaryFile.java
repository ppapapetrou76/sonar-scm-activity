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

package org.sonar.plugins.scmactivity.test;

import org.apache.commons.io.FileUtils;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class TemporaryFile extends ExternalResource {
  private final TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Override
  protected void before() throws Throwable {
    temporaryFolder.create();
  }

  @Override
  protected void after() {
    temporaryFolder.delete();
  }

  public File create(String name, String content) throws IOException {
    File file = temporaryFolder.newFile(name);
    FileUtils.write(file, content, "UTF-8");
    return file;
  }
}
