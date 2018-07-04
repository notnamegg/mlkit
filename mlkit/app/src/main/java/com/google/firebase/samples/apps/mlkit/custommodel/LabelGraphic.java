// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.firebase.samples.apps.mlkit.custommodel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;


import com.google.firebase.samples.apps.mlkit.GraphicOverlay;
import com.google.firebase.samples.apps.mlkit.GraphicOverlay.Graphic;

import java.util.List;

/** Graphic instance for rendering image labels. */
public class LabelGraphic extends Graphic {

  private final Paint textPaint;
  private final GraphicOverlay overlay;
  private float fps=0.0f;
  //private long lastTime = SystemClock.uptimeMillis();
  private List<String> labels;

  LabelGraphic(GraphicOverlay overlay,float fps) {
    super(overlay);
    this.overlay = overlay;
    textPaint = new Paint();
    textPaint.setColor(Color.WHITE);
    textPaint.setTextSize(60.0f);
    this.fps=fps;

  }

  synchronized public void setFps(float fps)
  {
    this.fps=fps;
    //postInvalidate();
  }
  synchronized void updateLabel(List<String> labels) {
    this.labels = labels;
    //long updatetime = SystemClock.uptimeMillis();
    //fps  = 1000.0f/(updatetime-lastTime);
    //lastTime=updatetime;
    postInvalidate();
  }

  @Override
  public synchronized void draw(Canvas canvas) {
    float x = overlay.getWidth() / 4.0f;
    float y = overlay.getHeight() / 4.0f;

    for (String label : labels) {
      canvas.drawText(label, x, y, textPaint);
      y = y - 62.0f;
    }
    canvas.drawText("fps:"+fps, x, y, textPaint);
  }
}
