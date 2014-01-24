#!/bin/sh

cd ..
./recompile.sh
./reobfuscate_srg.sh

cd reobf/minecraft/
zip LTB.jar *

cd ../../jar-server
