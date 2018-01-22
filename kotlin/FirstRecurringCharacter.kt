// ##NN First Recurring Character
// ##LL Kotlin
// ##WW https://www.reddit.com/r/dailyprogrammer/comments/7cnqtw/20171113_challenge_340_easy_first_recurring/
// ##DD > Write a program that outputs the first recurring character in a string.
// ##CC

package munkurious.FirstRecurringCharacter

val strings = arrayOf("IKEUNFUVFV", "PXLJOUDJVZGQHLBHGXIW", "*l1J?)yn%R[}9~1\"=k7]9;0[$")

fun main(args: Array<String>) {
    for(i in strings)
    {
        findFirstRecurringCharacter(i)
    }
}

fun findFirstRecurringCharacter(string: String)
{
    println("Finding First Recurring Character of : " + string)
    val alreadySeen = arrayListOf("")
    var found = false

    for(c in string)
    {
        if(found)   break

        for(x in alreadySeen)
        {
            if(c.toString().equals(x))
            {
                //is first recurring
                val index = string.indexOf(c, 0, false)
                println("The First Recurring letter is: " + c + " at index: " + index)
                found = true
                break
            }
        }

        alreadySeen.add(c.toString())
    }    
}