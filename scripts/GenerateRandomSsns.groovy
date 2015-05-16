/** 
 * Do some goofy stuff to get pseudorandom SSNs that are partitioned by
 * the ip of the machine running this script.
 */
def generateSSN(){
	def first3 = InetAddress.localHost.hostAddress.split("\\.")[3].padLeft(3,'0')
	def last6 = (new Random().nextInt(1000000) as String).padLeft(6,'0')
	"-$first3$last6"[1..3,0,4..5,0,6..9] //HA!
}

if (args.length == 0 || !args[0].isNumber()) {
	println "Usage is groovy ${this.class}.groovy [number]"
	return
}

int count = args[0] as int
if(count == 0) return

//generate a unique list
def bag = new HashSet()
while(bag.size() < count) bag << generateSSN()

bag.each { println it }
