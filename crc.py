def crc(mx,gx):
    pad_len = len(gx)-1
    dividend = int(mx.zfill(pad_len),2)
    divisor = int(gx,2)
    remainder = bin(dividend%divisor)[2:]

    print(f"{remainder=}")

    encoded_data = mx+remainder

    print(f"{encoded_data}")

    has_error = int(encoded_data,2) % divisor == 0
    print(f"{has_error=}")
