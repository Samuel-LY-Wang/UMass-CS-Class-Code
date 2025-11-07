def mergesort(anArray):
    if (len(anArray) < 2):
        return anArray
    else:
        midpoint = len(anArray)//2
        firstPiece = anArray[:midpoint]
        secondPiece = anArray[midpoint:]
        firstPiece = mergesort(firstPiece)
        secondPiece = mergesort(secondPiece)
        return merge(firstPiece, secondPiece)

def merge(a, b):
    c = []
    a_len=len(a)
    b_len=len(b)
    ind_a=0
    ind_b=0
    while (ind_a < a_len and ind_b < b_len):
        if (a[ind_a] > b[ind_b]):
            c.append((b[ind_b]))
            ind_b += 1
        else:
            c.append((a[ind_a]))
            ind_a += 1
    if (ind_a < a_len):
        c.extend(a[ind_a:a_len])
    if (ind_b < b_len):
        c.extend(b[ind_b:b_len])
    return c

if __name__ == "__main__":
    array = [5,3,8,6,2,7,4,1]
    print("Unsorted array: ", array)
    sortedArray = mergesort(array)
    print("Sorted array: ", sortedArray)