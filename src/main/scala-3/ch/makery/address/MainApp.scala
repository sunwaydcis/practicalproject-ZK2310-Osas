package ch.makery.address

import ch.makery.address.model.Person
import ch.makery.address.util.Database
import ch.makery.address.view.{PersonEditDialogController, PersonOverviewController}
import javafx.fxml.FXMLLoader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes.*
import javafx.scene as jfxs
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image
import scalafx.stage.{Modality, Stage}

object MainApp extends JFXApp3:

  Database.setupDB()
  //Window Root Pane
  var roots: Option[scalafx.scene.layout.BorderPane] = None
  //Window Root Pane

  var cssResource = getClass.getResource("view/DarkTheme.css")
  var overviewController: Option[PersonOverviewController] = None
  /**
   * The data as an observable list of Persons.
   */
  val personData = new ObservableBuffer[Person]()

  /**
   * Constructor
   */
  personData ++= Person.getAllPersons

  override def start(): Unit =
    // transform path of RootLayout.fxml to URI for resource location.
    val rootResource = getClass.getResource("view/RootLayout.fxml")
    // initialize the loader object.
    val loader = new FXMLLoader(rootResource)
    // Load root layout from fxml file.
    loader.load()

    // retrieve the root component BorderPane from the FXML
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "AddressApp"
      icons += new Image(getClass.getResource("/images/86957_address_book_icon.png").toExternalForm)
      scene = new Scene():
        stylesheets = Seq(cssResource.toExternalForm)
        root = roots.get




    // call to display PersonOverview when app start
    showPersonOverview()
  // actions for display person overview window
  def showPersonOverview(): Unit =
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    val ctrl = loader.getController[PersonOverviewController]
    overviewController = Option(ctrl)
    this.roots.get.center = roots

  def showPersonEditDialog(person: Person): Boolean =
    val resource = getClass.getResource("view/PersonEditDialog.fxml")
    val loader = new FXMLLoader(resource)
    loader.load();
    val roots2 = loader.getRoot[jfxs.Parent]
    val control = loader.getController[PersonEditDialogController]

    val dialog = new Stage():
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene:
        root = roots2
        stylesheets = Seq(cssResource.toExternalForm)

    control.dialogStage = dialog
    control.person = person
    dialog.showAndWait()

    control.okClicked






//    val stringA =  new StringProperty("Sunway") //publisher
//    val stringB = new StringProperty("Monash") //subscriber
//    val stringC = new StringProperty("Taylor") //subscriber
//
//    stringB.<==(stringA)
//    stringC.<==(stringA)
//    stringA.value= "Segi"
//    stringC.value = "UTM"
//
//    println("String A value:" + stringA.value)
//    println("String B value:" + stringB.value)
//    println("String C value:" + stringC.value)
//    stringA.onChange((a,b,c) => {
//      println("a has changed value from" + b + "to" + c)
//    })
//    stringA.value = "Monash"
//
//    val add:(Int, Int) => Int = (a: Int, b: Int) => {a + b}
//    println(add(1,2))