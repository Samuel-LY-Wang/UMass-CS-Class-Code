def recursive_search(lst: list, target) -> bool:
    if not lst:
        return False
    if lst[0] == target:
        return True
    try:
        return recursive_search(lst[1:], target) # will throw IndexError if lst only has 1 element
    except IndexError:
        # list was of index 1, thus no match found
        return False