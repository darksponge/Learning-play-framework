import org.scalatestplus.play.{HtmlUnitFactory, OneBrowserPerSuite, PlaySpec}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Logger


class TaskList1Spec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "Task list 1" must {
    "login and access functions" in {
      go to s"http://localhost:$port/login1"
//      println(pageTitle)
      pageTitle mustBe "Login"
      find(cssSelector("h2")).isEmpty mustBe false
      find(cssSelector("h2")).foreach(_.text mustBe "Login")
      click on "username-login"
      textField("username-login").value = "Paul"
      click on "password-login"
      pwdField(id("password-login")).value = "12345678"
      submit()
      eventually {
        pageTitle mustBe "Task List"
        find(cssSelector("h2")).isEmpty mustBe false
        find(cssSelector("h2")).foreach(_.text mustBe "Task List")
        findAll(cssSelector("li")).toList.map(_.text) mustBe List("Make Videos", "Eat Bread", "Sleep", "Implement a Task List")
      }
    }
  }
}
