package ConcepteOOP;

public class LazyNumber {

    private final int number;

    public LazyNumber(int number) {
        this.number = number;
    }

    //Verificare numar perfect
    public boolean isPerfect() {
        int sum = 0;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0 && number != 1 || number != 0) {
                sum += i;
            }
        }
        return number == sum + 1;
    }

    //Verificare numar prim
    public boolean isPrime() {
        int counter = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                counter++;
            }
        }
        return counter == 2;
    }

    //Verificare daca numarul este par si suma cifrelor este divizibila cu 3
    public boolean isMagic() {

        int numberCopy = number;
        int sum = 0;
        while (numberCopy != 0 && number != 0) {
            sum += numberCopy % 10;
            numberCopy /= 10;
        }
        return number % 2 == 0 && sum % 3 == 0;
    }

}
