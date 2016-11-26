package com.bigstark.zangsisi.app.base;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {
  private static final Bus BUS = new Bus(ThreadEnforcer.MAIN);

  public static Bus getInstance() {
    return BUS;
  }

  public static void post(final Object event) {
    BUS.post(event);
  }

  public BusProvider() {
  }
}