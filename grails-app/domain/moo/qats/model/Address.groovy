package moo.qats.model

class Address {
	String line1
	String line2
	String city
	String state
	String zip


	// cascade as part of Person.save(), but don't include a backreference to person	
	static belongsTo = [Person]	
    static constraints = {
		line2 nullable:true
		state (matches: '[A-Z]{2}')
		zip (matches: '^[0-9]{5}(-[0-9]{4})?$')
    }
}
