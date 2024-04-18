# Usage: ckaa.sh <input_file>
# Usage: ckaa.sh

if [ $# -gt 1 ]; then
    echo "Usage: $0 <input_file> OR $0"
    exit 1
fi

javac -d ./bin -sourcepath ./src src/control_structure/*.java src/statements/*.java src/tokenization/*.java src/variables/*.java src/*.java


if [ $# -eq 1 ]; then
    java -cp ./bin Translator $1
    exit 0
fi

# otherwise do the default
java -cp ./bin Translator samples/TestInput.txt