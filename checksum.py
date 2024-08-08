import sys
from typing import List
#redirect input 
sys.stdin = open("input.txt","r")
sys.stdout = open("output.txt","w")
 
buff :str = input()
s :int = int(input())

#chunk and sum data
chunks :List[int] = [int("0b" + buff[i:i+s],2) for i in range(0,len(buff),s)]
sum_of_chunks :str = bin(sum(chunks)) 
print("Chunks: ", chunks)
print(f"{sum_of_chunks=}")

def get_complement(buff :str):
    buff = buff[2:] #discard 0b at the start
    return "0b"+"".join("1" if bit == "0" else "0" for bit in buff)

complement_of_chunks_sum = get_complement(sum_of_chunks)
print(f"{complement_of_chunks_sum=}")

sum_of_chunks_and_complement = bin(int(sum_of_chunks,2)+int(complement_of_chunks_sum,2))
print(f"{sum_of_chunks_and_complement=}")
print(f"{get_complement(sum_of_chunks_and_complement)=}")


