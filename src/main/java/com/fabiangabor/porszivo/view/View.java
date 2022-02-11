package com.fabiangabor.porszivo.view;

import com.fabiangabor.porszivo.data.Datastore;

public interface View {
    public void println(Object string);

    public void printDirection(Datastore datastore);

    public void printCommandHistory(Datastore datastore);
}
