if [ $# -ne 1 ]; then
    echo "Usage: $0 <token file name>"
    echo
    exit 1
fi

java -jar ciparser-1.0.jar token $1
