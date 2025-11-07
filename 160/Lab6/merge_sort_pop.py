def mergesort(anArray):
  if (len(anArray) < 2):
    return (anArray)
  else:
    midpoint = len(anArray)//2
    firstPiece = anArray[0:midpoint]
    secondPiece = anArray[midpoint: len(anArray)]
    firstPiece  = mergesort(firstPiece)
    secondPiece = mergesort((secondPiece))
    return(merge(firstPiece, secondPiece))

def merge(a, b):
  c = []
  while (len(a)>0 or len(b) >0):
    if (len(a)==0):
      c.append((b[0]))
      b.pop(0)
    elif (len(b)==0):
        c.append((a[0]))
        a.pop(0)
    else:
      if (a[0] > b[0]):
        c.append((b[0]))
        b.pop(0)
      else:
        c.append((a[0]))
        a.pop(0)
  return(c)

if __name__ == "__main__":
    array = [5,3,8,6,2,7,4,1]
    print("Unsorted array: ", array)
    sortedArray = mergesort(array)
    print("Sorted array: ", sortedArray)