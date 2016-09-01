package com.scala.chapter5

import java.util

/**
  * In Scala we do not have to specify the type in most cases
  */
object SensibleTyping {
  def main(args: Array[String]) {
    /**
      * In the below code the type for month is inferred automatically be Scala to be Int
      */

    val year:Int = 2016
    val month = 10

    /**
      * ########################################################
      * <h1>5.1 Collections and Type Inference
      * ########################################################
      *
      */
    /**
      * Scala will provide type inference and type safety for the Java Generics collections as well.
      * The following is an example that uses an ArrayList. The first declaration uses explicit, but redundant, typing. The second declaration takes advantage of type inference.
      */
    var list1 : util.List[Int] = new util.ArrayList[Int]()
    var list2 = new util.ArrayList[Int]

    list2 add 1
    list2 add 2

    list1 add 1
    list1 add 1

    var total : Int = 0

    for (i <- 0 until list1.size()){
      total += list1.get(i)
    }
    println("Total is " + total)

    /**
      * Scala is vigilant about the type of the object you instantiate. It prohibits conversions that may cause typing issues.
      * list3 is an arraylist if Int type
      * list4 is unspecified
      * But behind the scne scala created an Arraylist[Nothing]
      * That is why when we assign list3 = list4 we get an error
      *
      * Nothing is a subclass of all classes in Scala.
      * Nothing is the bottom-most subclass.
      */

    var list3 = new util.ArrayList[Int]
    var list4 = new util.ArrayList

    //list3 = list4

    /**
      * So, how can you create a new ArrayList without specifying the type?
      * One way is to use the type Any.
      * Any is the base type of all types in Scala
      */

    var list5 = new util.ArrayList[Int]
    var list6 = new util.ArrayList[Any]

    var one : Int = 1
    var two : Any = null

    //Since two is any type we are able to assign two to one but the reverse will not be possible
    two = one

    //Still Scala does not allow the same flexibility with lists
    //list6 = list5

    /**
      * Option Type
      * When you perform pattern matching, for example, the result of the match may be an object, a list, a tuple, and so on, or it may be nonexistent.
      * Returning a null quietly is problematic.
      * Scala wants you to clearly specify your intent that sometimes you do actually expect to give no result. Scala achieves this in a type-safe manner using the Option[T] type
      * Here, commentOnPractice( ) may return a comment (String) or may not have any comments at all.
      * This is represented as instances of Some[T] and None.
      */

    def commentOnPractice(input : String)={
      if(input == "test") Some("good") else None
    }

    for(input <- Set("test","Hack")){
      val comment = commentOnPractice(input)
      println("input " + input + " comment " + comment.getOrElse("Found no comments"))
    }

    /**
      * Method Return Type Inference
      * In addition to inferring the types of variables, Scala also tries to infer the return type of methods.
      * However, there is a catch. It depends on how you define your method.
      * f you define your method with an equals sign (=), then Scala infers the return type. Otherwise, it assumes the method is a void method.
      */

    /**
      * Passing Variable Argumants (Varargs)
      * If your method takes parameters, you need to specify the parameter names and their types
      * You can write a method that takes a variable number of arguments.
      * Use the special symbol (*) after the type information
      */

    def divide(op1: Double, op2: Double) = op1/op2 * 2

    println(divide(4,2))

    //Scala treats the varargs parameter (values in the previous example) as an array, so we can iterate over it
    def max(values : Int*) = values.foldLeft(values(0)) {Math.max}

    println(max(2,3,4))

    //we can’t, however, send an array
    val numbers = Array(2,3,4,5)
    //max(numbers) throws error

    //If we have to pass an array we need to explode the values in the array as follows
    println(max(numbers:_*))

    /**
      * Variance of parameterized Type
      */
    //Scala prevents you from making assignments that may potentially lead to runtime failures. Specifically, it prevents the following code from compiling:
    var list8 = new util.ArrayList[Int]
    var list9 = new util.ArrayList[Any]

    //Scala does not allow this because in scala we cannot an instance of a sub class to an instance of its base class unlike java
    //This is actually a good thing and we will see a Java example to understand why this is a good thing.
    //list9 = list8

    //<<<<<<<<<<<<<<<<<<<SEE JAVA PACKAGE FOR CHAPTER 5>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
      * covariance and contravariance
      * The ability to send a collection of subclass instances to a collection of
        base class is called covariance. And the ability to send a collection of
        superclass instances to a collection of subclass is called contravariance.
        By default Scala does not allow either one of them.

        However there are some scenarios like below when this functionality is necessary.
        Consider 2 classes Pets and Dogs derived from pets
      */

    class Pet(val name:String){
      override def toString() = name
    }

    class Dog(override val name:String) extends Pet(name)

    def workWithPets(pets: Array[Pet]) {}

    val dogs = Array(new Dog("Hulsasion"),new Dog("Comet"))

    //We will get a compilation error in the below statment because Scala does not know that we are sending a derived type where it is expecting a base type
    //There is way to overcome this
    //workWithPets(dogs)

    /*
    This is a bit intense. In the below method definition we have a new syntax T <: Pet. What we mean by this is that we are telling Scala
    that this methiod will accept any type T which is derived from the type Pet
     */
    def playWithPets[T <: Pet](pets: Array[T]){
      println("Playing with Pets" + pets.mkString(", "))
    }

    playWithPets(dogs)

    //Similarly if we want out class in Scala to allow covariance then all we need to do is

    //we need to pass a + infront of the type parameter
    class MyList[+T]

    var list11 = new MyList[Int]
    var list12 = new MyList[Any]

    //The below statement is possible now because now we have told Scala to allow covariance
    list12 = list11

    //Similarly contravariance can be allowed by passing class MyList[-T]

  }
}
