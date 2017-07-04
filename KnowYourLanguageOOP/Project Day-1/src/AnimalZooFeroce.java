/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {
    @Override
    public void mananca(Object o) throws AnimalManancaOmException, AnimalManancaAnimalException {
        if (o instanceof AngajatZoo) {
            throw new AnimalManancaOmException("animalul mananca un angajat zoo!");
        } else if (o instanceof Animal) {
                throw new AnimalManancaAnimalException("animalul mananca alt animal");
        } else {
            System.out.println("AnimalZooFeroce mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalZooFeroce se joaca");
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalZooFeroce face baie");
    }

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }
}
