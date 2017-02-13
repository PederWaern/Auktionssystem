package com.surperfluousfew.auktionsystem;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class StageHandler {
    public Stage getParentStage(Parent parent){
        return (Stage) parent.getScene().getWindow();
    }
}
