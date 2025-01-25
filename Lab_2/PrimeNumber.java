import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(isPrime(n));
    }

    private static long isPrime(int num) {
        boolean prime[] = new boolean[num + 1];
        for (int i = 0; i <= num; i++)
            prime[i] = true;

        for (int p = 2; p * p <= num; p++) {
            if (prime[p] == true) {
                for (int i = p * p; i <= num; i += p)
                    prime[i] = false;
            }
        }

        long count = 0;
        for (int i = 2; i <= num; i++) {
            if (prime[i] == true)
                count++;
        }

        return count;
    }
}
