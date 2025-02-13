"use strict";
const table = document.getElementById("insertTable");
if (table) {
    const td = table.rows[1].cells[4];
    const input = td.firstElementChild;
    if (input)
        input.value = 'NULL';
}
const btn = window.document.getElementById("insert_submit");
const con_sugest = window.document.getElementById('continue');
const end_sugest = window.document.getElementById('end');
if (btn && con_sugest && end_sugest) {
    con_sugest.style.display = 'none';
    end_sugest.style.display = 'none';
    btn.addEventListener("input", () => {
        con_sugest.style.display = '';
        end_sugest.style.display = '';
        console.log('test');
    });
}
