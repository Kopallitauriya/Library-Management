package models

case class Account(id: Int, customerId: Int, balance: Double) {
  def deposit(amount: Double): Account = {
    val newBalance = balance + amount
    Account(id, customerId, newBalance)
  }

  def withdraw(amount: Double): Account = {
    val newBalance = balance - amount
    Account(id, customerId, newBalance)
  }
}