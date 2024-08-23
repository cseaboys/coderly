import sys
from random import randint
from operator import xor
from functools import reduce


def main():
    data = {i: 0 for i in range(1, 8)}
    # data_bits = [int(x) for x in input()]
    data_bits = [1, 0, 1, 1]
    data_indices = [3, 5, 6, 7]
    for i, index in enumerate(data_indices):
        data[index] = data_bits[i]

    def get_parity(p):
        p1 = [1, 3, 5, 7]
        p2 = [2, 3, 6, 7]
        p4 = [4, 5, 6, 7]
        parity = lambda li: reduce(xor, [data[i] for i in li])
        match p:
            case 1:
                return parity(p1)
            case 2:
                return parity(p2)
            case 4:
                return parity(p4)

    data[1] = get_parity(1)
    data[2] = get_parity(2)
    data[4] = get_parity(4)

    print(f"Encoded data: {data}")

    # todo: random bit to flip


if __name__ == "__main__":
    main()
