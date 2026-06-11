#!/bin/bash

echo -e "\e[36mCompilation des fichiers...\e[0m"
cd src || exit 1

javac @compile.list -d ../class

EXIT_CODE=$?
cd ..

if [ $EXIT_CODE -eq 0 ]; then
    echo -e "\e[32mCompilation réussie ! Lancement du programme...\e[0m"
    echo "--------------------------------"
    java -cp ./class src.Controleur
else
    echo -e "\e[31mErreur lors de la compilation.\e[0m"
fi
