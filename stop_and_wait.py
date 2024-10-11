import asyncio
import random

TIMEOUT = 1


async def send_data(data_buffer):
    for data in data_buffer:
        print(f"Transmitting data {data}")
        while True:
            try:
                success = await asyncio.wait_for(transmit_data(data), TIMEOUT)
                if success:
                    print(f"ACK recieved for data {data}")
                    print(f"Transmitted data {data} successfully")
                    break
                else:
                    print(f"NACK recieved for {data}")
            except asyncio.TimeoutError:
                print(f"Timeout occured, retrying...")


async def transmit_data(data):
    # Simulate timeout
    await asyncio.sleep(random.randint(0, 2))
    if random.random() < 0.95:
        print(f"Data {data} received")
        return True
    return False


async def main():
    data = [1, 2, 3, 4]
    await send_data(data)


if __name__ == "__main__":
    asyncio.run(main())
