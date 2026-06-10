Write-Host "Compilation des fichiers..." -ForegroundColor Cyan
cd src

javac @compile.list -d ../class
cd ..

if ($LastExitCode -eq 0) {
    Write-Host "Compilation réussie ! Lancement du programme..." -ForegroundColor Green
    Write-Host "--------------------------------"
    java -cp ./class src.Controleur
} else {
    Write-Host "Erreur lors de la compilation." -ForegroundColor Red
}
