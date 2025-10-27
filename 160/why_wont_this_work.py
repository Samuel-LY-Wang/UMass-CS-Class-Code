#Name: Samuel Wang

#can't use math.sqrt?
#try **0.5 instead (a**b -> raise a to the power of b -> a^b on a calculator)

def my_quadratic(a,b,c):
    # y1 = -b + √(b^2 - 4ac) / 2a
    # y2 = -b - √(b^2 - 4ac) / 2a
    # on error: print out error, and return warning values
    if (a==0):
        return None,None
    try:
        disc=(b**2-4*a*c)**0.5
        plus = (-b+disc)/(2*a)
        minus = (-b-disc)/(2*a)
        return plus,minus
    except ValueError as e:
        print(e, "- Function is not Quadratic")
        print("Divided by zero")
        return None,None

#####################################
if __name__ == "__main__":

        ## MY TESTS ##
        ## TEST 1: ##
        #y = x^2 + 8x + 16
        #should be: y1 = -4, y2 = -4
    y1,y2 = my_quadratic(1,8,16)
    print(y1,y2)

        ## TEST 2: ##
        #y = x^2 + 10x + 2
        #should ~ be: y1 = -5.2041..., y2 = -14.7958... 
    y1,y2 = my_quadratic(1,10,2)
    print(y1,y2)

        ## TEST 3: ##
        #y = x^2
        #should be: y1 = 0.0, y2 = 0.0 
    y1,y2 = my_quadratic(1,0,0)
    print(y1,y2)

        ## TEST 4: ##
        #y = x^2 + 4
        #give two imaginary values
    y1,y2 = my_quadratic(1,0,4)
    print(y1,y2)