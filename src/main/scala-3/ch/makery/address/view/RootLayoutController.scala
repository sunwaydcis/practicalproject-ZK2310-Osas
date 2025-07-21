package ch.makery.address.view

import ch.makery.address.MainApp
import javafx.event.ActionEvent
import javafx.fxml.FXML


@FXML
class RootLayoutController():

  @FXML
  def handleClose(action: ActionEvent): Unit = {
    MainApp.stage.close()
    //System.exit(0) Force Shutdown
  }

  @FXML
  def handleDelete(action: ActionEvent): Unit = {
    MainApp.overviewController.map(x => x.handleDeletePerson(action))
  }

  