/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {
    int bonus = 0;
    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
        calculeazaBonusSalarial();
    }

    @Override
    public void calculeazaBonusSalarial() {
        bonus += 3 * valoareBonusPerAnimal;
    }

    @Override
    public void afisareBonus() {
        System.out.println("Bonus cumulat: " + bonus);
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException {
        if (animal instanceof AnimalZooRar && mancare == null) {
            throw new AnimalPeCaleDeDisparitieException("animal rar fara mancare");
        } else {
            lucreaza(animal);
            animal.doarme();
            animal.faceBaie();
            animal.mananca(mancare);
            animal.seJoaca();
            calculeazaBonusSalarial();
        }
    }

    public int getBonus() {
        return bonus;
    }
}
