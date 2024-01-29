package viewone;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseStatusButtonController {

    private Button previousButton;
    private final List<Button> buttonList = new ArrayList<>();
    private final List<String> buttonNameList = Arrays.asList(
            "Active",
            "Suspended"
    );

    private void colorShift(Button button) {
        if (previousButton != null) {
            previousButton.setStyle(null);
        }
        button.setStyle("-fx-background-color: linear-gradient(to bottom, #00e4af, #5d7cf3);" +
                "-fx-background-radius:  5em;" + "-fx-border-color:  #2D043D;" +
                "-fx-border-radius:  5em;" + "-fx-text-fill: #2D043D;");
        previousButton = button;
    }

    public Button statusButtonAction(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        colorShift(sourceButton);

        // Store the button in the list if it's not already present
        if (!buttonList.contains(sourceButton)) {
            buttonList.add(sourceButton);
        }

        return sourceButton;
    }
}
