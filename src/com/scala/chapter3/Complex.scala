package com.scala.chapter3

/**
  *Class Complex created with a constructor that accepts 2 parameters
  */
class Complex(val real:Int, val imaginary: Int) {
  def +(opearndi : Complex) : Complex = {
    println("Calling +")
    new Complex(real + opearndi.real,imaginary + opearndi.imaginary)
  }

  def *(opearndi : Complex) : Complex = {
    println("Calling *")
    new Complex(real * opearndi.real,imaginary * opearndi.imaginary)
  }

  override def toString() : String = {
    real + (if (imaginary < 0) "" else "+") + imaginary + "i"
  }
}
