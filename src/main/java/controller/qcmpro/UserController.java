package controller.qcmpro;

import com.json.model.Quiz;
import com.json.model.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class UserController {

    @FXML
    private Text userName;
    @FXML
    private Text level;
    @FXML
    private Text email;
    @FXML
    private Text manage;

    public void initData(User u) {
        userName.setText(u.getuserName());
        level.setText("Level : "+u.getLevel());
        email.setText(u.getEmail());
        manage.setText("Manage");
    }

}
