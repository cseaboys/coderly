remainder = dividend

while remainder.bit_length() >= divisor.bit_length():
    shift = remainder.bit_length() - divisor.bit_length()
    remainder ^= divisor << shift
