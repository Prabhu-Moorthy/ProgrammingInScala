package com.scala.chapter3

/**
  * Created by Prabhu on 25-08-2016.
  */
object Greetings {
  def main(args: Array[String]) {
    /**
      * <- implies that the left side is a val and it is assigned with aggregated values that are given on the right side
      * to is a function
      */
    for (i <- 1 to 3){
      print(i + ",")
    }
    println("Scala Rocks!!!")

    /**
      * until is a function. The difference from to and until is that to includes the lower and upper bound where as unitl does not include the upper bound
      * In both the above statements i is a val and so it is immutable and each time a new instance of i is created and a new value assigned to it
      */
    for(i <- 1 until 3){
      print(i + ",")
    }
    println("Scala Rocks!!!")

    /**
      *More powerfull functional style for loop
      * We are using the foreach method of the Range class.
      * Foreach method accepts a functional value as a parameter
      * Within the parameter we are providing the body of the function that takes one parameter which is i
      * the => seperates the parameter list on the left with the implementation on the right
      */
    (1 to 3).foreach(i => print(i + ","))
    println("Scala Rocks!!!")

    /**
      * Scala treats everythig as an object there are no primitive types in scala
      */
    println(2.toString())


    /**
      *To illustrate how scala and java code can be mixed in the below code we create a scala Int capacity and pass it to
      * the ensureCapacity method if the Java Arraylist which is expecting a primitive int
      */
    playWithInt()
    def playWithInt(){
      val capacity : Int = 10
      val list = new java.util.ArrayList[String]
      list.ensureCapacity(capacity)
    }

    /**
      * Tuples and Multiple Assignments
      * In the below code we have a function getPersonInfo which returns 3 values.
      * If this code was written in Java we either need to return an PersonInfo object or create a list and add the values.
      * But in Scala we can create a tuple(an immutable object sequence created as comma separated values) and return it from a frunction.
      * With multiple assignments we can assign the tuple to multiple vals or vars
      */
    def getPersonInfo(primaryKey: Int) = {
      ("Prabhu","DM","prabhu.hotpepper@gmail.com")
    }

    val(firstname,lastname,email) = getPersonInfo(1)

    println("First Name " + firstname)
    println("Last Name " + lastname)
    println("e-mail " + email)

    /*Compilation error throws because we need to return 3 values*/
    //val(firstname,lastname) = getPersonInfo(1)

    /*Alternatively we can assign the tuple to one val and get it using ._1, ,_2 etc..*/
    val personInfo = getPersonInfo(1)

    println(personInfo._1)
    println(personInfo._2)

    /**
      * Strings and Multimline Raw Strings
      * String that span multiple lines can be placed in three double quotes
      */
    val str = """In his famous inaugural speech, John F. Kennedy said
                "And so, my fellow Americans: ask not what your country can do
                 for you-ask what you can do for your country." He then proceeded
                 to speak to the citizens of the World..."""
    println(str)

    /*There is some weird indendation present when you print the above statement.
    * Scala's RichString has convenience methods to handle this scenario
    * However wherever we need to remove indendation we need to place the pipe symbol (|)
    * */
    val str1 = """In his famous inaugural speech, John F. Kennedy said
                |"And so, my fellow Americans: ask not what your country can do
                |for you-ask what you can do for your country." He then proceeded
                |to speak to the citizens of the World...""".stripMargin
    println(str1)

    /**
      * Operator Overloading
      * Scala has no operators, so technicall there is no operator overloading, In scala + and - etc., symbols are
      * method names and these can be treated as normal operators because of the flexibility in scala to leave out . and () whenever necessary
      * So c1 + c2 actually means c1.+(c2)
      */
    val c1 = new Complex(1,2)
    val c2 = new Complex(2,-3)

    val sum = c1 + c2

    println("(" + c1 + ") + (" + c2 + ") = " + sum)

    /**
      * Presidence in scala, since there is no opearator in scala the first character of a method neame is used to find the precidence
      * If you print the below code you will understand that * is called before + because of the BODMAS rule which is applied to the first character of the method here
      */
    val c3 = new Complex(2,2)
    println(c1 + c2 * c3)
  }
}
