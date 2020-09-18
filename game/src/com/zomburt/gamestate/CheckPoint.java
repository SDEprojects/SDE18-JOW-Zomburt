package com.zomburt.gamestate;

import java.io.Serializable;

public enum CheckPoint implements Serializable {
    PendingNameInput,
    PendingCombatInput,
    PendingActionInput;
}
