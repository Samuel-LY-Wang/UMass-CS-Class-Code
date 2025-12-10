class PrimeFunctions {
    public static boolean IsPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i=2; i*i<=n; i++) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }

    public int[] PrimesUpTo(int n) {
        int count = 0;
        int[] primes = new int[n];
        for (int i=2; i<n; i++) {
            if (IsPrime(i)) {
                primes[count] = i;
                count++;
            }
        }
        return ArrayUtils.slice(primes, 0, count);
    }

    public int[] PrimeDecomposition(int n) {
        if (n == 1) {
            return new int[0];
        }
        int[] factors = new int[n];
        int count = 0;
        for (int i=2; i<=n; i++) {
            while (n%i==0 && IsPrime(i)) {
                factors[count] = i;
                count++;
                n /= i;
            }
        }
        return ArrayUtils.slice(factors, 0, count);
    }
}