# qats-random
My solution to the QATS random data homework assignment

This task is intended to familiarize you with the some OOP nomenclature as well as (hopefully) learn the expressive power of
Groovy. It will require you to read and write files, use random number generators, and structure a basic Grails application.

The scenario is this:
You are a developer working on a customer database have to generate random data for your unit tests. Some of the data has been provided to you in the form of text files, but you need to assign the data from those files to domain objects.

## Part 1 - Files and the Classpath
Attached to this ticket are several text files. Use the data in these files to randomly populate a domain object with values.
class Person {
                 String ssn
                 String lastName
}

Each time your program is run, it should populate 100 instances of Person and save them. Each run should produce a different
set of values using the ssns and last names from the file. Can your program read the file off the classpath?


## Part 2 - Writing data
Your test scenario does not have any first names yet! You do not have time to gather a list of names since this is just test
data. Using an array of first names, write code that will combine a relatively short list of first names into longer
combination names, e.g. if the list were
def firstNames = ['Billy','Bobby','Joe','Timmy','Tommy','Ricky']

Your code should create all combinations of these names ["Billy Bobby", "Billy Joe","Billy Timmy","Billy Tommy","Billy
Ricky" ... ] and write them to a file, one entry per line.
What can you do to increase the total number of permutations? With a 256-char database column, how many combo-names can you make with the given array?

## Part 3 - Addresses and Object Composition
You also need addresses for these Persons! Attached to this ticket is a list of addresses. Create an Address class and
compose it into the Person class.
What is the advantage of object composition? Why might it be inadvisable to just put address fields (line1, line2, city,
state, zip) into Person?
Modify your code to read the addresses from the file and populate Address instances.


## Part 4 - Bringing it all together
You now have files containing first names, a file containing SSNs, a file containing last names, and a file containing
addresses.  Write a unit test which will generate 50000 unique test entries (Person and Address) and save them to a database. Use Grails' built-in h2 database.

Add a method to your unit test that ensures each entry is unique.

## Part 5 - Grails UI
Write a grails-based UI that provides CRUD access to your 50,000 test entries.


