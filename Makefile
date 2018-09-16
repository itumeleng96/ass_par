
#IJ Malemela 

JFLAGS = -g -d bin src/*.java -cp bin
JC = javac
vpath %.java src
vpath %.class bin

.SUFFIXES: .java .class 
.java.class:
	$(JC) $(JFLAGS) $<

all: 	 doc \
	 SumArray.class \
	 avgSunCalculator.class \
	 avgSunSeq.class \
	 Tree.class 
run: all 
	java -cp ./bin avgSunCalculator
		   

doc: all
	javadoc -d docs src/*.java

clean:
	rm -r bin/*.class
	rm -r docs/*.html
