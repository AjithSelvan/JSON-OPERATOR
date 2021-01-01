/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
     TO IMPORT USE import Json.json WHEN ON CURRENT DIRECTORY
     THIS JSON CLASS CAN BE ONLY USED ON WINDOWS OS AND CAN'T BE USED ON ANY OS'es OR DEVELOPMENT PLATFORM                                                                                                                                                                         |
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 */


package Json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

//main class
class json{
    companion object{
        //gson object
        val gsonObject=Gson()
        //Additional file for saving json path
        val FILE_SAVE_LOCATION_FILE ="C:\\Users\\${System.getProperty("user.name")}\\Documents\\JsonSavedLocation.txt"
    }
    //deletes json and Additional file
    fun deleteAllAndReset():Boolean{
       return File(getJsonPath()).delete() && File(FILE_SAVE_LOCATION_FILE).delete()
    }
    //initialize file and its loaction is also saved here
    fun createJsonFile(path: String?){
        if (isFirstOpen()){
            //get path from user else default path
            val fileNameWithPath = if (path.isNullOrEmpty())  "C:\\Users\\${System.getProperty("user.name")}\\Documents\\jsonFileOnly.json"
            else "$path.json"
            File(fileNameWithPath).createNewFile()
            File(FILE_SAVE_LOCATION_FILE).createNewFile()
            //writing json's path to the backup file
            File(FILE_SAVE_LOCATION_FILE).writeText(File(fileNameWithPath).absolutePath)
            val fileWriter = FileWriter(FILE_SAVE_LOCATION_FILE, true)
            try {
                fileWriter.write("\nFalse")
                fileWriter.close()
                println("Successfully created! ${File(fileNameWithPath).absolutePath}")
            } catch (e: Exception) {
                print(e)
            }
        }else {
            println("Already created at ${getJsonPath()}")}
    }
    //hashMap is required as an native argument to store as a json using gson
    fun createJson(hashMap :HashMap<Any,Any>) {
        if (!isFirstOpen())
        try {
            //to create json like syntax
            val gson = GsonBuilder().setPrettyPrinting().create()
            //returns string from hashmap
            val rawstr=gson.toJson(hashMap)
            val fileWriter = FileWriter(getJsonPath(), true)
            try {
                fileWriter.write("$rawstr")
                fileWriter.close()
            } catch (e: Exception) {
                print(e)
            }
        }catch (e: Exception){
            e.stackTrace
        }
    }
    //gets the current json file path
    fun getJsonPath():String?{
        val BR : BufferedReader =File(FILE_SAVE_LOCATION_FILE).bufferedReader()
        val path=BR.readLine()
        BR.close()
        return path
    }
    //returns json as file
    fun getJsonFile():File?{
        return if (File(getJsonPath()).exists())
            File(getJsonPath())
        else null
    }
    //This method returns all key value pair as hashmap
    fun readAllKeyValues(): HashMap<Any, Any>?  {
        var allKeyValues: HashMap<Any, Any>? = null
       try {
            //read the json file as string
            val jFileAsString = File(getJsonPath()).inputStream().readAllBytes().toString(Charsets.UTF_8)
            if (File(getJsonPath()).exists()) {
                val type = object : TypeToken<HashMap<Any, Any>>() {}.type
                val returnedHash = gsonObject.fromJson<HashMap<Any, Any>>(jFileAsString, type)
                allKeyValues = returnedHash
            }
        }catch (e : Exception){
           print("Create a file first OR empty file")
        }
        return allKeyValues
        }
    fun findValue(Key: Any): String? {
        var Value: String?=null
        try {
            var key=Key.toString()
            val jFileAsString = File(getJsonPath()).inputStream().readAllBytes().toString(Charsets.UTF_8)
            if (File(getJsonPath()).exists()) {
                val type = object : TypeToken<HashMap<Any, Any>>() {}.type
                val ab = gsonObject.fromJson<HashMap<Any, Any>>(jFileAsString, type)
                Value = ab[key] as String
            }
        }catch (e : Exception){
            print("NOT FOUND OR empty file")
        }
        return  Value
    }
    //checks whether the given
    private fun keyChecker(Key : String): Throwable? {
        val key= Key
        var countOfChar=0
        key.onEach {
            countOfChar += 1
        }
        if (key != null) {
            if (countOfChar >= 32 ){
                return Exception("please enter key within 32 char and value less than 8000 char")
        }else print("Provide proper values \n")}
        else print("please enter key within 32 char and value less than 8000 char \n")
        return null
    }
    //to check for the json file is already created
    private fun isFirstOpen(): Boolean {
        var found: String? =null
        if (File(FILE_SAVE_LOCATION_FILE).exists()){
        val fileReader= FileReader(FILE_SAVE_LOCATION_FILE)
        fileReader.useLines {
            found= it.last()
        }}
        return found != "False"
    }
}
