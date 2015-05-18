
package moo.qats.model

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.gorm.Domain;
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Domain([Person,Address])
@TestMixin(HibernateTestMixin)
class PersonSpec extends Specification {

	def ssns, firstNames, lastNames, addresses
	def setup() {
		ssns = this.class.classLoader.getResourceAsStream("data/ssns.txt").text.split('\n')
		//ssns = ["123-12-1234"]
		firstNames = this.class.classLoader.getResourceAsStream("data/first_names.txt").text.split('\n')
		lastNames = this.class.classLoader.getResourceAsStream("data/last_names.txt").text.split('\n')

		def addressStrings = this.class.classLoader.getResourceAsStream("data/addresses.txt").text.split('\n\n')

		addresses = addressStrings.collect {
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
	}

	def cleanup() {
	}

	void "test something"() {
		setup:
		def random = { lst -> lst[new Random().nextInt(lst.size())] }

		when:
		def people = (1..50000).collect {
			def ssn = random(ssns)
			ssns -= ssn // single-use ssns
			new Person(
					firstName: random (firstNames),
					lastName: random (lastNames),
					address: random(addresses),
					ssn: ssn,
					)
		}
		people*.save(failOnError:true)

		then:
		assertUnique (people)
		Person.findAll().size() == 50000
	}

	boolean assertUnique(people){
		/* 
		 * // we COULD do something like this, but why might that cause us issues?
		 *  
		 * // false ==> don't mutate the original collection
		 * def unique = people.unique (false) { a, b ->
		 * 	//if ssn, firstName, and lastName match on this one, false
		 * 	["ssn","firstName","lastName"].every {
		 * 		a."$it".equals(b."$it")
		 * 	} ? 0 : 1 // 0 ==> items aren't unique
		 * }
		 */
		
		// quick and dirty!  assume ssn is unique
		assert people*.ssn.unique().size() == people.size()
	
		
		// going a step further, what else could we do to ensure uniqueness?	
		true
	}
}
