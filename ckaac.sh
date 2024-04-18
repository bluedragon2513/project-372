# Usage: ckaac.sh <input_file>

if [ $# -ne 1 ]; then
    echo "Usage: $0 <input_file>"
    exit 1
fi

javac -d ./bin -sourcepath ./src src/control_structure/*.java src/statements/*.java src/tokenization/*.java src/variables/*.java src/*.java

java -cp ./bin Compiler $1