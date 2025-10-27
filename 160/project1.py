def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

def are_relatively_prime(x,y):
    # two numbers are relatively prime if their greatest common divisor is 1
    return gcd(x, y) == 1

def primes_up_to(n):
    primes=[]
    for i in range(n):
        if is_prime(i):
            primes.append(i)
    return primes

def prime_decomposition(n):
    factors=[]
    cur_factor=2
    while n>1:
        if n%cur_factor==0:
            factors.append(cur_factor)
            n=n//cur_factor
        else:
            cur_factor+=1
    return factors

def decomp_check(n):
    factorization=prime_decomposition(n)
    return len(factorization)==2 and factorization[0] != factorization[1]