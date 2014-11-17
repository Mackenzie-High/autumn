# ~!/binbash

home=${PWD}
 
for f in $(find "examples/" -name 'Main.leaf'); do 
    dir="$home/$(dirname "$(dirname "$(dirname "$f")")")"
    echo $dir; 
    cd "$dir/project/src/"
    autumn run > "$dir/stdout.txt"
    rm -f "$dir/project/src/stdout.txt"

    if grep -q "Parsing Failed" "$dir/stdout.txt"; then
        echo "Bad Example - Parsing Failed!"
        exit 1
    fi
done