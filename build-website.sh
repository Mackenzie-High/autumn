

# Replace the old javadoc with the new javadoc.
rm -rf "javadoc/"
cp -r "/media/disk/Code/NetBeansProjects/autumn/dist/javadoc" "javadoc"


# Commit the changes and push the updates to the server.
git add .
git commit -am "update website"
git push "origin" "gh-pages"

