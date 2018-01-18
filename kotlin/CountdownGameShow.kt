// ##NN [Easy] Countdown Game Show
// ##LL Kotlin
// ##WW https://www.reddit.com/r/dailyprogrammer/comments/6fe9cv/20170605_challenge_318_easy_countdown_game_show/
// ##DD This one was quite difficult for me, as I have not learned recursion at the time I took this project on. Just getting the recusion was a difficult enough task, the permutation just increased it. > The rules are pretty simple: Given a set of numbers X1-X5, calculate using mathematical operations to solve for Y. No PEMDAS.
// ##CC

package munkurious.countdowngameshow

import java.util.Arrays

val operations = arrayOf("+", "-", "*", "/")
var solutions = 0

fun main(args: Array<String>) {
    //handle inputs through arrays
    runShow(intArrayOf(25, 100, 9, 7, 3, 7), 881)
    runShow(intArrayOf(6, 75, 3, 25, 50, 100), 952)
}

fun runShow(array: IntArray, solut: Int)
{
    solutions = 0
    println("Starting show with array: " + Arrays.toString(array))

    println("Looking for total: " + solut)

    println("Brute forcing " + Math.pow((array.size).toDouble(), 4.toDouble()) + " solutions to find correct one.")

    doIt(array.toMutableList(), solut, "")
    for(perm in permute(array.toList()))
    {
        doIt(perm.toMutableList(), solut, "")
    }
    println("Done. Found " + solutions + " solutions.")
}

fun doIt(array: MutableList<Int>, solut: Int, str: String)
{
    if(array.size == 1)
    {
        if(array.get(0) == solut || Math.abs((solut - array.get(0))) < 0.0000001)
        {
            println("Found Solution! " + array.get(0))
            println(str + " = " + solut)
            solutions++
        }
        else
        {
            //println("Not a solution")
            //println(str + " != " + solut + " , but = " + array.get(0))
        }
    }
    else if(array.size > 1)
    {
        for(i in 0..3)
        {
            var arr: MutableList<Int> = mutableListOf()
            arr.addAll(array)
            val x = operations[i]
            var newstr: String
            if(str == "")
            {
                newstr = "" + arr.get(0) + " " + x + " " + arr.get(1)
            }
            else
            {
                newstr = "" + str + " " + x + " " + arr.get(1)
            }
            val new = runCalc(arr.get(0), arr.get(1), x)
            arr.set(0, new)
            arr.removeAt(1)
            doIt(arr, solut, newstr)
        }
    }
}

//permutation code from rosettacode (this stuff blew my mind)
//http://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}


fun runCalc(first: Int, second: Int, op: String): Int
{
    when(op)
    {
        "+" -> return first + second
        "-" -> return first - second
        "*" -> return first * second
        "/" -> return first / second
        else -> return 0
    }
}