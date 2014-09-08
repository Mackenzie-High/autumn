# ~!/binbash

home=${PWD}
 
for f in $(find "examples/" -name 'code.leaf'); do 
    dir=$(dirname $f)
    code="$home/$dir/code.leaf"
    echo $f; 
    autumn run $code > "$dir/stdout.txt" 

    if grep -q "Parsing Failed" "$dir/stdout.txt"; then
        echo "Bad Example - Parsing Failed!"
        exit 1
    fi
done