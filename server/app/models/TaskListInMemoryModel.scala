package models

import collection.mutable

object TaskListInMemoryModel {
    
    private val users = mutable.Map[String, String]("Paul" -> "12345678", "Steph" -> "4567")
    private val tasks = mutable.Map[String, List[String]]("Paul" -> List("Make Videos", "Eat Bread", "Sleep", "Implement a Task List"))

    def validateUser(username: String, password: String): Boolean = 
        users.get(username) match {
            case Some(p) =>
                p == password
            case None => 
                false
        } 
        
    def createUser(username: String, password: String): Boolean = 
        users.get(username) match {
            case Some(_) => false   // The user already exists
            case None =>
                 users(username) = password
                 true
        }

    def getTasks(username: String): Seq[String] = 
        tasks.get(username) match {
            case Some(list) => list
            case None => Nil
        }
    def getUsers: List[String] = users.keys.toList

    def addTask(username: String, task: String): Unit = {
        if (users.contains(username)) {
            tasks.get(username) match {
                case Some(ts) =>
                    tasks(username) = task :: ts
                case None =>
                    tasks(username) = task :: Nil
            }
        }

    }

    def removeTask(username: String, index: Int): Boolean = {
        if (index >= 0 && tasks.contains(username) && index < tasks(username).length) {
            tasks(username) = tasks(username).patch(index, Nil, 1)
            return true
        }
        false
    }
}