# This script must be run on Mackenzie's computer due to the need for absolute file paths. 


# This is the path to where NetBeans will output the new JavaDoc on Mackenzie's computer.
# The JavaDoc generation is performed on the master branch. 
# The JavaDoc must be copied to the gh-pages branch for display on the website. 
NEW_JAVADOC="/media/disk/Code/NetBeansProjects/autumn/dist/javadoc"



# We cannot continue, if the new JavaDoc was not generated.
# The new JavaDoc must be generated manually, before this script is run.
if ! [ -a $NEW_JAVADOC ] ; then
    echo "Please generate the new JavaDoc, using NetBeans, before running this script."
    exit 1;
fi 



# Replace the old javadoc with the new javadoc.
rm -rf "javadoc/"
cp -r $NEW_JAVADOC "javadoc"



# Commit the changes and push the updates to the server.
git add .
git commit -am "auto update website"
git push "origin" "gh-pages"




