import models.TaskListInMemoryModel
import org.scalatestplus.play._

class TaskListInMemoryModelSpec extends PlaySpec {
    "TaskListInMemoryModel" must {
        "do valid login for default user" in {
            TaskListInMemoryModel.validateUser("Paul", "12345678") mustBe (true)
        }

        "reject login with wrong username" in {
            TaskListInMemoryModel.validateUser("Fred", "12345678") mustBe (false)
        }

        "reject login with wrong password" in {
            TaskListInMemoryModel.validateUser("Paul", "1234") mustBe (false)
        }

        "reject login with wrong password and wrong usernam" in {
            TaskListInMemoryModel.validateUser("Fred", "asdfghjk") mustBe (false)
        }

        "get correct default tasks" in {
            TaskListInMemoryModel.getTasks("Paul") mustBe List("Make Videos", "Eat Bread", "Sleep", "Implement a Task List")
        }

        "create new user with no tasks" in {
            TaskListInMemoryModel.createUser("Fred", "asdfghjk") mustBe (true)
            TaskListInMemoryModel.getTasks("Fred") mustBe Nil
        }

        "create user with existing name" in {
            TaskListInMemoryModel.createUser("Paul", "Password") mustBe (false)
        }

        "add new task for default user" in {
            TaskListInMemoryModel.addTask("Paul", "testing")
            TaskListInMemoryModel.getTasks("Paul") must contain ("testing")
        }

        "add new task for non-existing user" in {
            TaskListInMemoryModel.addTask("Mike", "testing1")
            TaskListInMemoryModel.getTasks("Mike") must not contain("testing1")
        }

        "remove tasks" in {
            TaskListInMemoryModel.addTask("Paul", "testing1")
            TaskListInMemoryModel.getTasks("Paul") must contain("testing1")
            TaskListInMemoryModel.removeTask("Paul", 0)
            TaskListInMemoryModel.getTasks("Mike") must not contain("testing1")
        }
    }
}