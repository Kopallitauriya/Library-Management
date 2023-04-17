package models

case class BorrowedBook(bookId: Int, customerId: Int, dueDate: String) {
  def extendDueDate(newDueDate: String): BorrowedBook = {
    BorrowedBook(bookId, customerId, newDueDate)
  }
}