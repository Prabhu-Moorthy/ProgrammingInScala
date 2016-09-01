package com.scala.chapter4

/**
  * Created by Prabhu on 31-08-2016.
  */
object ClassesInScala {
  def main(args: Array[String]) {
  /*
   * ########################################################
   * <h1>4.1 Creating Classes</h1>
   * ########################################################
   *
   */
    class Car(val year : Int) {
      private var milesDriven : Int = 0
      def miles() = milesDriven

      def drive(distance: Int){
        milesDriven += Math.abs(distance)
      }

      /**
        * In Scala the parameter to the class is doubled as the constructor
        * miles() is the getter method for milesDriven. Getter and Setters in Scala do not follow the Java naming convension
        */

    }

    val c = new Car(2009)

    println(c.year)
    c.drive(1000)
    println(c.miles())

  /*
   * ########################################################
   * <h1>4.2 Defining Fields, Methods, and Constructors</h1>
   * ########################################################
   *
   */
  /*
    The below class CreditCard is a complete class definition with 2 properties number anc creditLimit
  */
    class CreditCard(val number: Int, var creditLimit: Int)

    /**
      * Any Expression that we put inside a class is executed a part of the consutuctor
      */
    class Sample {
      println("Inside Sample Class")
    }

    new Sample

    /**
      * In addition to default constructors we can also have zero or moer auxillary constructors.
      * In the below code the primary constructor takes 2 parameters.
      * From within the auxiliary constructor, we’re calling the primary constructor to initialize the name-related fields
      * The first statement within an auxiliary constructor is required to be a call to either the primary constructor or another auxiliary constructor.
      * Scala treats fields specially. Any var defined within a class is mapped to a private field declaration followed by the definition of corresponding
      * accessor methods—getter and setter
      *
      * The access privilege you mark on the field is used for accessor methods. So, in the previous example, when we declared the field private var position: String = _, Scala created the
      * following:
      *
      * private java.lang.String position;
      * private void position_$eq(java.lang.String);
      * private java.lang.String position();
      *
      * Scala creates a special method position( ) for the getter and position_=( ) for the setter.
      *
      * In the definition of position, you could have set the initial value to null. Instead, we used an underscore (_).
      * In this context, the _ stands for the default value for the type—so, for Int, it is 0. For Double, it is 0.0.
      * This default initialization facility is only available for var and not val as val is treated as final
      *
      */

    class Person(val firstName: String, val lastName: String) {
      private var position :String = _

      def this(firstName: String,lastName: String,positionHeld: String){
        this(firstName,lastName)
        position = positionHeld
      }

      override def toString(): String ={
        firstName + " " + lastName + " holds " + position + " position "
      }
    }

    val john = new Person("John", "Smith","Analyst")
    println(john)

    /*
      * ########################################################
      * <h1>4.3 Extending a Class
      * ########################################################
      *
      */
    /**
      * Extending a class in Scala is similar to extending a class in Java except for two restrictions: method overriding requires the override keyword,
      * and only the primary constructor can pass parameters to the base constructor.
      */

    class Vehicle(val id:Int,val year: Int){
      override def toString():String = "ID: " + id + " Year: " + year
    }

    class Bus(override val id: Int, override val year: Int,
              var fuelLevel: Int) extends Vehicle(id, year) {
      override def toString() : String = super.toString() + " Fuel Level: " + fuelLevel
    }

    val b = new Bus(1,2009,100)
    println(b)
  }
}
