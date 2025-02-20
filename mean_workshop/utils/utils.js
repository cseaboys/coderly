function getEl(sel) {
    return document.querySelector(sel)
}
function setTextContent(sel, content) {
    getEl(sel).textContent = content
}
function appendTextContent(sel, content) {
    getEl(sel).textContent = content
}
function setStyle(sel, style) {
    getEl(sel).style = style
}

async function delay(ms) {
    await new Promise(
        (resolve) => {
            setTimeout(() => { resolve(); }, ms);
        })
}