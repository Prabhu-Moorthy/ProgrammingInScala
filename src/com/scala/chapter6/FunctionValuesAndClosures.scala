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
    println("Sum is : " + sum)


    //20) If instead of summing we need to compute the max of the elements all we need to do is

    val max = inject(array1,Integer.MIN_VALUE,(i,j) => Math.max(i,j))
    println("Mas value is : " + max)

    /*
     21) If we’d like to navigate over elements in a collection and perform operations, you don’t have to really roll out your own inject( ) method.
     22) Scala library already has this method built in. It is the foldLeft( ) method.
     23) It is also the method /:.
     */
    val array2 = Array(2, 3, 5, 1, 6, 4)
    val sum1 = (0 /: array2) { (sum, elem) => sum + elem }
    val sum2 = array2.foldLeft(0){(i,j) => i+j}
    //both the above sum's do the same thing, just the syntax is different
    val max1 = (Integer.MIN_VALUE /: array2) {(large,element) => Math.max(large,element)}

    println("Sum of elements in array " + array2.toString() + " is " + sum1)
    println("Sum of elements in array " + array2.toString() + " is " + sum2)
    println("Max of elements in array " + array2.toString() + " is " + max1)

    /*
    24) In the above code block the function value was placed inside curly braces instead of being sent as an argument to the function.
    25) However, if we attempt the following on the inject() method, we will get an error:
    26) To overcome this error we need to know soemthing what is known as currying
     */
    //val sum4 = inject(array2,0){(i,j) => i + j}

    /*
    <b>Currying</b>
    27) Currying in Scala transforms a function that takes more than one parameter into a function that takes multiple parameter lists.
    28) If you’re calling a function multiple times with the same set of arguments, you can reduce the noise and spice up your code by using currying.
    29) Instead of writing a method that takes one parameter list with multiple parameters write it with multiple parameter lists with one parameter
        each (you may have more than one parameter in each list as well)
    30) instead of def foo(a: Int, b: Int, c: Int) {}, write it as def foo(a: Int)(b: Int)(c: Int) {}.
    31) We can then call it as, for example, foo(1)(2)(3), foo(1){2}{3}, or even foo{1}{2}{3}
     */

    def foo(a:Int,b:Int,c:Int) {
      println("Inside Foo with parameters" + a + " " + b + " " + c)
    }

    def foo1(a:Int)(b:Int)(c:Int){
      println("Inside Foo1 with parameters" + a + " " + b + " " + c)
    }

    foo(2,3,4)
    foo1(6){7}{8}

    /*
    Now let's rewrite the inject method that we saw earlier in curried form
    The parameter list is the only differnce between the first inject function and this inject function
    The difference lies however in how we call this method
     */

    def inject1(arr:Array[Int],initial:Int)(operation: (Int,Int) => Int): Int = {
      var carryover = initial
      arr.foreach(element => carryover = operation(carryover,element))
      carryover
    }
    val ar2 = Array(1,2)
    val sum22 = inject1(ar2,0){(i,j) => i+j}

    println("Sum in curried form is " + sum22)

    /*
    <b>6.5 Reusing Function Values</b>
     32) We saw how function values help create more reusable code and eliminate code duplication by passing a function as a value
     33) But, embedding a method as an argument to another method doesn’t encourage reuse of that code.
     34) You can, however, create references to function values and therefore reuse them as well.
     35) Assume we have a class Equipment that expects us to provide a calculation routine for its simulation. We can send in the calculation as a function value to the constructor like this:
     */

    class Equipment(val routune: Int => Int){
      def simulate(input:Int) = {
        println("Runnig routine")
        routune(input)
      }
    }

    /*
    36) When we create instances of Equipment, we can pass in a function value as a parameter to the constructor.
     */
    val equipment1 = new Equipment({i => println("The value being passed is  : " + i);i})
    val equipment2 = new Equipment({i => println("The value being passed is  : " + i);i})

    equipment1.simulate(4)
    equipment1.simulate(5)

    /*
    37) In the previous code, we want to use the same calculation code for both the Equipment instances. Unfortunately, that code is duplicated.
    38) if we decide to change the calculation, we’d have to change both.
    39) It would be good to create that once and reuse it. We can assign the function value to a val and reuse it like this:
    40) We stored the function value into a reference named calculator.
    41) Scala needed a little help with the type information when we defined this function value.
        In the earlier example, Scala inferred the input as Int based on the context of the call.
    42) However, since we’re defining this function value as stand-alone, we had to tell Scala the type of the parameter.
    43) We then passed the name of the reference as an argument to the constructor in the two instances we created.
     */

    val calculator = {i: Int => println("The value being passed as a reference function is :" + i);i}

    val equipment3 = new Equipment(calculator)
    val equipment4 = new Equipment(calculator)

    equipment3.simulate(7)
    equipment4.simulate(8)

    /*
    44) In the previous example, we created a reference calculator to a function value.
    45) This may feel more natural to you since you’re used to defining references/variables within functions or methods.
    46) Scala however  allows you to pass in a normal function where a function value is expected.
     */

    def calculator1(input: Int):Int = {
      println("Calculator1 is a function which receives the value : " + input)
      input
    }

    val equipment5 = new Equipment(calculator1)
    val equipment6 = new Equipment(calculator1)

    equipment5.simulate(77)
    equipment6.simulate(87)

    /*
    <b>6.6 Positional Notation for Parameters</b>
    47) Scala provides the notation _, the underscore, to represent parameters of a function value.
    48) You can use this if you plan to use that parameter only once in the function.
    49) Each time you use the underscore within a function, it represents a subsequent parameter.
     */

    val arr50 = Array(1, 2, 3, 4, 5)
    println("Sum of all values in the array is " + arr50.foldLeft(0)((sum,elem) => sum + elem))
    /*
    50) In the function value since we are using sum and elem only once when can substitute it with the _
    51) The first occurrence of _ represents the value carried over in the invocation of the function, and the second represents elements in the array.
     */
    println("Sum of all values in the array using _ is " + arr50.foldLeft(0)( _ + _))
    /*
    52) The fold left method that we call on the array can also be represented with the notation /:
     */
    println("Sum of all values in the array using _ and using /: is " + (0 /: arr50)( _ + _))

    /*
    53) Where it makes sense, you can take this conciseness further. Assume we have a function that determines the maximum of two numbers.
     */
    def max54(val1:Int,val2:Int):Int = {
      if(val1 > val2) val1 else val2
    }

    var max50 = arr50.foldLeft(Integer.MIN_VALUE){max54(_,_)}

    println("Max value using _ is : " + max50)

    /*
    Scala takes the consciousness to a whole new level
    54) The _ represents not only a single parameter; it can represent the entire parameter list as well.
     */

    var max51 = arr50.foldLeft(Integer.MIN_VALUE){max54 _}

    println("Max value using _ onlyu once is : " + max51)

    /*
    55) the _ represents the entire parameter list, that is, (parameter1, parameter2).
        If you are merely passing what you receive to an underlying method, you don’t even need the ceremony of the _
     */

    var max52 = arr50.foldLeft(Integer.MIN_VALUE){max54}

    println("Max value using nothing is : " + max52)

  }
}
