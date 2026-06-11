#!/bin/bash

echo "Compilation des fichiers..."
cd src
javac @compile.list -d ../class
cd ..

if [ $? -eq 0 ]; then
    echo "Compilation réussie ! Lancement du programme..."
    echo "--------------------------------"
    cd class
    java -cp . src.Controleur
    cd ..
else
    echo "Erreur lors de la compilation."
fi
