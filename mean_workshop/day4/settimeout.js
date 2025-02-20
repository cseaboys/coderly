function doWork() {
    for (let i = 1; i <= 3; i++) {
        setTimeout(() => {
            console.log("Hi", i);
        }, 3000 * i);
    }
}
// doWork()



await delay(3000)
console.log("after 3 seconds");