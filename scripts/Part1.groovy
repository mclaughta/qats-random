def dataDir = new File(getClass().protectionDomain.codeSource.location.path).parentFile.parent  + "/grails-app/conf/data/"

def lastNames = new File("${dataDir}/last_names.txt").text.split('\n')
def ssns = new File("${dataDir}/ssns.txt").text.split('\n')

def random = { lst -> lst[new Random().nextInt(lst.size())] }

class Person { String ssn, lastName } 

(1..100) .collect {
	Person p = new Person(ssn: random(ssns), lastName: random(lastNames))
}.each {
	println "${it.ssn}: ${it.lastName}"
}