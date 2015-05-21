import java.text.DecimalFormat

import moo.qats.model.*

class BootStrap {
	
	private static final int NUM_INSTANCES = 500

	def init = { servletContext ->
		//////////////// LOAD RANDOM DATA FROM FILES /////////////////
		def ssns = this.class.classLoader.getResourceAsStream("data/ssns.txt").text.split('\n')
		def firstNames = this.class.classLoader.getResourceAsStream("data/first_names.txt").text.split('\n')
		def lastNames = this.class.classLoader.getResourceAsStream("data/last_names.txt").text.split('\n')

		def addressStrings = this.class.classLoader.getResourceAsStream("data/addresses.txt").text.split('\n\n')

		def addresses = addressStrings.collect {
			def lines = it.split('\n')

			Address address = new Address()
			address.line1 = lines[0]
			if(lines.size() > 3) address.line2 = lines[1]

			def lastLine = lines.last().split()
			// everything up to the last two elts in the array is the state
			// use a range to strip the trailing comma
			address.city = lastLine[0 .. lastLine.size() -3].join(' ')[0..-2]
			address.state = lastLine[-2]
			address.zip = lastLine.last()

			return address
		}

		//////////////// POPULATE AND SAVE RANDOM PERSON OBJECTS ////////////////
		def random = { lst -> lst[new Random().nextInt(lst.size())] }
		def format = new DecimalFormat("##%")
		println "Creating and persisting $NUM_INSTANCES Person objects..."
		def people = (1..NUM_INSTANCES).collect {
			def ssn = random(ssns)
			if(it % 5000 == 0 ) println format.format( it / NUM_INSTANCES as float)
			ssns -= ssn // single-use ssns
			new Person(
					firstName: random (firstNames),
					lastName: random (lastNames),
					address: random(addresses),
					ssn: ssn,
					)
		}
		people*.save(failOnError:true)
		println "... completed."

	}
	def destroy = {
	}
}
