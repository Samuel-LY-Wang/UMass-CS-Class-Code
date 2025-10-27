def recursive_count(lst: list, target) -> int:
    if not lst:
        return 0
    if lst[0] == target:
        try:
            return 1 + recursive_count(lst[1:], target) # will throw IndexError if lst only has 1 element
        except IndexError:
            return 1
    else:
        try:
            return recursive_count(lst[1:], target) # will throw IndexError if lst only has 1 element
        except IndexError:
            return 0

if __name__ == "__main__":
    sample_list = [1, 3, 4, 5]
    target_value = 2
    count = recursive_count(sample_list, target_value)
    assert count == 0
    print(f"The value {target_value} appears {count} times in the list {sample_list}.")