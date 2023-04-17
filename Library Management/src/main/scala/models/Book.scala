package models

case class Book(id: Int, title: String, author: String, quantity: Int) {
  def increaseQuantity(amount: Int): Book = {
    val newQuantity = quantity + amount
    Book(id, title, author, newQuantity)
  }

  def decreaseQuantity(amount: Int): Book = {
    val newQuantity = quantity - amount
    Book(id, title, author, newQuantity)
  }
}