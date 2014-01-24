#!/bin/bash

cd $1

if [ "$2" = "" ]; then
	pwd
	
	cd ..
	./recompile.sh
	./reobfuscate_srg.sh
	pwd

	cd reobf/minecraft/
	zip -r LTB.jar *
	rm -rf ../../jar-server/mods/LTB.jar 2> /dev/null
	mv LTB.jar ../../jar-server/mods/LTB.jar
	pwd

	cd ../../jar-server
	pwd
fi;
terminator -e "java -jar mcpc-plus-1.6.4-R2.1-forge965-B224.jar; READ PAUSE -p \"PAUSE\""

