mx = input("Enter message (binary): ")
gx = input("Enter generator (binary): ")

dividend = int(mx, 2)
divisor = int(gx, 2)

remainder = dividend

while remainder.bit_length() >= divisor.bit_length():
    shift = remainder.bit_length() - divisor.bit_length()
    remainder ^= divisor << shift

print(f"CRC remainder: {bin(remainder)[2:]}")