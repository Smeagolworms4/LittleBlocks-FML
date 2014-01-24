#!/bin/sh

cd $1

if [ "$2" = "" ]; then
	pwd
	
	cd ..
	./recompile.sh
	./reobfuscate_srg.sh
	pwd

	cd reobf/minecraft/
	zip LTB.jar *
	mv LTB.jar ../../jar-server/mods/LTB.jar
	pwd

	cd ../../jar-server
	pwd
fi;
java -jar mcpc-plus-1.6.4-R2.1-forge965-B224.jar

