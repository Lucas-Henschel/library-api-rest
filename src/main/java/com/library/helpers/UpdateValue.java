package com.library.helpers;

import java.util.function.Consumer;

public class UpdateValue {
    public static void updateIfNotNull(Consumer<String> setter, String newValue) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
