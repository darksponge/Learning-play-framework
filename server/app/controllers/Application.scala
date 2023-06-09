package controllers

import javax.inject._

import shared.SharedMessages
import play.api.mvc._
import play.api.i18n._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index(SharedMessages.itWorks))
  }

  def product(prodType: String, prodNum: Int): Action[AnyContent] = Action {
    Ok(s"Product type is: $prodType, product number is $prodNum")
  }

  def randomNumber: Action[AnyContent] = Action {
    Ok(util.Random.nextInt(100).toString)
  }

  def randomString(lenght: Int): Action[AnyContent] = Action {
    Ok(util.Random.nextString(lenght))
  }
  
}
