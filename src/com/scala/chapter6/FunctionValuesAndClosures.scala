package com.scala.chapter6

/*
1) Functions are firstclass citizens in functional programming
2) Functions can be passed to and returned from functions as parameters
 */
object FunctionValuesAndClosures {
  def main(args: Array[String]) {
    /*
       <b>6.1 Moving from normal to Higher-order Functions</b>
       Let us write a method in Jave to find the the sum of a given range of numbers
         public int sum(int number){
         int result = 0;
         for(int i = 0;i <= number;i++){
           result += i
         }
         return result;
         }
       Assume that we want to count the number of even and odd numbers now.
       The best we can do is modify this function.But that is poor code resuability.
       In Scala however we can pass an anonymous function to the function that iterates over the range.
       So we can pass different functions with different logic.
       Such functions that accept other functions as parameters are called Higher Order Functions

       <b>6.2 Function Values </b>
       1) In Scala, we can create functions within functions, assign them to references, and pass them around to other functions.
       2) Scala internally deals with these so-called function values by creating them as instances of special classes
       3) Let’s rewrite our previous example in Scala using function values. Suppose we want to perform different operations
          (such as summing numbers or counting the number of even numbers) on a range of values.
       4) We’ll start by first extracting the common code, which is that code for looping over the range of values, into a method named totalResultOverRange():
       5) We have created the below function with 2 parameters, a number to iterate and a codeBlock which isa function value.
       6) The function parameter codeBlock is a function type which accepts int and returns int. The whole totalResultsOverRange methid returns Int
  */

    def totalResultsOverRange(number: Int,codeBlock: Int => Int): Int = {
      var results = 0
      for(i <- 0 to number){
        results += codeBlock(i)
      }
      results
    }

    //Now to calculate the sum of the values all we need to do is pass an anonymous function to the function totalResultsOverRange
    println("Total is : " + totalResultsOverRange(11,i => i))

    //To count the number of even number is the range all we need to is pass a different function
    println("Even Number is : " + totalResultsOverRange(11, i => if(i % 2 == 0) 1 else 0))

    /*
      7) Scala allows you to accept any number of parameters as function values, and they can be any parameter, not just the trailing parameter.
      8) Methods that accept function values are commonplace in the Scala library.

      <b>6.3 Function Values with Multiple Parameters</b>

      9) You can define and use function values with multiple parameters.
     10) Here is an example of a method inject( ) that passes the result of the operation on one element in an array of Int to the operation on the next element.
     11) There are a lot of things that are happening in this function.
     12) The inject( ) method takes three parameters:
          an array of Int,
          an initial Int value to inject into the operation,
          the operation itself as a function value
     13) In the method we set a variable carryOver to the initial value.
     14) We loop through the elements of the given array using the foreach( ) method.
         This method accepts a function value as a parameter, which it invokes with each element in the array as an argument.
     15) In the function that we send as an argument to foreach( ), we’re invoking the given operation with two arguments
          the carryOver value
          the context element
     16) We assign the result of the operation call to the variable carryOver so it can be passed as an argument in the subsequent call to the operation
     */

    def inject(arr:Array[Int],initial:Int,operation: (Int,Int) => Int): Int = {
      var carryover = initial
      arr.foreach(element => carryover = operation(carryover,element))
      carryover
    }

    /*
     17) Let's see how we can use this function to Sum an Array of values
     18) The first argument to the method inject( ) is an array whose elements we’d like to sum. The second argument is an initial value 0 for the sum.
     19) The third argument is the function that carries out the operation of totaling the elements, one at a time.

     */
    val array1 = Array(1,2)
    val sum = inject(array1,0,(i,j) => i + j)
    println(sum)


  }
}
