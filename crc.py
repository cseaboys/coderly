def crc(mx, gx):
    pad_len = len(gx) - 1
    dividend = int(mx + "0" * pad_len, 2)
    divisor = int(gx, 2)
    remainder = bin((dividend % divisor) + 1)[2:]

    print(f"{remainder=}")
    encoded_data = mx + remainder
    print(f"{encoded_data=}")

    received_data_properly = int(encoded_data, 2) % divisor == 0
    print(f"{received_data_properly= }")


mx = "101011"
gx = "1101"
crc(mx, gx)
