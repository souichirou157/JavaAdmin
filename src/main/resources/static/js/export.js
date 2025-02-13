"use strict";
/** th:onclick="|trriger()|" */
const flag = window.document.getElementById('loder');
const exportbtn = window.document.getElementById('insert_submit');
//
//const ex_con = window.document.getElementById('ex_continue') as HTMLElement | null;
//const ex_end = window.document.getElementById('ex_end') as HTMLElement | null;
if (flag && exportbtn) {
    exportbtn.addEventListener('input', () => {
        flag.click();
    });
}
