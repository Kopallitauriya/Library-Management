package db

import com.typesafe.config.ConfigFactory
import slick.jdbc.MySQLProfile.api._

object DatabaseConfig {
  private val config = ConfigFactory.load()

  private val dbUrl = config.getString("db.url")
  private val dbUser = config.getString("db.user")
  private val dbPassword = config.getString("db.password")

  val db: Database = Database.forURL(dbUrl, dbUser, dbPassword)
}
