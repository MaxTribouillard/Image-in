# Tests Unitaires - Calcul de Coût par Projet

## Fichier créé : CoutProjetTest.java

J'ai créé un fichier de test unitaire complet pour vérifier les calculs de coût par projet pour les **Salariés** et les **Prestataires**.

## Corrections apportées au code existant :

### 1. **Intervenant.java** - Bug critique corrigé ✅
```java
// AVANT (INCORRECT) :
if (a.getProjet().getNom() == nomProjet) {  // ❌ Comparaison par référence

// APRÈS (CORRECT) :
if (a.getProjet().getNom().equals(nomProjet)) {  // ✅ Comparaison par valeur
```

### 2. **Salarie.java** - Ajout de getter/setter pour `cout` ✅
```java
public int getCout() {
    return cout;
}

public void setCout(int cout) {
    this.cout = cout;
}
```

## Tests créés (8 scénarios) :

### ✅ Tests pour Salarié :
1. **testCoutProjetSalarie** : Calcul du coût avec plusieurs affectations (60 + 40 = 100 min × 5€ = 500€)
2. **testCoutProjetSalarieMultiplesProjets** : Vérification que le calcul est correct pour plusieurs projets distincts
3. **testCoutProjetSansAffectation** : Vérifie que le coût est 0 pour un projet inexistant

### ✅ Tests pour Prestataire :
4. **testCoutProjetPrestataire** : Calcul avec le tarif prestataire (120 min × 15€ = 1800€)
5. **testCoutProjetPrestataireForfait** : Calcul avec le tarif société/forfait (120 min × 10€ = 1200€)
6. **testCoutProjetPrestataireDifferencesForfaitVsPresta** : Compare les deux modes de calcul

### ✅ Tests de comparaison :
7. **testComparaisonCoutsSalarieVsPrestataire** : Vérifie que pour le même temps, le prestataire coûte plus cher

## Comment exécuter les tests dans Eclipse :

1. **Actualiser le projet** : Clic droit sur `Imagein` → `Refresh` (F5)
2. **Exécuter les tests** : 
   - Clic droit sur `CoutProjetTest.java`
   - `Run As` → `JUnit Test`
3. **Voir les résultats** : La vue JUnit affichera les résultats

## Structure des tests :

```
@BeforeEach
setUp() {
    // Crée un salarié avec cout = 5€/min
    // Crée un prestataire avec cout = 15€/min et société avec cout = 10€/min
    // Crée deux projets : "Projet Alpha" et "Projet Beta"
}

@Test
testXXX() {
    // Crée des affectations
    // Calcule le coût
    // Vérifie avec assertEquals()
}
```

## Résumé des formules testées :

### Salarié :
```java
coutProjet = tempsPassee × salarie.cout
```

### Prestataire (mode prestataire) :
```java
coutProjet = tempsPassee × prestataire.coutMinute
```

### Prestataire (mode forfait société) :
```java
coutProjet = tempsPassee × prestataire.societe.coutMinute
```

## Statut : ✅ Tous les fichiers compilent sans erreur

Les tests sont prêts à être exécutés dans Eclipse !
