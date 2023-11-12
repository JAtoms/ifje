# Makefile for Composition of Functions to Mathematical Expression Transformer

# Define the Java compiler
JAVAC = javac
# Define the Java interpreter
JAVA = java
# Main class
MAIN_CLASS = Main

# Source files
SRC = Main.java Parser.java Lexer.java Expression.java Token.java

# Build directory
BUILD_DIR = build

# Output JAR file
OUTPUT_JAR = CompositionTransformer.jar

# Input composition of functions
INPUT = "add(5, mul(3, sub(10, pow(6, 4))) /* comment */)"

# Compile the Java source files
compile:
	@mkdir -p $(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) $(SRC)

# Run the program
run: compile
	$(JAVA) -cp $(BUILD_DIR) $(MAIN_CLASS) $(INPUT)

# Create an executable JAR file
jar: compile
	jar cfe $(OUTPUT_JAR) $(MAIN_CLASS) -C $(BUILD_DIR) .

# Clean the build directory
clean:
	rm -rf $(BUILD_DIR)

.PHONY: compile run jar clean
