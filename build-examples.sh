# ~!/binbash

home=${PWD}
 
for f in $(find "examples/" -name 'code.leaf'); do 
    dir=$(dirname $f)
    code="$home/$dir/code.leaf"
    echo $f; 
    autumn -i -src $code > "$dir/stdout.txt"
done