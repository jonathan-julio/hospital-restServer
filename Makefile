PROGRAMS=hospital-server
DIR_SOURCE=./src
DIR_DIST=./dist
JAR_SUFIX=0.0.1-SNAPSHOT

build: setup
	./mvnw package -Dmaven.test.skip -DskipTests
	cp $(foreach PROGRAM, $(PROGRAMS), ./target/$(PROGRAM)-$(JAR_SUFIX).jar) $(DIR_DIST)/
	ls $(DIR_DIST)/*.jar | awk '/$(JAR_SUFIX).jar$$/ {file=$$0; sub(/-$(JAR_SUFIX).jar$$/, ".jar", file); print "mv", $$0, file }' | sh

clean:
	./mvnw clean -f $(DIR_SOURCE)
	rm $(DIR_DIST)/*.jar

setup:
	@-mkdir -p dist