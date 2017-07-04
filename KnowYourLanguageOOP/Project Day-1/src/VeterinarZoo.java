/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {

    int bonus = 0;

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Veterinarul are grija de animal");
        if(animal instanceof AnimalZooFeroce) {
            animal.faceBaie();
        }
        calculeazaBonusSalarial();
    }

    @Override
    public void calculeazaBonusSalarial() {
        bonus += 2 * valoareBonusPerAnimal;
    }

    public void afisareBonus() {
        System.out.println("Bonus cumulat: " + bonus);
    }
}
