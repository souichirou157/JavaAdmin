"use strict";
/**npx tsc app.tsでコンパイルnpx tsc --init ts.cofigでできる npx tscすべてのts */
/**https://github.com/souichirou157/adminsite.git */
function getAdmin() { return document.getElementsByClassName('btn')[0]; }
function getDev() { return document.getElementsByClassName('btn')[1]; }
//th:action="@{/buttonClick}" 
console.log('console.test');
getAdmin().addEventListener('click', function () {
    alert('test');
});
getDev().addEventListener('click', function () {
    console.log('console.test');
});
console.log('console.test');
