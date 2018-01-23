// ##NN Foundations Path - Challenge 1
// ##LL Kotlin
// ##WW https://techdevguide.withgoogle.com/paths/foundational/find-longest-word-in-dictionary-that-subsequence-of-given-string#code-challenge
// ##DD > Given a string S and a set of words D, find the longest word in D that is a subsequence of S. Word W is a subsequence of S if some number of characters, possibly zero, can be deleted from S to form W, without reordering the remaining characters.
// ##DD 
// ##CC

package munkurious.foundationspathchallenge1

val D = arrayOf("able", "ale", "apple", "bale", "kangaroo")
val S = "abppplee"


fun main(args: Array<String>) {
    findSubsequence(S, D)
}

fun findSubsequence(s: String, D: Array<String>)
{
    var subs: MutableList<String> = mutableListOf<String>()
    for(e in D)
    {
        var ind = 0
        for(char: Char in s)
        {
            if(ind == e.length)
            {
                subs.add(e)
                break
            }

            if(char.equals(e.get(ind)))
            {
                ind++
            }
        }
    }

    var lastlength = 0
    var winner = ""
    for(sub: String in subs)
    {
        if(sub.length > lastlength)
        {
            lastlength = sub.length
            winner = sub
        }
    }

    println("The longest common subsequence is: " + winner)
}