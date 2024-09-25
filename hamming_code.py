from random import choice, randint

# import sys

# sys.stdin = open("in", "r")
# sys.stdout = open("out", "w")


def main():
    data = [0] * 7
    buff = [int(i) for i in input("Enter 4 bits: ")]
    if len(buff) != 4:
        raise Exception("Length of buff should be 4")
    # buff = [1, 0, 1, 1]

    data[2] = buff[0]
    data[4:] = buff[1:]
    print(f"Data without enconding: {data}")

    def get_parity():
        return (
            data[0] ^ data[2] ^ data[4] ^ data[6],
            data[1] ^ data[2] ^ data[5] ^ data[6],
            data[3] ^ data[4] ^ data[5] ^ data[6],
        )

    data[0], data[1], data[3] = get_parity()
    print(f"Encoded data: {data}")
    should_flip = choice([True, False])
    if should_flip:
        print("Let's not flip the bit, No error")
    else:
        print("Let's flip a bit")
        rand_bit_flip = randint(0, 6)

        print(f"Error index: {rand_bit_flip}")
        data[rand_bit_flip] = 1 - data[rand_bit_flip]
        print(f"Error Data: {data}")

    p1, p2, p4 = get_parity()
    if p1 == p2 == p4 == 0:
        print("No error found")
        print(f"Encoded data: {data}")
    else:
        error_index = ((p4 * 4) + (p2 * 2) + p1) - 1
        data[error_index] = 1 - data[error_index]
        print(f"Correctly encoded data: {data}")


main()


"""
Supposed to be modular and encapsulated, the prolonged one, same

from random import choice, randint


class HammingError(Exception):
    pass


def encode_data(buff):
    if len(buff) != 4:
        raise HammingError("Length of buff should be 4")
    data = [0] * 7
    data[2] = buff[0]
    data[4:] = buff[1:]
    return data


def calculate_parity_bits(data):
    return (
        data[0] ^ data[2] ^ data[4] ^ data[6],
        data[1] ^ data[2] ^ data[5] ^ data[6],
        data[3] ^ data[4] ^ data[5] ^ data[6],
    )


def introduce_error(data):
    should_flip = choice([True, False])
    if should_flip:
        print("No error introduced.")
        return data
    else:
        print("Introducing an error.")
        rand_bit_flip = randint(0, 6)
        print(f"Error introduced at index: {rand_bit_flip}")
        data[rand_bit_flip] = 1 - data[rand_bit_flip]
        return data


def correct_error(data):
    p1, p2, p4 = calculate_parity_bits(data)
    if p1 == p2 == p4 == 0:
        print("No error found.")
        return data
    else:
        error_index = (p4 * 4) + (p2 * 2) + p1 - 1
        print(f"Error detected at index: {error_index}")
        data[error_index] = 1 - data[error_index]
        return data


def main():
    buff = [int(i) for i in input("Enter 4 binary digits: ").strip()]
    
    data = encode_data(buff)
    print(f"Data without encoding: {data}")
    
    data[0], data[1], data[3] = calculate_parity_bits(data)
    print(f"Encoded data: {data}")
    
    data_with_error = introduce_error(data)
    print(f"Data after possible error: {data_with_error}")
    
    corrected_data = correct_error(data_with_error)
    print(f"Corrected data: {corrected_data}")


if __name__ == "__main__":
    main()


"""
