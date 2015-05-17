class BootStrap {

    def init = { servletContext ->
		//PART 1 - read a file off the classpath
		def ssns = this.class.classLoader.getResourceAsStream("data/ssns.txt").text.split('\n')
		def lastNames = this.class.classLoader.getResourceAsStream("data/last_names.txt").text.split('\n')
    }
    def destroy = {
    }
}
