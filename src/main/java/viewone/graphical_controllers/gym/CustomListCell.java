package viewone.graphical_controllers.gym;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CustomListCell extends ListCell<String> {
    private final HBox cellContent;
    private final Button cellButton;

    public CustomListCell() {
        cellButton = new Button("Click Me"); // Create a button
        cellContent = new HBox(cellButton); // Place the button inside an HBox
        cellButton.setOnAction(event -> {
            // Handle button click event here
            System.out.println("Button clicked in cell: " + getItem());
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            // Set the cell content
            cellButton.setText("Click " + item);
            setGraphic(cellContent);
        } else {
            setGraphic(null);
        }
    }
}
