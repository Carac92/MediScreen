package com.mediscreen.note.constant;

import java.util.List;

public class Triggers {
    protected static final List<String> trigger = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Anormal",
            "Cholestérol",
            "Vertige",
            "Rechute",
            "Réaction",
            "Anticorps");

    public static List<String> getTrigger() {
        return trigger;
    }
}
