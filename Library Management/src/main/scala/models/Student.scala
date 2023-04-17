package models

case class Student(id: Int, name: String, department: String) {
  var borrowedBooks: List[BorrowedBook] = List()

  def borrowBook(book: Book, dueDate: String): BorrowedBook = {
    val borrowedBook = BorrowedBook(book.id, id, dueDate)
    borrowedBooks = borrowedBook :: borrowedBooks
    borrowedBook
  }

  def getBorrowedBooks: List[BorrowedBook] = {
    borrowedBooks
  }

  def returnBook(borrowedBook: BorrowedBook): Unit = {
    borrowedBooks = borrowedBooks.filterNot(_ == borrowedBook)
  }
}
