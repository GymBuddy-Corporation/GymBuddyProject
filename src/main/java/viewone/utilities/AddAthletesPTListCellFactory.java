package viewone.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.*;

//TODO Questa ci sarà utile per le list view quando sapremo usare i bean
public class AddAthletesPTListCellFactory {
    private Parent parentNode = null;
    private int i=0;
/*    @Override
    protected void updateItem(){                        //<--(EventBean item, boolean empty)
        super.updateItem(item, empty);
        if (item != null) {
            try {
                if (parentNode == null) {
                    parentNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListAthlete.fxml")));
                }
                Text titleText = (Text) parentNode.lookup("#" + "titleText") ;
                Text descriptionText = (Text) parentNode.lookup("#" + "descriptionText") ;
                Text eventDateText = (Text) parentNode.lookup("#" + "eventDateText");
                Text expirationDateText= (Text) parentNode.lookup("#"+"expirationDateText");
                Text participantNumberText= (Text) parentNode.lookup("#"+"participantNumberText");
                Text tagText= (Text) parentNode.lookup("#"+"tagText");
                ImageView immToChange= (ImageView) parentNode.lookup("#"+"immToChange");

                titleText.setText(item.getEventName());
                descriptionText.setText(item.getDescription());
                eventDateText.setText(String.valueOf(item.getDataEvento()));
                expirationDateText.setText(String.valueOf(item.getExpirationDate()));
                participantNumberText.setText(String.valueOf(item.getPartecipantNumber()));
                tagText.setText(item.getTag());

                ItemController itemController= new ItemController();
                EventBean sb= itemController.getLogo(item);
                String ttt= (sb.getEventPath());
                Image imm= new Image("file:"+ ttt);
                immToChange.setImage(imm);

                i++;

                setGraphic(parentNode);

            } catch (IOException e) {
                //
            }
        }
        else {
            setGraphic(null);
        }
    }*/
}