/**
 * Created by Emilia.Palaghita on 7/4/2017.
 */
public class AnimalZooRar extends Animal {

    String nume;
    String taraDeOrigine;

    public AnimalZooRar() {
        nume = "Leu";
        taraDeOrigine = "China";
    }

    public AnimalZooRar(String n) {
        nume = n;
    }

    public AnimalZooRar(String n, String tara) {
        nume = n;
        taraDeOrigine = tara;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTaraDeOrigine() {
        return taraDeOrigine;
    }

    public void setTaraDeOrigine(String taraDeOrigine) {
        this.taraDeOrigine = taraDeOrigine;
    }

    @Override
    public void mananca(Object o) throws AnimalManancaOmException, AnimalManancaAnimalException{
        if (o instanceof AngajatZoo) {
            throw new AnimalManancaOmException("animalul mananca un angajat zoo!");
        } else if (o instanceof Animal) {
            throw new AnimalManancaAnimalException("animalul mananca un alt animal");
        } else {
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }
}
