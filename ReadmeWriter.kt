// ##NN README Writer
// ##LL Kotlin
// ##WW 
// ##DD Writes a description of the daily programming problems handles with a link to their file for the ease of the README on github.
// ##CC

package munkurious.readmewriter

import java.io.File
import java.io.InputStream
import java.nio.file.* 

class FilesData(val map: Map<String, Any?>)
{
    val path: String by map
    val title: String by map
    val lang: String by map
    val site: String by map
    val desc: String by map
}

val extensions = arrayOf("kt")

fun main(args: Array<String>) {
    writeREADME()
}

fun writeREADME()
{
    //for the sense of sorting the programming tasks by language, it's easier to just rewrite the whole thing

    //delete the readme if there is one
    Files.deleteIfExists(Paths.get("", "README.md"))

    //write new readme
    val readmeFile = File("README.md")

    readmeFile.writeText("# Daily Programming Tasks")
    readmeFile.appendText("\nA place for me to hold all of my daily programming challenges, and other small programming challenges I do for fun. Most are from /r/DailyProgramming, but others are not.\n")

    var newList = ArrayList<ArrayList<FilesData>>()
    var list = scanFiles()
    
    for(item in list)
    {
        //sort the items into futher buckets. probably a better way of doing this but at the moment i am unaware
        if(list.size == 0)
        {
            //create first bucket
            var list2 = ArrayList<FilesData>()
            list2.add(item)
            newList.add(list2)
        }
        else
        {
            var sorted = false
            for(lists in newList)
            {
                if(lists.size > 0 && lists.get(0).lang.equals(item.lang)) // same bucket
                {
                    lists.add(item)
                    sorted = true
                    break
                }
            }

            if(!sorted)
            {
                //new bucket
                var list2 = ArrayList<FilesData>()
                list2.add(item)
                newList.add(list2)
            }
        }
    }

    for(lists in newList)
    {
        var first = true
        for(item in lists)
        {
            if(first)
            {
                readmeFile.appendText("\n## " + item.lang)
                first = false
            }

            readmeFile.appendText("\n### " + item.title)
            readmeFile.appendText("\n" + item.desc)
            readmeFile.appendText("\n\n" + item.site)
            readmeFile.appendText("\n")
        }
        readmeFile.appendText("\n\n")
    }
}

fun scanFiles():ArrayList<FilesData>
{
    var list = ArrayList<FilesData>()
    //list.add(FilesData(argumentshere))
    
    File(Paths.get("").toAbsolutePath().toString()).walk().forEach {
        if(it.toString().contains(".") && it.toString().split(".")[1] in extensions)
        {
            //is one of the items
            println("Creating new FilesData")
            val str = it.toString()
            println("Main String: " + str)

            println("Path: " + str)

        //    //readmewriter should be done but is special because i want it in the main directory
        //    val title:String
        //    if(str.contains("ReadmeWriter"))
        //    {
        //        title = str.split("DailyProgramming\\")[1].split(".")[0]
        //    }
        //    else
        //    {
        //        title = str.split("DailyProgramming\\")[1].split("\\")[1].split(".")[0]
        //    }
        //    println("Title: " + title)

            var title = "Title"
            var lang = "Language"
            var site = "Site"
            var desc = ""

            //read file for challenge information
            var cont = true
            File(str).useLines { 
                lines -> lines.forEach { that -> 
                    if(cont)
                    {
                        when
                        {
                            that.contains("##NN") -> title = that.split("##NN ")[1].trim()
                            that.contains("##LL") -> lang = that.split("##LL ")[1].trim()
                            that.contains("##WW") -> site = that.split("##WW ")[1].trim()
                            that.contains("##DD") -> desc = desc + "\n\n" + that.split("##DD ")[1].trim()
                            that.contains("##CC") -> cont = false
                            else -> false
                        }
                    }
                }
            }
            
            println("Title: " + title)
            println("Language: " + lang)
            println("Website: " + site)
            println("Description: " + desc)
            
            list.add(FilesData(mapOf(
                "path" to str,
                "title" to title,
                "lang" to lang, 
                "site" to site,
                "desc" to desc
                )))
        }
    }
    return list
}