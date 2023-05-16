package com.mediscreen.note.constant;

import java.util.List;
/**
 * This is the class for the triggers.
 * It is used to store the triggers.
 */
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
