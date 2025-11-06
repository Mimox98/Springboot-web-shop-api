// Auto-detect base URL and allow a configurable API prefix if you version your API.
const ORIGIN = window.location.origin;

// If your endpoints live under /api/v1, set this to "/api/v1".
// If they are at the root, leave it as "".
const BASE_PATH = ""; // e.g., "/api/v1"

function fullUrl(path) {
    const cleaned = path.startsWith("/") ? path : `/${path}`;
    return `${ORIGIN}${BASE_PATH}${cleaned}`;
}

async function getJson(path) {
    const url = fullUrl(path);
    const res = await fetch(url, { headers: { "Accept": "application/json" } });
    const text = await res.text();

    let body;
    try { body = JSON.parse(text); } catch { body = text; }

    if (!res.ok) {
        const msg = typeof body === "string" ? body : JSON.stringify(body, null, 2);
        throw new Error(`${res.status} ${res.statusText}\n${msg}`);
    }
    return body;
}

function showOutput(data) {
    const el = document.getElementById("output");
    el.textContent = typeof data === "string" ? data : JSON.stringify(data, null, 2);
}

async function runPath(rawPath) {
    const path = rawPath.trim().startsWith("/") ? rawPath.trim() : `/${rawPath.trim()}`;
    const target = document.getElementById("console");
    if (target) target.scrollIntoView({ behavior: "smooth", block: "start" });

    const input = document.getElementById("pathInput");
    if (input) input.value = path;

    showOutput(`Fetching ${BASE_PATH}${path} …`);
    try {
        const json = await getJson(path);
        showOutput(json);
    } catch (e) {
        showOutput(e.toString());
    }
}

function wireTryButtons() {
    const buttons = document.querySelectorAll("[data-try]");
    for (const btn of buttons) {
        btn.addEventListener("click", () => {
            const path = btn.getAttribute("data-try") || "/users";
            runPath(path);
        });
    }
}

function wireConsole() {
    const input = document.getElementById("pathInput");
    const run = document.getElementById("runBtn");
    run.addEventListener("click", () => {
        const raw = (input.value || "/users");
        runPath(raw);
    });
}

function setYear() {
    const y = document.getElementById("year");
    if (y) y.textContent = new Date().getFullYear();
}

document.addEventListener("DOMContentLoaded", () => {
    wireTryButtons();
    wireConsole();
    setYear();
});
