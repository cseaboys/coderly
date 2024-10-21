import asyncio
import random

TIMEOUT: int = 1


async def send_data(data_buffer: list[object]):
    for data in data_buffer:
        print(f"Transmitting data {data}")
        while True:
            try:
                success: bool = await asyncio.wait_for(transmit_data(data), TIMEOUT)
                if success:
                    print(f"ACK received for data {data}")
                    print(f"Transmitted data {data} successfully")
                    break
                else:
                    print(f"NACK received for {data}, retrying transfer")
            except asyncio.TimeoutError:
                print("Timeout occurred, retrying transfer...")


async def transmit_data(data: object) -> bool:
    # simulate timeout
    await asyncio.sleep(random.randint(0, 2))
    if random.random() < 0.95:
        print(f"Data {data} received")
        return True
    return False


async def main():
    data: list[object] = [1, 2, 3, 4]
    await send_data(data)


if __name__ == "__main__":
    asyncio.run(main())
