// ##NN Monty Hall Statistics Runner
// ##LL Kotlin
// ##WW https://en.wikipedia.org/wiki/Monty_Hall_problem
// ##DD Runs a statistic out of 100 'doors' with one having a prize, and runs it a thousand times. Gives statistics at the end of switching doors after choosing one versus staying on the door you chose at the beginning.
// ##CC

package munkurious.montyhall

import java.util.Random

var wins: Int = 0
var switchWins: Int = 0
var stayWins: Int = 0
var runs: Int = 1000

val random = Random()

fun main(args: Array<String>) {
    runTest()
}

fun runTest()
{
    var tests = 0;
    wins = 0;

    while(tests < runs)
    {
        println("--- Test " + tests + " ---")

        //choose which door prize is inside
        val prize = random.nextInt(0..101)
        println("prize at " + prize) 

        // get random out of 100; first door chosen
        val first = random.nextInt(0..101)
        println("Chose door " + first)

        //select second door with possibility of being opened
        var doorToOpen: Int
        if(first == prize)
        {
            doorToOpen = random.nextInt(0..101)
            while(doorToOpen != first || doorToOpen != prize)
            {
                doorToOpen = random.nextInt(0..101)
            }
        }
        else
        {
            doorToOpen = prize
        }

        println("Choose between door number " + first + " and " + doorToOpen)

        //0 equals stay, 1 means switch
        var second = random.nextInt(0..2)
        var chosen = 0;
        when(second)
        {
            0 -> {
                println("Choosing to stay on the first door")
                chosen = first
                if(chosen == prize)
                {
                    stayWins++
                }
            }
            1 -> {
                println("Choosing to switch to the other door")
                chosen = doorToOpen
                if(chosen == prize)
                {
                    switchWins++
                }
            }
            else ->
            {
                println("something went wrong. ERRORERRORERRORERROR")
            }
        }

        println("chose " + chosen + " - prize at " + prize)

        if(chosen == prize)
        {
            println("WIN - GOT PRIZE")
            wins++
        }
        else
        {
            println("LOOSE - NO PRIZE FOR YOU")
        }

        println(first)
        tests++
    }

    println("+-------------------------------+")
    println("Finished Simulations")
    println("Wins: " + wins + "/" + runs + "; " + ((wins.toDouble()/tests.toDouble())* 100) + "%")
    println("Stay Wins: " + stayWins + "/" + wins + "; " + ((stayWins.toDouble()/wins.toDouble())* 100) + "%")
    println("Switch Wins: " + switchWins + "/" + wins + "; " + ((switchWins.toDouble()/wins.toDouble())* 100) + "%")
}

//random generator for ranges
fun Random.nextInt(range: IntRange): Int 
{
    return range.start + nextInt(range.last - range.start)
}

