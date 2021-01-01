# JSON-OPERATOR
An easy and basic Kotlin based JSON handler with minimum functionalities.

## Table of Contents
  1.Capability

## Capability
  This module works only on `Windows` and won't work on any other development platform

## Supported Functionality
  - [x] Create a JSON file
  - [x] HashMap to JSON
  - [x] JSON to HashMap
  - [x] Read by key
  - [x] Read by file
  - [x] Delete by File
  - [ ] Delete by key
  - [ ] TTL prop
 
## Bulid With
  - Kotlin
  
## Usage
  - 1 . Move the `Json.kt` file into your root class directory .
  - 2 . Import json Class by `import Json.json` .
  - 3 . Initialize `json` class by creating instance.
        ```
        val jsonClassObj : json = json()
        ```
  - 4 . Access class methods using created instance.
  
## Methods
  - 1 . `createJsonFile(path : String?) : Unit`
  Initialize json file by calling .
  ```
  //if you want default path then provide `null`
  jsonClassObj.createJsonFile(null)
  //if you want to set your own path then provide your path as String, example : d:\\demo
  jsonClassObj.createJsonFile("d:\\")
  ```
  
  - 2 . `createJson(hashMap :HashMap<Any,Any>?) : Unit`
  creates JSON key : value pair with this method . First create HashMap() of any type you want with key : value  pairs in it and pass it as an argument.
  ```
  //Any data type is accepted
  jsonClassObj.createJson(CustomHashMap)
  ```

  - 3 . ` getJsonPath():String? ` returns the Json files path if created before.
  
  - 4 . ` getJsonFile():File? ` returns the json file if created for other uses
  
  - 5 . ` readAllKeyValues(): HashMap<Any, Any>? ` call this to get all json key value if present into Kotlin HashMap<Any,Any>() else null will be returned if file is empty or not created.
  
  - 6 . ` findkey(Key : Any) : String? ` pass key value to get the json string.
  
## IMPORTANT REQUIREMENT

   **GSON open-source lib is use to parse Json from converting hashmap to Json and from Json to hashamap**.
   So, GSON is one of the basic requirement for this module , you can get it form [here](https://github.com/google/gson) and configure it.
