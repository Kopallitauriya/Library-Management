package db

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

class LibraryRepository(db: Database) {
  import Tables._

  def getAllBooks(): Future[Seq[Book]] =
    db.run(books.result).map(_.map(Book.tupled))

  def getBookById(id: Int): Future[Option[Book]] =
    db.run(books.filter(_.id === id).result.headOption).map(_.map(Book.tupled))

  def addBook(book: Book): Future[Book] =
    db.run((books returning books.map(_.id) into ((book, id) => book.copy(id = id))) += book).map(Book.tupled)

  def updateBookQuantity(id: Int, quantity: Int): Future[Int] =
    db.run(books.filter(_.id === id).map(_.quantity).update(quantity))

  def searchBooks(title: String, author: String): Future[Seq[Book]] =
    db.run(books.filter(b => b.title like s"%$title%" && b.author like s"%$author%").result).map(_.map(Book.tupled))

  def getAllCustomers(): Future[Seq[Customer]] =
    db.run(customers.result).map(_.map(Customer.tupled))

  def getCustomerById(id: Int): Future[Option[Customer]] =
    db.run(customers.filter(_.id === id).result.headOption).map(_.map(Customer.tupled))

  def addCustomer(customer: Customer): Future[Customer] =
    db.run((customers returning customers.map(_.id) into ((customer, id) => customer.copy(id = id))) += customer).map(Customer.tupled)

  def removeCustomer(id: Int): Future[Int] =
    db.run(customers.filter(_.id === id).delete)

  def getAccountByCustomerId(customerId: Int): Future[Option[Account]] =
    db.run(accounts.filter(_.customerId === customerId).result.headOption).map(_.map(Account.tupled))

  def depositMoney(accountId: Int, amount: Int): Future[Int] =
    db.run(accounts.filter(_.id === accountId).map(_.balance).update(_ + amount))

  def registerStudent(student: Student): Future[Student] =
    db.run((students returning students.map(_.id) into ((student, id) => student.copy(id = id))) += student).map(Student.tupled)

  def getAllStudents(): Future[Seq[Student]] =
    db.run(students.result).map(_.map(Student.tupled))

  def checkoutBook(bookId: Int, studentId: Int, dueDate: String): Future[BorrowedBook] =
    db.run((borrowedBooks returning borrowedBooks.map(_.id) into ((borrowedBook, id) => borrowedBook.copy(id = id))) +=
      BorrowedBook(bookId = bookId, studentId = studentId, dueDate = dueDate)).map(BorrowedBook.tupled)

  def checkinBook(borrowedBookId: Int): Future[Int] =
    db.run(borrowedBooks.filter(_.id === borrowedBookId).delete)
}
