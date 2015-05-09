JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    DrawPoint.java \
    ThreeDVector.java \
    MatrixUtility.java \
    RGBColor.java \
    LineClipping.java \
    ThreeDPoint.java \
    ZBuffer.java \
	CG_hw5.java

MAIN = CG_hw5

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class