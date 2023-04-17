package db

import slick.jdbc.MySQLProfile.api._

object Tables {
  class BooksTable(tag: Tag) extends Table[(Int, String, String, Int)](tag, "books") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def author = column[String]("author")
    def quantity = column[Int]("quantity")
    def * = (id, title, author, quantity)
  }

  val books = TableQuery[BooksTable]

  class CustomersTable(tag: Tag) extends Table[(Int, String, String)](tag, "customers") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def address = column[String]("address")
    def * = (id, name, address)
  }

  val customers = TableQuery[CustomersTable]

  class AccountsTable(tag: Tag) extends Table[(Int, Int, Int)](tag, "accounts") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def customerId = column[Int]("customer_id")
    def balance = column[Int]("balance")
    def * = (id, customerId, balance)
    def customer = foreignKey("customer_fk", customerId, customers)(_.id)
  }

  val accounts = TableQuery[AccountsTable]

  class StudentsTable(tag: Tag) extends Table[(Int, String, String)](tag, "students") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def department = column[String]("department")
    def * = (id, name, department)
  }

  val students = TableQuery[StudentsTable]

  class BorrowedBooksTable(tag: Tag) extends Table[(Int, Int, Int, String)](tag, "borrowed_books") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def bookId = column[Int]("book_id")
    def studentId = column[Int]("student_id")
    def dueDate = column[String]("due_date")
    def * = (id, bookId, studentId, dueDate)
    def book = foreignKey("book_fk", bookId, books)(_.id)
    def student = foreignKey("student_fk", studentId, students)(_.id)
  }

  val borrowedBooks = TableQuery[BorrowedBooksTable]
}
