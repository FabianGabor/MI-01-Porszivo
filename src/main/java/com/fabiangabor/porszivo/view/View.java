package com.fabiangabor.porszivo.view;

import com.fabiangabor.porszivo.data.Datastore;

public interface View {
    void println(Object string);

    void printDirection(Datastore datastore);

    void printCommandHistory(Datastore datastore);
}
