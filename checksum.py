import sys

# redirect input
sys.stdin = open("input.txt", "r")
# 10101111
# 2
sys.stdout = open("output.txt", "w")

buff: str = input()
num_of_chunks :int = int(input())
chunks_length :int = len(buff)//num_of_chunks
# chunk and sum data
chunks: list[str] = ["0b" + buff[i : i + chunks_length] for i in range(0, len(buff), chunks_length)]
sum_of_chunks: str = bin(sum(map(lambda x: int(x, 2), chunks)))
print("Chunks: ", *chunks)
print(f"{sum_of_chunks=}")
print(f"Checksum: {sum_of_chunks}")


def get_complement(buff: str):
    buff = buff[2:]  # discard 0b at the start
    return "0b" + "".join("1" if bit == "0" else "0" for bit in buff)


complement_of_chunks_sum = get_complement(sum_of_chunks)
print(f"{complement_of_chunks_sum=}")

sum_of_chunks_and_complement = bin(
    int(sum_of_chunks, 2) + int(complement_of_chunks_sum, 2)
)
print(f"{sum_of_chunks_and_complement=}")
print(f"{int(get_complement(sum_of_chunks_and_complement),2) == 0 =}")
