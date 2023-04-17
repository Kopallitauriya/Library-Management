package models

case class Customer(id: Int, name: String, address: String) {
  var accounts: List[Account] = List()

  def getAccountById(accountId: Int): Option[Account] = {
    accounts.find(_.id == accountId)
  }

  def addAccount(account: Account): Unit = {
    accounts = account :: accounts
  }

  def removeAccount(accountId: Int): Unit = {
    accounts = accounts.filterNot(_.id == accountId)
  }
}