mx = input("Enter message (binary): ")
gx = input("Enter generator (binary): ")

dividend = int(mx + '0' * (len(gx) - 1), 2)
divisor = int(gx, 2)

remainder = dividend

while remainder.bit_length() >= divisor.bit_length():
    shift = remainder.bit_length() - divisor.bit_length()
    remainder ^= divisor << shift

expected_length = len(gx) - 1

remainder_binary = bin(remainder)[2:]
padded_remainder = remainder_binary.zfill(expected_length)

print(f"Calculated CRC: {padded_remainder}")

print("\nVerification:")
encoded_data = mx + padded_remainder
print(f"Encoded message (message + CRC): {encoded_data}")

dividend = int(encoded_data, 2)
remainder = dividend

while remainder.bit_length() >= divisor.bit_length():
    shift = remainder.bit_length() - divisor.bit_length()
    remainder ^= divisor << shift

if remainder == 0:
    print("CRC Verification: Passed (Remainder is zero)")
else:
    print(f"CRC Verification: Failed (Remainder is {bin(remainder)[2:].zfill(expected_length)})")

mx, gx = input(), input()
r = int(mx + '0' * (len(gx) - 1), 2)
while r.bit_length() >= len(gx): 
    r ^= int(gx, 2) << (r.bit_length() - len(gx))
print(f"CRC: {(crc := bin(r)[2:].zfill(len(gx)-1))}")
r = int(mx + crc, 2)
print(f"Encoded data: {bin(r)[2:]}")
while r.bit_length() >= len(gx): 
    r ^= int(gx, 2) << (r.bit_length() - len(gx))
print("Verification: Passed" if r == 0 else f"Failed ({bin(r)[2:].zfill(len(gx)-1)})")
