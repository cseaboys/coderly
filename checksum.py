bin_ = lambda x: bin(x)[2:]
buff: str = input()
num_of_chunks: int = int(input())
chunks_length: int = len(buff) // num_of_chunks
chunks: list[str] = [
    buff[i : i + chunks_length] for i in range(0, len(buff), chunks_length)
]
sum_of_chunks: str = bin_(sum(int(chunk, 2) for chunk in chunks))
print("Chunks: ", *chunks)
print(f"{sum_of_chunks=}")


def get_complement(buff: str):
    return "".join("1" if bit == "0" else "0" for bit in buff)


complement_of_chunks_sum = get_complement(sum_of_chunks)
print(f"Checksum: {complement_of_chunks_sum}")
print(f"{complement_of_chunks_sum=}")

sum_of_chunks_and_complement = bin_(
    int(sum_of_chunks, 2) + int(complement_of_chunks_sum, 2)
)
print(f"{sum_of_chunks_and_complement= }")
checksum_valid = int(get_complement(sum_of_chunks_and_complement), 2) == 0
print(f"{checksum_valid= }")