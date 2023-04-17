import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  // Initialize the repository
  val libraryRepository = new LibraryRepository()

  // Create some sample data
  val book1 = Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 5)
  val book2 = Book(2, "To Kill a Mockingbird", "Harper Lee", 3)
  val student1 = Student(1, "John Smith")
  val student2 = Student(2, "Jane Doe")

  // Add the sample data to the database
  val addBooks = libraryRepository.addBooks(Seq(book1, book2))
  val addStudents = libraryRepository.addStudents(Seq(student1, student2))
  Await.result(addBooks, 10.seconds)
  Await.result(addStudents, 10.seconds)

  // Retrieve the list of books and students
  val allBooks = libraryRepository.getAllBooks()
  val allStudents = libraryRepository.getAllStudents()
  val books = Await.result(allBooks, 10.seconds)
  val students = Await.result(allStudents, 10.seconds)

  println("All Books:")
  books.foreach(println)
  println("All Students:")
  students.foreach(println)

  // Register a student and check out a book
  val registerStudent = libraryRepository.registerStudent(student1.id, "The Great Gatsby")
  Await.result(registerStudent, 10.seconds)
  val checkOutBook = libraryRepository.checkOutBook(student1.id, book1.id)
  Await.result(checkOutBook, 10.seconds)

  // Retrieve the list of borrowed books for the student
  val borrowedBooks = libraryRepository.getBorrowedBooks(student1.id)
  val borrowed = Await.result(borrowedBooks, 10.seconds)

  println(s"${student1.name}'s borrowed books:")
  borrowed.foreach(println)

  // Check in the book
  val checkInBook = libraryRepository.checkInBook(student1.id, book1.id)
  Await.result(checkInBook, 10.seconds)

  // Retrieve the list of borrowed books for the student
  val newBorrowedBooks = libraryRepository.getBorrowedBooks(student1.id)
  val newBorrowed = Await.result(newBorrowedBooks, 10.seconds)

  println(s"${student1.name}'s borrowed books:")
  newBorrowed.foreach(println)
}
