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
package com.android.tools.idea.uibuilder.scene.draw;

import com.android.tools.idea.uibuilder.scene.SceneContext;

import java.awt.*;

/**
 * Paint interface for draw commands
 * This interface also implies a constructor that takes a String
 * Which can expand the serialization of the of the command
 */
public interface DrawCommand extends Comparable {
  public final static int COMPONENT_LEVEL = 20;
  public final static int TARGET_LEVEL = 30;
  public final static int CONNECTION_LEVEL = 10 ;
  public final static int TOP_LEVEL = 50;
  public final static int CLIP_LEVEL =  0;
  public final static int UNCLIP_LEVEL =  1000;
  public final static int POST_CLIP_LEVEL =  1010;
  int getLevel(); // things are drawn 0 first
  void paint(Graphics2D g, SceneContext sceneContext);
  String serialize();
}
