package website

import java.util.Date

sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
                            val id: String,
                            val createdAt: Date = new Date()
                          ) extends Visitor

final case class User(
                       val id: String,
                       val email: String,
                       val createdAt: Date = new Date()
                     ) extends Visitor
