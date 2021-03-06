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
package com.android.tools.idea.testing;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.pico.DefaultPicoContainer;
import org.jetbrains.annotations.NotNull;
import org.picocontainer.ComponentAdapter;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class IdeComponents {
  private IdeComponents() {
  }

  public static <T> T replaceServiceWithMock(@NotNull Class<T> serviceType) {
    T mock = mock(serviceType);
    replaceService(serviceType, mock);
    return mock;
  }

  public static <T> void replaceService(@NotNull Class<T> serviceType, @NotNull T newServiceInstance) {
    doReplaceService(ApplicationManager.getApplication(), serviceType, newServiceInstance);
  }

  @NotNull
  public static <T> T replaceServiceWithMock(@NotNull Project project, @NotNull Class<T> serviceType) {
    T mock = mock(serviceType);
    replaceService(project, serviceType, mock);
    return mock;
  }

  public static <T> void replaceService(@NotNull Project project, @NotNull Class<T> serviceType, @NotNull T newServiceInstance) {
    doReplaceService(project, serviceType, newServiceInstance);
  }

  private static <T> void doReplaceService(@NotNull ComponentManager componentManager,
                                           @NotNull Class<T> serviceType,
                                           @NotNull T newServiceInstance) {
    DefaultPicoContainer picoContainer = (DefaultPicoContainer)componentManager.getPicoContainer();

    String componentKey = serviceType.getName();
    ComponentAdapter componentAdapter = picoContainer.unregisterComponent(componentKey);
    assert componentAdapter != null;

    picoContainer.registerComponentInstance(componentKey, newServiceInstance);
    assertSame(newServiceInstance, picoContainer.getComponentInstance(componentKey));
  }
}
